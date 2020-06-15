package io.swagger.api.factories;

import io.swagger.api.SuggestionApiService;
import io.swagger.api.impl.SuggestionApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class SuggestionApiServiceFactory {
    private final static SuggestionApiService service = new SuggestionApiServiceImpl();

    public static SuggestionApiService getSuggestionApi() {
        return service;
    }
}
