/*
 * <copyright file="TcPropsAutoConfiguration.java" company="CWIE, LLC">
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
 * <date>Sep 30, 2016</date>
 * <summary></summary>
 */
package tclisten;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tclisten.autoconfigure.TcProps;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
@Configuration
@EnableConfigurationProperties
@ConditionalOnWebApplication
public class TcPropsAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(search=SearchStrategy.CURRENT)
	public TcProps tcProps(){
		return new TcProps();
	}
}
