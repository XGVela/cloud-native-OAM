package org.xgvela.oam.config;

import com.google.common.collect.Lists;
import org.xgvela.oam.utils.WebUtils;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.name(WebUtils.HEADER_NAME_AUTH).description("OAM权限验证token").modelRef(new ModelRef("string")).parameterType("header").required(true).defaultValue(WebUtils.HEADER_VALUE_AUTH).build();
        List<Parameter> parameters = Lists.newArrayList();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //.apis(RequestHandlerSelectors.basePackage("org.xgvela.cnet.mepm.web,org.xgvela.cnet.rest.security"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OAM核心网api")
//                .description("更多Spring Boot相关文章请关注：http://test/")
//                .termsOfServiceUrl("http://test/")
                .version("Application Version: 1.0-SNAPSHOT, Spring Boot Version: " + SpringBootVersion.getVersion())
                .build();
    }
}
