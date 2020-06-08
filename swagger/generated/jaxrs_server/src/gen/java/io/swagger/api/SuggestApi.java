package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.SuggestApiService;
import io.swagger.api.factories.SuggestApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.Item;
import io.swagger.model.ItemRequest;

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

@Path("/suggest")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the suggest API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class SuggestApi  {
   private final SuggestApiService delegate;

   public SuggestApi(@Context ServletConfig servletContext) {
      SuggestApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("SuggestApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (SuggestApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = SuggestApiServiceFactory.getSuggestApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create suggestions.", notes = "", response = Item.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = Item.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response postSuggeston(@ApiParam(value = "Item library data." ,required=true) ItemRequest body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.postSuggeston(body,securityContext);
    }
}
