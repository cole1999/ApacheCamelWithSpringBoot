package org.cole.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component
public class Endpoint extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/cole")
                .get("/helloWorld")
                .outType(String.class)
                .produces("text/plain")
                .param()
                .name("name")
                .type(RestParamType.query)
                .endParam()
                .to("direct:helloWorld");

        from("direct:helloWorld")
                .setBody(simple("Hello world, ${header.name}!"));
    }

}