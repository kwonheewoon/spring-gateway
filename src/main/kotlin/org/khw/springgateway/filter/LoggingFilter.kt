package org.khw.springgateway.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class LoggingFilter : GlobalFilter {

    private val logger = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        val route: Route? =
            exchange!!.getAttribute("org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute")
        if (route != null && "shoppera" == route.getId()) {
            // shoppera로 들어오는 요청에 대해 로그 기록
            logger.info("Request to shoppera: " + exchange.request.path)
        }
        return chain!!.filter(exchange)
    }
}