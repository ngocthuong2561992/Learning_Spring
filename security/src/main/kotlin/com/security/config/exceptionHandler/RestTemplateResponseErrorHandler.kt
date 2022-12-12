package com.security.config.exceptionHandler

import org.springframework.http.HttpStatus
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.ResponseErrorHandler
import java.io.IOException

internal class RestTemplateResponseErrorHandler : ResponseErrorHandler {
    @Throws(IOException::class)
    override fun hasError(response: ClientHttpResponse): Boolean {
        val series = response.statusCode.series()
        return series == HttpStatus.Series.CLIENT_ERROR || series == HttpStatus.Series.SERVER_ERROR
    }

    @Throws(IOException::class)
    override fun handleError(response: ClientHttpResponse) {
        val msgError = ("""StatusCode: ${response.statusCode.value()} ${response.statusCode.reasonPhrase}""")
        if (response.statusCode.series() == HttpStatus.Series.SERVER_ERROR) {
            throw IOException(msgError)
        } else if (response.statusCode.series() == HttpStatus.Series.CLIENT_ERROR) {
            throw IOException(msgError)
        }
    }
}