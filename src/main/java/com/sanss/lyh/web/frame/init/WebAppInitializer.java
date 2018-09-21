package com.sanss.lyh.web.frame.init;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.sanss.lyh.web.frame.cfg.AppConfig;
import com.sanss.lyh.web.frame.cfg.MvcConfig;
import com.sanss.lyh.web.frame.cfg.SiteMeshFilter;


public class WebAppInitializer implements WebApplicationInitializer{
	@Override
    public void onStartup(final ServletContext sc) throws ServletException {
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD,
				DispatcherType.INCLUDE);
        AnnotationConfigWebApplicationContext  rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfig.class, MvcConfig.class);
        sc.addListener(new ContextLoaderListener(rootContext));

        DispatcherServlet dispatcherServlet = new DispatcherServlet(rootContext);

        ServletRegistration.Dynamic dynamic = sc.addServlet("dispatcherServlet", dispatcherServlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");      
        
        SiteMeshFilter siteMeshFilter=new SiteMeshFilter();
        FilterRegistration.Dynamic filter= sc.addFilter("siteMeshFilter", siteMeshFilter);
        filter.addMappingForServletNames(dispatcherTypes, true, "dispatcherServlet");
    }
}
