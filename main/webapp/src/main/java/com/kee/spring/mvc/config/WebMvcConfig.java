package com.kee.spring.mvc.config;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wfs.spring.mvc.json.FastJsonArgumentResolver;

/**
 * 
 * @author qindebu
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableScheduling
@ComponentScan(basePackages = { "com.wfs.engine" })
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(300*1000L);
	}

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJacksonHttpMessageConverter());
		converters.add(new FastJsonHttpMessageConverter());
		super.configureMessageConverters(converters);
	}

//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("chat");
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");  
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");  
        registry.addResourceHandler("/js/**").addResourceLocations("/js/"); 
        registry.addResourceHandler("/common/**").addResourceLocations("/common/");
		registry.addResourceHandler("/resource/**").addResourceLocations("/resource/");
		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		FastJsonArgumentResolver jsonResolver = new FastJsonArgumentResolver();
		argumentResolvers.add(jsonResolver);
		super.addArgumentResolvers(argumentResolvers);
	}
//
//	@Bean
//	public ViewResolver viewResolver() {
//		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//		resolver.setTemplateEngine(templateEngine());
//		return resolver;
//	}
//
//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.setTemplateResolver(templateResolver());
//		return engine;
//	}

//	
//	@Bean
//	public InternalResourceViewResolver coinfigureInternalResourceViewResolver(){
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/jsp");  
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}
//
//
//	@Bean
//	public TemplateResolver templateResolver() {
//		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
//		resolver.setPrefix("/WEB-INF/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode("HTML5");
//		resolver.setCacheable(false);
//		return resolver;
//	}

}
