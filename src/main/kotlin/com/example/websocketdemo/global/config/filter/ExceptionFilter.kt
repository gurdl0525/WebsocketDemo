package com.example.websocketdemo.global.config.filter

import com.example.websocketdemo.global.error.ErrorCode
import com.example.websocketdemo.global.error.ErrorResponse
import com.example.websocketdemo.global.exception.BusinessException
import com.example.websocketdemo.global.exception.InternalServerErrorException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch(e: Exception) {
            e.printStackTrace()
            when (e) {
                is BusinessException -> sendErrorMessage(response, e.errorCode)
                else -> sendErrorMessage(response, InternalServerErrorException.errorCode)
            }
        }
    }

    private fun sendErrorMessage(response: HttpServletResponse, errorCode: ErrorCode) {
        response.let {
            it.status = errorCode.status()
            it.contentType = MediaType.APPLICATION_JSON_VALUE
            it.writer.write(objectMapper.writeValueAsString(ErrorResponse.of(errorCode)))
        }
    }
}