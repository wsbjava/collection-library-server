package io.swagger.api.factories;

import io.swagger.api.RentalsApiService;
import io.swagger.api.impl.RentalsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class RentalsApiServiceFactory {
    private final static RentalsApiService service = new RentalsApiServiceImpl();

    public static RentalsApiService getRentalsApi() {
        return service;
    }
}
