package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.SuggestionApiService;
import io.swagger.api.factories.SuggestionApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Error;
import io.swagger.model.Suggestion;
import io.swagger.model.UserSuggestionList;

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

@Path("/suggestion")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the suggestion API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class SuggestionApi  {
   private final SuggestionApiService delegate;

   public SuggestionApi(@Context ServletConfig servletContext) {
      SuggestionApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("SuggestionApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (SuggestionApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = SuggestionApiServiceFactory.getSuggestionApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve data about whole suggestions", notes = "", response = UserSuggestionList.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "User suggestion list", response = UserSuggestionList.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized error e.g. not priviliged", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response getSuggestionList(@ApiParam(value = "Number of records to return",required=true) @QueryParam("limit") Integer limit
,@ApiParam(value = "Initial index",required=true) @QueryParam("offset") Integer offset
,@ApiParam(value = "Phrases to search",required=true) @QueryParam("phrases") String phrases
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSuggestionList(limit,offset,phrases,securityContext);
    }
    @PUT
    @Path("/{id}/accept")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Accept suggestion.", notes = "", response = Suggestion.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = Suggestion.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized error e.g. not priviliged", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response putSuggestionIdAccept(@ApiParam(value = "Suggestion id",required=true) @PathParam("id") Integer id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.putSuggestionIdAccept(id,securityContext);
    }
    @PUT
    @Path("/{id}/reject")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Reject suggestion.", notes = "", response = Suggestion.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = Suggestion.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized error e.g. not priviliged", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response putSuggestionIdReject(@ApiParam(value = "Suggestion id",required=true) @PathParam("id") Integer id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.putSuggestionIdReject(id,securityContext);
    }
}
