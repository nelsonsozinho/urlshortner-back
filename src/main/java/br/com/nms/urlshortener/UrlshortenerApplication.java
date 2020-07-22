package br.com.nms.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class UrlshortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlshortenerApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean firstApi(ApplicationContext parent) {
		return createChildContextServlet(parent, "/shortener/api/*", "api");
	}

	@Bean
	public ServletRegistrationBean secondApi(ApplicationContext parent) {
		return createChildContextServlet(parent, "/shortener/*", "path");
	}

	private ServletRegistrationBean createChildContextServlet(ApplicationContext parent, String path, String name) {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setParent(parent);
		ctx.register(PropertyPlaceholderAutoConfiguration.class, DispatcherServletAutoConfiguration.class);

		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, path);
		servletRegistrationBean.setName(name);

		return servletRegistrationBean;
	}

}
