package webSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan( basePackageClasses = WebConfig.class )
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private TimeBasedAccessInterceptor timeBasedAccessInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( "/resources/**" )
            .addResourceLocations( "/resources/" );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //with @Bean here
        registry.addInterceptor(clientLoggerHandlerInterceptor());
        //with @Configuration on the class
        registry.addInterceptor(timeBasedAccessInterceptor);
    }

    @Bean
    public ViewResolver viewResolver(){
        ThymeleafViewResolver thViewResolver = new ThymeleafViewResolver();
        thViewResolver.setTemplateEngine( springTemplateEngine() );
        thViewResolver.setCharacterEncoding( "UTF-8" );
        return thViewResolver;
    }

    @Bean
    public SpringTemplateEngine springTemplateEngine(){
        SpringTemplateEngine spTempEngine = new SpringTemplateEngine();
        spTempEngine.setTemplateResolver( templateResolver() );
        return spTempEngine;
    }

    @Bean
    public ITemplateResolver templateResolver(){
        SpringResourceTemplateResolver spTempRes = new SpringResourceTemplateResolver();
        spTempRes.setApplicationContext( appContext );
        spTempRes.setPrefix( "/WEB-INF/templates/" );
        spTempRes.setSuffix( ".html" );
        spTempRes.setTemplateMode( "HTML5" );
        return spTempRes;
    }

    @Bean
    public ClientLoggerHandlerInterceptor clientLoggerHandlerInterceptor(){
        return new ClientLoggerHandlerInterceptor();
    }

}
