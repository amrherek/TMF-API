package com.orange.apibss.common.configuration;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.orange.apibss.common.model.PartialResult;

@ControllerAdvice
public class ResourceSizeAdvice implements ResponseBodyAdvice<PartialResult<?>> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // Checks if this advice is applicable.
        // In this case it applies to any endpoint which returns a page.
        return PartialResult.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public PartialResult<?> beforeBodyWrite(PartialResult<?> partialResult, MethodParameter returnType,
            MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().add("X-Total-Count", String.valueOf(partialResult.getTotalResultCount()));
        response.getHeaders().add("X-Result-Count", String.valueOf(partialResult.getPartialResultList().size()));
        return partialResult;
    }

}
