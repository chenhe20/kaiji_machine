package kcl.ac.uk.kaiji_machine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    UserPermissionInterceptor userPermissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(userPermissionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/task-mgt/all")
                .excludePathPatterns("/info-query/**")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/blog/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }



}
