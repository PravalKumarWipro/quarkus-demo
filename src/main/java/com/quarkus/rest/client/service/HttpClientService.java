package com.quarkus.rest.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface HttpClientService {

    Map getApi(String url, Map<String,String> headers) throws IOException;
    Map postApi(String url,Map requestBody, Map<String,String> headers) throws IOException;
    Map putApi(String url,Map requestBody, Map<String,String> headers) throws IOException;
    Map deleteApi(String url, Map<String,String> headers) throws IOException;
}
