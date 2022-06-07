package com.rays.p10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rays.p10.common.FrontCtl;

@SpringBootApplication
@EnableWebMvc
public class Project10Application {

	public static void main(String[] args) {
		SpringApplication.run(Project10Application.class, args);
	}
	
	@Autowired
	FrontCtl frontCtl;
	@Bean
	public WebMvcConfigurer corsConfigurer() {
 
		WebMvcConfigurer w = new WebMvcConfigurer() {  //its interface!!!!!!

			/**
			 * Add CORS
			 */
			public void addCorsMappings(CorsRegistry registry) {
				CorsRegistration cors = registry.addMapping("/**");
				cors.allowedOrigins("http://localhost:4200");
				cors.allowedHeaders("*");
				cors.allowCredentials(true);
			}
			
			
			 
			/**
			 * Add Interceptors
			 */
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(frontCtl).addPathPatterns("/**").excludePathPatterns("/Auth/**", "/User/profilePic/**");
			}

			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");
			}


		};
			return w;
		
		}
}
