package es.api.odontoweb.Customers.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

    @OpenAPIDefinition(
            info = @Info(
                    title = "OdontoWeb Customers API",
                    description = "Microservicio CRUD de pacientes para OdontoWeb",
                    contact = @Contact(
                            name = "Gustavo Armitano",
                            url = "http://esculeait.com",
                            email = "garmitano@gmail.com"
                    ),
                    license = @License(
                            name = "MIT Licence",
                            url = "https://github.com/thombergs/code-examples/blob/master/LICENSE")),
            servers = @Server(url = "http://localhost:8080")
    )
    public class SwaggerConfiguration {

    }
