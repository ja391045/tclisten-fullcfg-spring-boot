/*
 * <copyright file="NioSelectorPoolProps.java" company="CWIE, LLC">
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
 * <date>Aug 2, 2016</date>
 * <summary></summary>
 */
package org.tcconn.autoconfigure;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
public class NioSelectorPoolProps {
	
	protected Integer maxSelectors = 200;
	
	protected Integer maxSpareSelectors = -1;
	
	/**
	 * Generic no-arg Constructor.
	 */
	public NioSelectorPoolProps() {}

	/**
	 * Get a customzer that sets these properties.
	 * @return The customizer.
	 */
	public TomcatConnectorCustomizer getCustomizer(){
		return new TomcatConnectorCustomizer() {
			
			@Override
			public void customize(Connector connector) {
				connector.setProperty("selectorPool.maxSelectors", String.valueOf(maxSelectors));
				connector.setProperty("selectorPool.maxSpareSelectors",
						String.valueOf(maxSpareSelectors));
			}
		};
	}
	/**
	 * @return the maxSelectors
	 */
	public Integer getMaxSelectors() {
		return maxSelectors;
	}

	/**
	 * @param maxSelectors the maxSelectors to set
	 */
	public void setMaxSelectors(Integer maxSelectors) {
		this.maxSelectors = maxSelectors;
	}

	/**
	 * @return the maxSpareSelectors
	 */
	public Integer getMaxSpareSelectors() {
		return maxSpareSelectors;
	}

	/**
	 * @param maxSpareSelectors the maxSpareSelectors to set
	 */
	public void setMaxSpareSelectors(Integer maxSpareSelectors) {
		this.maxSpareSelectors = maxSpareSelectors;
	}
	
	

}
