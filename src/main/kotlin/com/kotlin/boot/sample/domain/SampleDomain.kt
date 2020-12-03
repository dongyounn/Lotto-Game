package com.kotlin.boot.sample.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "SAMPLE")
data class SampleDomain(
        @Id
        val userId: String,
        val name: String
)

