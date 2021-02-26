package com.kotlin.boot.global.utils
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

const val MAX_ROW_SIZE = 3000

data class CustomPageRequest (
        val page: Int = 0,
        val size: Int = 10,
        val orders: List<String> = ArrayList()
){
    fun of(): PageRequest = PageRequest.of(
            if (page < 0) 0 else page,
            if (size > MAX_ROW_SIZE) MAX_ROW_SIZE else size,
            generateSort()
    )

    private fun generateSort() = if (orders.isNullOrEmpty()) {
        Sort(Sort.Direction.DESC, "created")
    }
    else {
        Sort.by(orders
                .asSequence()
                .map {
                    val order = it.split(":")
                    val property = order[0]
                    val direction = if (order[1].toUpperCase().contentEquals("ASC")) Sort.Direction.ASC else Sort.Direction.DESC
                    Sort.Order(direction, property)
                }
                .toList()
        )
    }
}