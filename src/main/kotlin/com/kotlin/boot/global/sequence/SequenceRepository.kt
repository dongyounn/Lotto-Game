package com.kotlin.boot.global.sequence

import com.zaxxer.hikari.HikariDataSource
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


interface SequenceRepository {
    fun getNextLong(seqEnum: SequenceEnum): Long
}

@Component
class SequenceRepositoryImpl(private val datasource: HikariDataSource) : SequenceRepository {
    @Transactional(readOnly = true)
    override fun getNextLong(seqEnum: SequenceEnum): Long
            = OracleSequenceMaxValueIncrementer(datasource, seqEnum.sequenceName).nextLongValue()
}