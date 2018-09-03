package com.ducnd.chattfunn.common.config;

import com.ducnd.chattfunn.common.Constants;
import com.ducnd.chattfunn.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    private ConverterRegistry converterRegistry;


    @PostConstruct
    public void init() {
        converterRegistry.addConverter(new StringToLocalDateTimeConverter());
        converterRegistry.addConverter(new StringToLocalDateConverter());
    }

    @Bean
    public Docket api() {

        List<Parameter> parameterBuilders = new ArrayList<>();
        parameterBuilders.add(
                new ParameterBuilder()
                        .name(Constants.HEADER_NAME_AUTH)
                        .description("Description of header")
                        .modelRef(new ModelRef("string"))
                        .parameterType("header")
                        .required(false)
                        .build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ducnd.chattfunn.controller")).paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .globalOperationParameters(parameterBuilders);

    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(new PageRequest(0, Constants.PAGE_SIZE_DEFAULT));
        resolver.setOneIndexedParameters(true);
        argumentResolvers.add(resolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**");
        registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index.html");
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(CommonUtils.converterJack());
        super.configureMessageConverters(converters);
    }
}
