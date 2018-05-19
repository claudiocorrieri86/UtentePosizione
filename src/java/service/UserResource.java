/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.UserHelper;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;

/**
 * REST Web Service
 *
 * @author Cloud
 */
@Path("/user")
public class UserResource {

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of service.UserResource
     * @return an instance of java.lang.String
     */
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getJson() {
        UserHelper uh = new UserHelper();
        User user = uh.getUserByUsername("a");
        if(user == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }*/

    /**
     * PUT method for updating or creating an instance of UserResource
     * @param username
     */
    @PUT
    @Path("/{username}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public User putJson(@PathParam("username") String username) {
        UserHelper uh = new UserHelper();
        User user = uh.getUserByUsername(username);
        if(user == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }else{
            Integer newActiveValue = null;
            if(user.getActive().intValue() == 0){
                newActiveValue = new Integer(1);
            }else{
                newActiveValue = new Integer(0);
            }
            user.setActive(newActiveValue);
            
            int updateResult = uh.updateUserActiveById(user.getActive(), user.getId());
            if(updateResult == 0){
                throw new WebApplicationException(Response.Status.NOT_MODIFIED);
            }
        }
        return user;
    }
}
