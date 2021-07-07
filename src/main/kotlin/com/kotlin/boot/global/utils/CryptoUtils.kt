package com.kotlin.boot.global.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

const val ALGORITHM = "AES/ECB/PKCS5Padding"

@Component
class CryptoUtils {

    @Value("\${enc.key}")
    private val key = ""

    fun decrypt(dbData: String) =
        SecretKeySpec(key.toByteArray(), "AES").let {
            try {
                Cipher.getInstance(ALGORITHM).let { cipher ->
                    cipher.init(Cipher.DECRYPT_MODE, it)
                    String(cipher.doFinal(Base64.getDecoder().decode(dbData)))
                }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }


    fun encrypt(attribute: String) =
        SecretKeySpec(key.toByteArray(), "AES").let {
            try {
                Cipher.getInstance(ALGORITHM).let { cipher ->
                    cipher.init(Cipher.ENCRYPT_MODE, it)
                    String(Base64.getEncoder().encode(cipher.doFinal(attribute.toByteArray())))
                }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
}