package io.swagger.api.factories;

import io.swagger.api.LibraryApiService;
import io.swagger.api.impl.LibraryApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class LibraryApiServiceFactory {
    private final static LibraryApiService service = new LibraryApiServiceImpl();

    public static LibraryApiService getLibraryApi() {
        return service;
    }
}
