package org.localtest.controller;

import org.localtest.model.User;
import org.localtest.service.UserServiceImpl;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserController {

   @ConfigProperty(name = "cache.client", defaultValue = "APACHE_CLIENT")
    String cacheClient;

    @Inject
    public UserServiceImpl userServiceImpl;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/test")
    public String hello() {
        return "Hello From Quarkus!!! Cache Client we are using  :: "+cacheClient;
    }


    @GET
    @Path("/{userId}")
    public String getBooks(@PathParam("userId") int userId) {
        return userServiceImpl.getUserById(userId);
    }

    @DELETE
    @Path("/{userId}")
    public void deleteBook(@PathParam("userId") int userId) {
        userServiceImpl.delete(userId);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(User user) {
        userServiceImpl.saveOrUpdate(user.getUserId(), user.getUserName());
        return "User with userId " + user.getUserId() + " Added";
    }
}
