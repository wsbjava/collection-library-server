package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ItemApiService;
import io.swagger.api.factories.ItemApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.Item;
import io.swagger.model.ItemRentalRequest;

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

@Path("/item")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the item API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class ItemApi  {
   private final ItemApiService delegate;

   public ItemApi(@Context ServletConfig servletContext) {
      ItemApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("ItemApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (ItemApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = ItemApiServiceFactory.getItemApi();
      }

      this.delegate = delegate;
   }

    @POST
    @Path("/rentRequest")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Rent an item.", notes = "", response = Item.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = Item.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response rentRequest(@ApiParam(value = "Role" ,required=true) ItemRentalRequest body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.rentRequest(body,securityContext);
    }
}
