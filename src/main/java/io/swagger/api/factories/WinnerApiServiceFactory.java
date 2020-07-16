package io.swagger.api.factories;

import io.swagger.api.WinnerApiService;
import io.swagger.api.impl.WinnerApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-16T04:40:34.487Z[GMT]")public class WinnerApiServiceFactory {
    private final static WinnerApiService service = new WinnerApiServiceImpl(SerlizerFactories.getMapper(), MongoDbDriverFactories.getGameDao());

    public static WinnerApiService getWinnerApi() {
        return service;
    }
}
