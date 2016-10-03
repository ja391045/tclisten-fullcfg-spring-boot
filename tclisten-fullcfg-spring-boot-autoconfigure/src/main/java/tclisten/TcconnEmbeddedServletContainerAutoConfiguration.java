/*
 * <copyright file="TcconnEmbeddedServletContainerAutoConfiguration.java" company="CWIE, LLC">
 * Copyright (c) 2016 CWIE, LLC
 * 
 * This source is subject to the [] License.  Please see License.txt file
 * for more information.  All other rights reserved.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF
 * ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A 
 * PARTICULAR PURPOSE.
 * </copyright>
 * <author>John Anderson</author>
 * <email>johnha@ccbill.com</email>
 * <date>Sep 29, 2016</date>
 * <summary></summary>
 */
package tclisten;

import javax.servlet.Servlet;

import org.apache.catalina.startup.Tomcat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import tclisten.autoconfigure.TcProps;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 * Top entry point where tcconn takes over in place of 
 * <a href="http://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/web/EmbeddedServletContainerAutoConfiguration.html">
 * EmbeddedServletContainerAutoConfiguration</a>.
 */

@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@AutoConfigureBefore(EmbeddedServletContainerAutoConfiguration.class)
@ConditionalOnClass({Servlet.class, Tomcat.class})
@ConditionalOnWebApplication
public class TcconnEmbeddedServletContainerAutoConfiguration implements ApplicationContextAware {

	private static final Log log = LogFactory.getLog(TcconnEmbeddedServletContainerAutoConfiguration.class);
	
	private ApplicationContext ctx;
	
	/**
	 * Spring constructor.
	 */
	public TcconnEmbeddedServletContainerAutoConfiguration() {
		log.debug("TcConn AutoConfiguration Started.");
	}
	
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory(){
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		TcProps tcProps = ctx.getBean(TcProps.class);
		log.info("Applying configuration to Embedded Tomcat Factory.");
		TomcatEmbeddedServletContainerFactoryConfigurer tcConfig = 
				new TomcatEmbeddedServletContainerFactoryConfigurer(factory, tcProps);
		tcConfig.configureConnectors();
		return factory;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;
	}
}
