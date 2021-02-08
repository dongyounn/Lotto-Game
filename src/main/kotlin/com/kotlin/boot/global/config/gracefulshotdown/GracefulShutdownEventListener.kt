//package com.kotlin.boot.global.config.gracefulshotdown
//
//import org.slf4j.LoggerFactory
//import org.springframework.context.ApplicationListener
//import org.springframework.context.event.ContextClosedEvent
//import org.springframework.stereotype.Component
//
//
//@Component
//class GracefulShutdownEventListener(
//    private val gracefulShutdownHandlerWrapper: GracefulShutdownHandlerWrapper
//) : ApplicationListener<ContextClosedEvent> {
//
//    private val log = LoggerFactory.getLogger(this::class.java)
//
//    override fun onApplicationEvent(event: ContextClosedEvent) {
//        log.info("GRACEFUL_SHUTDOWN_STARTED")
//        gracefulShutdownHandlerWrapper.getGracefulShutdownHandler().shutdown()
//        try {
//            gracefulShutdownHandlerWrapper.getGracefulShutdownHandler().awaitShutdown()
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            log.error("GRACEFUL_SHUTDOWN_FAILED")
//        }
//        log.info("GRACEFUL_SHUTDOWN_FINISHED")
//    }
//}