package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Error;
import io.swagger.model.Suggestion;
import io.swagger.model.UserSuggestionList;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public abstract class SuggestionApiService {
    public abstract Response getSuggestionList( @NotNull Integer limit, @NotNull Integer offset, @NotNull String phrases,SecurityContext securityContext) throws NotFoundException;
    public abstract Response putSuggestionIdAccept(Integer id,SecurityContext securityContext) throws NotFoundException;
    public abstract Response putSuggestionIdReject(Integer id,SecurityContext securityContext) throws NotFoundException;
}
