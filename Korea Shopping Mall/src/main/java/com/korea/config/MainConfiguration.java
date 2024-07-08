package com.korea.config;

import com.korea.converter.FileConverter;
import com.korea.converter.FilesConverter;
import com.korea.converter.ImageFileConverter;
import com.korea.interceptor.CategoryInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MainConfiguration implements WebMvcConfigurer {
    @Autowired private CategoryInterceptor categoryInterceptor;
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/myimage/**")
//                .addResourceLocations("file:C:\\Users\\Administrator\\Pictures\\Saved Pictures");
//    }
    @Bean
    public RestOperations restOpreations() {
        return new RestTemplate();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(categoryInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("classpath:/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new FileConverter());
        registry.addConverter(new FilesConverter());
        registry.addConverter(new ImageFileConverter());
    }
}
