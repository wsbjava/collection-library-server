package io.swagger.api.factories;

import io.swagger.api.ItemApiService;
import io.swagger.api.impl.ItemApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class ItemApiServiceFactory {
    private final static ItemApiService service = new ItemApiServiceImpl();

    public static ItemApiService getItemApi() {
        return service;
    }
}
