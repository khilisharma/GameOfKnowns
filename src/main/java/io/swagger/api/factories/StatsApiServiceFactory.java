package io.swagger.api.factories;

import io.swagger.api.StatsApiService;
import io.swagger.api.impl.StatsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")public class StatsApiServiceFactory {
    private final static StatsApiService service = new StatsApiServiceImpl();

    public static StatsApiService getStatsApi() {
        return service;
    }
}
