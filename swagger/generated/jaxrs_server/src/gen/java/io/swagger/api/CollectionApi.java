package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.CollectionApiService;
import io.swagger.api.factories.CollectionApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.Item;
import io.swagger.model.ItemList;
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

@Path("/collection")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the collection API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class CollectionApi  {
   private final CollectionApiService delegate;

   public CollectionApi(@Context ServletConfig servletContext) {
      CollectionApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("CollectionApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (CollectionApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = CollectionApiServiceFactory.getCollectionApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add item of collection", notes = "", response = Item.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Item.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response addItem(@ApiParam(value = "New item data." ,required=true) ItemRequest body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addItem(body,securityContext);
    }
    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve data about whole collection", notes = "", response = ItemList.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Collection list", response = ItemList.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response getCollection(@ApiParam(value = "Number of records to return",required=true) @QueryParam("limit") Integer limit
,@ApiParam(value = "Initial index",required=true) @QueryParam("offset") Integer offset
,@ApiParam(value = "Phrases to search",required=true) @QueryParam("phrases") String phrases
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getCollection(limit,offset,phrases,securityContext);
    }
    @DELETE
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Remove item from library.", notes = "", response = Item.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = Item.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized error e.g. not priviliged", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response removeItem(@ApiParam(value = "Remove item from library" ,required=true) Item body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.removeItem(body,securityContext);
    }
}
