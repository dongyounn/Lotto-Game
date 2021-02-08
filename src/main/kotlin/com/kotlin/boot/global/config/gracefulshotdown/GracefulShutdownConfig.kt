//package com.kotlin.boot.global.config.gracefulshotdown
//
//import io.undertow.servlet.api.DeploymentInfo
//import org.springframework.boot.web.embedded.undertow.UndertowDeploymentInfoCustomizer
//import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//
//@Configuration
//class GracefulShutdownConfig(
//    private val gracefulShutdownHandlerWrapper: GracefulShutdownHandlerWrapper
//) {
//    @Bean
//    fun servletWebServerFactory(): UndertowServletWebServerFactory {
//        val factory = UndertowServletWebServerFactory()
//        factory.addDeploymentInfoCustomizers(UndertowDeploymentInfoCustomizer { deploymentInfo: DeploymentInfo ->
//            deploymentInfo.addOuterHandlerChainWrapper(
//                gracefulShutdownHandlerWrapper
//            )
//        })
//        return factory
//    }
//}
