package edu.learn.cdi.demo.ws;

import edu.learn.cdi.demo.domain.models.Item;

import edu.learn.cdi.demo.bean.ServiceBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@io.swagger.annotations.Api(value = "user")
@RequestScoped
public class UserWS {
    private ServiceBean service;

    public UserWS() {
    }

    @Inject
    public UserWS(ServiceBean service) {
        this.service = service;
    }

    @io.swagger.annotations.ApiOperation(value = "Produces Hello message")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return "Hello!";
    }

    @io.swagger.annotations.ApiOperation(value = "Print all Items")
    @GET
    @Path("/getAllItems")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getAllItems() {
        return this.service.getAllItems();
    }

    @PUT
    @Path("/updateItemsById")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(Item item) {
        this.service.update(item);
        return "Product info with this id was updated";
    }

    @POST
    @Path("/addItems")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String create(Item item) {
        this.service.create(item);
        return "Product added";
    }

    @DELETE
    @Path("/deleteItemsById")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(Item item) {
        this.service.delete(item);
        return "Product info with this id was deleted";
    }

}
