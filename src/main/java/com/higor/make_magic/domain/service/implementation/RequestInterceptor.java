package com.higor.make_magic.domain.service.implementation;

import com.higor.make_magic.domain.service.exception.ThirdPartyApiException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * This class acts as an interceptor to Http Request in PoterApi
 * Your job is intercept the request, and check the response status code
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);

        if (response.code() != HttpStatus.OK.value())
            throw new ThirdPartyApiException("Please pass the correct api key");

        return response;
    }
}
