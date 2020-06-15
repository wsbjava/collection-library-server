package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Error;
import io.swagger.model.Item;
import io.swagger.model.ItemList;
import io.swagger.model.ItemRequest;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public abstract class CollectionApiService {
    public abstract Response addItem(ItemRequest body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getCollection( @NotNull Integer limit, @NotNull Integer offset, @NotNull String phrases,SecurityContext securityContext) throws NotFoundException;
    public abstract Response removeItem(Item body,SecurityContext securityContext) throws NotFoundException;
}
