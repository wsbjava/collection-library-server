package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.LibraryApiService;
import io.swagger.api.factories.LibraryApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.CollectionLibraryRequest;
import io.swagger.model.Error;
import io.swagger.model.Item;

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

@Path("/library")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the library API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class LibraryApi  {
   private final LibraryApiService delegate;

   public LibraryApi(@Context ServletConfig servletContext) {
      LibraryApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("LibraryApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (LibraryApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = LibraryApiServiceFactory.getLibraryApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add item to library.", notes = "", response = Item.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = Item.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response postLibrary(@ApiParam(value = "Item library data." ,required=true) CollectionLibraryRequest body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.postLibrary(body,securityContext);
    }
}
