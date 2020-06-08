package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.RentalsApiService;
import io.swagger.api.factories.RentalsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.UserItemRentalList;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/rentals")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the rentals API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class RentalsApi  {
   private final RentalsApiService delegate;

   public RentalsApi(@Context ServletConfig servletContext) {
      RentalsApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("RentalsApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (RentalsApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = RentalsApiServiceFactory.getRentalsApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve data about whole rentals", notes = "", response = UserItemRentalList.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "User rental list", response = UserItemRentalList.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response getUserRentalsList(@ApiParam(value = "Number of records to return",required=true) @QueryParam("limit") Integer limit
,@ApiParam(value = "Initial index",required=true) @QueryParam("offset") Integer offset
,@ApiParam(value = "Phrases to search",required=true) @QueryParam("phrases") String phrases
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getUserRentalsList(limit,offset,phrases,securityContext);
    }
}
