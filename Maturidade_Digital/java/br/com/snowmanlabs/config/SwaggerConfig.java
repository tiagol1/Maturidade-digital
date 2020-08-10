package br.com.snowmanlabs.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableWebSecurity
@EnableSwagger2
public class SwaggerConfig{
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.snowmanlabs"))
                .paths(regex("/dmd.*"))
                .build()
                .apiInfo(metaInfo());
    }
  
    private ApiInfo metaInfo() {
        @SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
                "Maturidade Digital",
                "Diagnóstico de maturidade.",
                "1.0",
                "Terms of Service",
                new Contact("Snowman Labs.", "https://www.snowmanlabs.com.br",""),"","", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }
    
                

            // Autenticação do Swagger via spring security
    
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//    .antMatchers("/swagger-resources/*",  "/dmd/*", "*.html", "/dmd/swagger.json")
//        .hasRole("SWAGGER")
//    .anyRequest()
//        .authenticated()
//            .and()
//                .httpBasic()
//            .and()
//                .csrf().disable();
//    }
//
//    @Autowired
//    
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("snow").password("{noop}2h9r7t").authorities("SWAGGER");
//    }
//    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
    
}

