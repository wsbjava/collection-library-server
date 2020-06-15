package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UserApiService;
import io.swagger.api.factories.UserApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.AdminToUserMessage;
import io.swagger.model.Error;
import io.swagger.model.Item;
import io.swagger.model.MessagesList;
import io.swagger.model.ModifyRole;
import io.swagger.model.RegisterUserRequest;
import io.swagger.model.Role;
import io.swagger.model.User;

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

@Path("/user")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the user API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class UserApi  {
   private final UserApiService delegate;

   public UserApi(@Context ServletConfig servletContext) {
      UserApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UserApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UserApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = UserApiServiceFactory.getUserApi();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/message")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Retrieve data about whole messages", notes = "", response = MessagesList.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Messages list", response = MessagesList.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response getMessages(@ApiParam(value = "Number of records to return",required=true) @QueryParam("limit") Integer limit
,@ApiParam(value = "Initial index",required=true) @QueryParam("offset") Integer offset
,@ApiParam(value = "Phrases to search",required=true) @QueryParam("phrases") String phrases
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getMessages(limit,offset,phrases,securityContext);
    }
    @POST
    @Path("/message")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Send message to user.", notes = "", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response messageSend(@ApiParam(value = "Target userId and text." ,required=true) AdminToUserMessage body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.messageSend(body,securityContext);
    }
    @PUT
    @Path("/{id}/modifyRole")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Modify role of user.", notes = "", response = Role.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = Role.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized error e.g. not priviliged", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response modifyRole(@ApiParam(value = "Role modification",required=true) @PathParam("id") Integer id
,@ApiParam(value = "Role" ,required=true) ModifyRole body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.modifyRole(id,body,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Register user.", notes = "Registration of a new user.", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response newUser(@ApiParam(value = "New user data" ) RegisterUserRequest body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.newUser(body,securityContext);
    }
    @DELETE
    @Path("/message")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Remove message", notes = "", response = Item.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Success", response = Item.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized error e.g. not priviliged", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response removeMessage(@ApiParam(value = "Remove message" ,required=true) AdminToUserMessage body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.removeMessage(body,securityContext);
    }
}
