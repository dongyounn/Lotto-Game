package com.kotlin.boot.sample.service

import com.kotlin.boot.sample.infra.repository.SampleRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SampleServiceTest(
    @Autowired private val sampleService: SampleService,
    @Autowired private val sampleRepository: SampleRepository
) {

    @BeforeEach
    fun saveDefaultData() {
        sampleService.saveData("12345")
    }

    @AfterEach
    fun deleteDefaultData() {
        sampleRepository.deleteById("12345")
    }

    @Test
    fun saveData() {
        print("HEllo")
    }

    @Test
    fun findData() {
        val result = sampleService.findData("12345")
        assertEquals(result.name, "Hello")
    }
}