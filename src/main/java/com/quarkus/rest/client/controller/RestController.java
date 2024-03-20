package com.quarkus.rest.client.controller;


import com.quarkus.rest.client.model.CacheMap;
import com.quarkus.rest.client.service.HttpClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/caching")
public class RestController {

    @Inject
    public HttpClientService httpClientService;

    @ConfigProperty(name = "base.url")
    public String baseUrl;
    public ObjectMapper mapper = new ObjectMapper();

    /* Endpoint to test the Client */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Response testApi() {
        String url = baseUrl + "/caching/test";
        Map<String, String> headers = new HashMap<>();
        Map response = null;
        try {
            response = httpClientService.getApi(url, headers);
            int statusCode = (int)response.get("statusCode");
            response.remove("statusCode");
            return Response.status(Response.Status.fromStatusCode(statusCode))
                    .entity(response)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Endpoint to retrieve a value from the cache based on a key */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{key}")
    public Response getKey(@PathParam("key") String key) {
        String url = baseUrl + "/caching/" + key;
        Map<String, String> headers = new HashMap<>();
        Map response = null;
        try {
            response = httpClientService.getApi(url, headers);
            int statusCode = (int)response.get("statusCode");
            response.remove("statusCode");
            return Response.status(Response.Status.fromStatusCode(statusCode))
                    .entity(response)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Endpoint to delete a value from the cache based on a key */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{key}")
    public Response deleteKey(@PathParam("key") String key) {
        String url = baseUrl + "/caching/" + key;
        Map<String, String> headers = new HashMap<>();
        Map response = null;
        try {
            response = httpClientService.deleteApi(url, headers);
            int statusCode = (int)response.get("statusCode");
            response.remove("statusCode");
            return Response.status(Response.Status.fromStatusCode(statusCode))
                    .entity(response)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Endpoint to add or update a key in the cache */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response addKey(CacheMap cacheMap) {
        String url = baseUrl + "/caching/";
        Map<String, String> headers = new HashMap<>();
        Map requestBody = mapper.convertValue(cacheMap, Map.class);
        Map response = null;
        try {
            response = httpClientService.postApi(url, requestBody, headers);
            int statusCode = (int)response.get("statusCode");
            response.remove("statusCode");
            return Response.status(Response.Status.fromStatusCode(statusCode))
                    .entity(response)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
