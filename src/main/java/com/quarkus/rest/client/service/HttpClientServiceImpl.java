package com.quarkus.rest.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class HttpClientServiceImpl implements HttpClientService {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public Map getApi(String url, Map<String, String> headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        Map apiResponse = new HashMap();
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = (CloseableHttpResponse) client
                     .execute(httpGet)) {
            String text = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            apiResponse = mapper.readValue(text, Map.class);
            apiResponse.put("statusCode",response.getStatusLine().getStatusCode());

        }
        return apiResponse;
    }

    @Override
    public Map postApi(String url, Map requestBody, Map<String, String> headers) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        Map apiResponse = new HashMap();
        StringEntity entity = new StringEntity(mapper.writeValueAsString(requestBody));
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = (CloseableHttpResponse) client
                     .execute(httpPost)) {

            String text = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            apiResponse = mapper.readValue(text, Map.class);
            apiResponse.put("statusCode",response.getStatusLine().getStatusCode());
        }
        return apiResponse;
    }

    @Override
    public Map putApi(String url, Map requestBody, Map<String, String> headers) throws IOException {

        HttpPut httpPut = new HttpPut(url);
        Map apiResponse = new HashMap();
        StringEntity entity = new StringEntity(mapper.writeValueAsString(requestBody));
        httpPut.setEntity(entity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = (CloseableHttpResponse) client
                     .execute(httpPut)) {

            String text = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            apiResponse = mapper.readValue(text, Map.class);
            apiResponse.put("statusCode",response.getStatusLine().getStatusCode());

        }
        return apiResponse;
    }

    @Override
    public Map deleteApi(String url, Map<String, String> headers) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        Map apiResponse = new HashMap();
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.setHeader("Content-type", "application/json");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = (CloseableHttpResponse) client
                     .execute(httpDelete)) {

            String text = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            apiResponse = mapper.readValue(text, Map.class);
            apiResponse.put("statusCode",response.getStatusLine().getStatusCode());
        }
        return apiResponse;
    }
}
