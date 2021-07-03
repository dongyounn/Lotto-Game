package com.kotlin.boot.global.dto

import com.kotlin.boot.global.utils.ALGORITHM
import org.springframework.beans.factory.annotation.Value
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.persistence.AttributeConverter
import javax.persistence.Convert

@Convert
class StringCryptoConverter : AttributeConverter<String, String> {

    @Value("\${enc.key}")
    private val key = ""

    override fun convertToEntityAttribute(dbData: String): String {
        val key = SecretKeySpec(key.toByteArray(), "AES")
        return try {
            val cipher: Cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, key)
            String(cipher.doFinal(Base64.getDecoder().decode(dbData)))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun convertToDatabaseColumn(attribute: String): String {
        val key = SecretKeySpec(key.toByteArray(), "AES")
        return try {
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            String(Base64.getEncoder().encode(cipher.doFinal(attribute.toByteArray())))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}