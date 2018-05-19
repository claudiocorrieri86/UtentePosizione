/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db.LocationHelper;
import db.UserHelper;
import java.util.List;
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
import model.Location;
import model.User;

/**
 * REST Web Service
 *
 * @author Cloud
 */
@Path("/location")
public class PositionResource {

    /**
     * Creates a new instance of UserResource
     */
    public PositionResource() {
    }

    /**
     * Retrieves representation of an instance of service.UserResource
     * @return an instance of java.lang.String
     */
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Location> getPosition(PositionInputBody requestBody) {
        LocationHelper lh = new LocationHelper();
        Double lat;
        Double lng;
        String start;
        String end;
        try{
            lat = requestBody.lat;
            lng = requestBody.lng;
            start = requestBody.start;
            end = requestBody.end;
        }catch(Exception e){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        
        List<Location> locations = lh.getLocation(lat, lng, start, end);
        if(locations == null || locations.isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        return locations;
    }

}
