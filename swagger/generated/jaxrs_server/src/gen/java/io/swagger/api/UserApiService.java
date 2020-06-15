package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.AdminToUserMessage;
import io.swagger.model.Error;
import io.swagger.model.Item;
import io.swagger.model.MessagesList;
import io.swagger.model.ModifyRole;
import io.swagger.model.RegisterUserRequest;
import io.swagger.model.Role;
import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public abstract class UserApiService {
    public abstract Response getMessages( @NotNull Integer limit, @NotNull Integer offset, @NotNull String phrases,SecurityContext securityContext) throws NotFoundException;
    public abstract Response messageSend(AdminToUserMessage body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response modifyRole(Integer id,ModifyRole body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response newUser(RegisterUserRequest body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response removeMessage(AdminToUserMessage body,SecurityContext securityContext) throws NotFoundException;
}
