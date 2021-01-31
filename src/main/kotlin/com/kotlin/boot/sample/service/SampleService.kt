package com.kotlin.boot.sample.service

import com.kotlin.boot.global.exception.BadRequestException
import com.kotlin.boot.global.exception.ErrorReason
import com.kotlin.boot.sample.domain.SampleDomain
import com.kotlin.boot.sample.infra.repository.SampleRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SampleService(
        private val sampleRepository: SampleRepository
) {
    @Transactional
    fun saveData(userId: String) {

        sampleRepository.save(SampleDomain(userId, "Hello"))
    }

    fun findData(userId: String): SampleDomain {
        return sampleRepository.findByUserId(userId) ?: throw BadRequestException(ErrorReason.INVALID_INPUT_DATA, "없엉")

    }
}