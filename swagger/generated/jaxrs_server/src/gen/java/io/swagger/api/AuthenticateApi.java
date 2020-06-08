package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.AuthenticateApiService;
import io.swagger.api.factories.AuthenticateApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.AuthenticationRequest;
import io.swagger.model.Error;
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

@Path("/authenticate")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the authenticate API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class AuthenticateApi  {
   private final AuthenticateApiService delegate;

   public AuthenticateApi(@Context ServletConfig servletContext) {
      AuthenticateApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("AuthenticateApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (AuthenticateApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = AuthenticateApiServiceFactory.getAuthenticateApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Authenticate user", notes = "Authentication of existing user", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "Client error, e.g. unsufficient data", response = Error.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Server error, e.g. unexpected exception", response = Error.class) })
    public Response authUser(@ApiParam(value = "Authentication passes" ) AuthenticationRequest body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.authUser(body,securityContext);
    }
}
