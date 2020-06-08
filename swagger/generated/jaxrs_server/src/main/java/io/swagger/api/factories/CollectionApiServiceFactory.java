package io.swagger.api.factories;

import io.swagger.api.CollectionApiService;
import io.swagger.api.impl.CollectionApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class CollectionApiServiceFactory {
    private final static CollectionApiService service = new CollectionApiServiceImpl();

    public static CollectionApiService getCollectionApi() {
        return service;
    }
}
