package com.kotlin.boot.sample.infra.repository

import com.kotlin.boot.sample.domain.SampleDomain
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SampleRepository : JpaRepository<SampleDomain, String> {
    fun findByUserId(userId: String): SampleDomain?
}