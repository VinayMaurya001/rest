/*package com.vinay.rest.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	public static final Contact DEFAULT_CONTACT = new Contact("Ranga", "http://www.in28minutes.com",
			"in28minutes@gmail.com");
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation", "Api Documentation", "1.0",
			"urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<VendorExtension>());
	HashSet<String> DEFAULT_CONSUMES_AND_PRODUCES = new HashSet<String>(Arrays.asList("application/json", "application/xml"));

	@Bean
	public LinkDiscoverers discoverers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}

	@Bean
	public Docket api() {
		//return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO);
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.consumes(DEFAULT_CONSUMES_AND_PRODUCES)
				.produces(DEFAULT_CONSUMES_AND_PRODUCES);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("My awesome API").description("My awesome API Description").version("1.0")
				.termsOfServiceUrl("hjbjh").contact(DEFAULT_CONTACT).license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").extensions(new ArrayList<VendorExtension>())
				.build();
	}
}
*/