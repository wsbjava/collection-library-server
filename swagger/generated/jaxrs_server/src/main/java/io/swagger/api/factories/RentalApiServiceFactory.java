package io.swagger.api.factories;

import io.swagger.api.RentalApiService;
import io.swagger.api.impl.RentalApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class RentalApiServiceFactory {
    private final static RentalApiService service = new RentalApiServiceImpl();

    public static RentalApiService getRentalApi() {
        return service;
    }
}
