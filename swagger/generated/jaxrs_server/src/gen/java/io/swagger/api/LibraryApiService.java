package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.CollectionLibraryRequest;
import io.swagger.model.Error;
import io.swagger.model.Item;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public abstract class LibraryApiService {
    public abstract Response postLibrary(CollectionLibraryRequest body,SecurityContext securityContext) throws NotFoundException;
}
