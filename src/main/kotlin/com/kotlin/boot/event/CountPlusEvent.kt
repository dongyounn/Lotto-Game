package com.kotlin.boot.event

import com.kotlin.boot.game.domain.GameResultEntity
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

data class CountPlus(
    val result: GameResultEntity
)

@Component
class GameEventListener() {

    private val log = LoggerFactory.getLogger(this::class.java)

    @EventListener
    @Async
    fun onSomethingProblem(request: CountPlus) {
        log.info("Execution Event Listener - PARTICIPATE PLUS COUNT")
        request.result.plusPlayerNo()
    }

}