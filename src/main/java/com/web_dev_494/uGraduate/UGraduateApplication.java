package com.web_dev_494.uGraduate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import java.io.File;

@SpringBootApplication
public class UGraduateApplication extends SpringBootServletInitializer {

	// This is needed to deploy on AWS. Please don't touch this
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UGraduateApplication.class);
	}

	// Initializes Spring Boot. Also, don't touch this
	public static void main(String[] args) {
		SpringApplication.run(UGraduateApplication.class, args);

	}

}

@Component
class MyTomcatWebServerCustomizer
		implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				connector.setAttribute("relaxedPathChars", "<>[\\]^`{|}");
				connector.setAttribute("relaxedQueryChars", "<>[\\]^`{|}");
			}
		});
	}
}