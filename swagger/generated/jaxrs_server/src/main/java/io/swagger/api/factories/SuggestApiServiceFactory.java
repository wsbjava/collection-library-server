package io.swagger.api.factories;

import io.swagger.api.SuggestApiService;
import io.swagger.api.impl.SuggestApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class SuggestApiServiceFactory {
    private final static SuggestApiService service = new SuggestApiServiceImpl();

    public static SuggestApiService getSuggestApi() {
        return service;
    }
}
