package com.chinaredstar.recruit.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.RelativeSwaggerPathProvider;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.mvc.EndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:杨果
 * @date:16/3/1 上午10:28
 * <p/>
 * Description:
 */
@Configuration
@EnableSwagger
@ComponentScan("com.chinaredstar.recruit.controller")
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        // 不显示Endpoint Mapping
        List<RequestMappingHandlerMapping> mappings =  springSwaggerConfig.swaggerRequestMappingHandlerMappings();
        Iterator<RequestMappingHandlerMapping> iterator = mappings.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof EndpointHandlerMapping) {
                iterator.remove();
            }
        }
        this.springSwaggerConfig = springSwaggerConfig;
    }

    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc framework - allowing for
     * multiple swagger groups i.e. same code base multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*?");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "china red star recruit web api",
                "web project quick start",
                "My Apps API terms of impl",
                "yuxin.zou@chinaredstar.com",
                "My Apps API Licence Type",
                "My Apps API License URL");
        return apiInfo;
    }
}
