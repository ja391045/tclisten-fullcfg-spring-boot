package tclisten.autoconfigure;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/*
 * <copyright file="SslProps.java" company="John Anderson">
 * Copyright (c) 2016 John Anderson
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
 * <email>ja391045@gmail.com</email>
 * <date>Aug 10, 2016</date>
 * <summary></summary>
 */

/**
 * @author <a href="mailto:ja391045@gmail.com">John Anderson</a>
 * Define a customizer returning method.
 */
public interface SslProps {

	/**
	 * Get an SSL configuration customizer.
	 * @return The customizer.
	 */
	public TomcatConnectorCustomizer getCustomizer();
}
