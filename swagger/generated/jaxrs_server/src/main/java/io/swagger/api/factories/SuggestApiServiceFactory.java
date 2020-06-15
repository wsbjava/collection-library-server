package io.swagger.api.factories;

import io.swagger.api.SuggestApiService;
import io.swagger.api.impl.SuggestApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class SuggestApiServiceFactory {
    private final static SuggestApiService service = new SuggestApiServiceImpl();

    public static SuggestApiService getSuggestApi() {
        return service;
    }
}
