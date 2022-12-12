package com.jpa.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus.Series series = response.getStatusCode().series();
        return series == HttpStatus.Series.CLIENT_ERROR || series == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String msgError = "StatusCode: " + response.getStatusCode().value() + " "
                + response.getStatusCode().getReasonPhrase();
        if(response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new IOException(msgError);
        }else if(response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            throw new IOException(msgError);
        }
    }
}
