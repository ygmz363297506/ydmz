
package com.sanss.lyh.web.frame.cfg;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.core.annotation.Order;


@Order(2)
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
//		builder.addDecoratorPath("/*", "/WEB-INF/views/decorator.jsp").addExcludedPath("/errorPage");			
	}
}
