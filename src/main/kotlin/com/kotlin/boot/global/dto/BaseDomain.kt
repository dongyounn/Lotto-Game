package com.kotlin.boot.global.dto

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@MappedSuperclass
abstract class BaseDomain {
    @Column(name = "CREATED", updatable = false)
    lateinit var created: LocalDateTime

    @Column(name = "UPDATED")
    lateinit var updated: LocalDateTime

    @PrePersist
    fun prePersist() {
        this.created = LocalDateTime.now()
        this.updated = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        this.updated = LocalDateTime.now()
    }
}