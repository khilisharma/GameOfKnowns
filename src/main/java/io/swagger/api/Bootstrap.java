package io.swagger.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
        title = "Swagger Server",
        version = "1.0.0",
        description = "This API lets users play the trivia game called Game Of Knowns",
        termsOfService = "",
        contact = @Contact(email = "khili22.sharma@gmail.com"),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"
        )
    )
)
public class Bootstrap {

}
