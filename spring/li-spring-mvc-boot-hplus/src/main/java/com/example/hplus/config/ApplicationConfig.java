package com.example.hplus.config;

import com.example.hplus.convertors.StringToEnumConvertor;
import com.example.hplus.interceptors.LoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;


@Configuration
@ComponentScan(basePackages = "com.test.hplus")
public class ApplicationConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("css/**", "images/**")
                .addResourceLocations("classpath:/static/css/", "classpath:/static/images/");
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(2);
        return viewResolver;
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConvertor());
    }

    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(mvcTaskExecutor());
    }

    @Bean
    public AsyncTaskExecutor mvcTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("hplusapp-thread-");
        return threadPoolTaskExecutor;
    }

    @Bean
    public XmlViewResolver xmlViewResolver(){
        XmlViewResolver viewResolver = new XmlViewResolver();
        viewResolver.setLocation(new ClassPathResource("views.xml"));
        viewResolver.setOrder(1);
        return viewResolver;
    }

    /*@Bean
    public ResourceBundleViewResolver resourceBundleViewResolver(){
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("views");
        return viewResolver;
    }*/

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/*");
    }
}
