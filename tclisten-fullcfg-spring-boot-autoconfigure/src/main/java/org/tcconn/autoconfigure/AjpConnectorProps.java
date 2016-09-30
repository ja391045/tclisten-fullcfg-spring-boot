/*
 * <copyright file="AjpConnectorProps.java" company="CWIE, LLC">
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
public class AjpConnectorProps extends ConnectorProps {
	
	protected Boolean ajpFlush = true;
	
	protected Integer packetSize = 8192;
	
	protected String requiredSecret = null;
	
	protected Boolean tomcatAuthentication = true;
	
	protected Boolean tomcatAuthorization = false;
	
	
	/**
	 * Generic No-Args Constructor. 
	 */
	public AjpConnectorProps() {}


	
	/* (non-Javadoc)
	 * @see org.tcconn.autoconfigure.ConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new AjpConnectorCustomizer(customizer); 
	}



	/**
	 * @return the ajpFlush
	 */
	public Boolean getAjpFlush() {
		return ajpFlush;
	}


	/**
	 * @param ajpFlush the ajpFlush to set
	 */
	public void setAjpFlush(Boolean ajpFlush) {
		this.ajpFlush = ajpFlush;
	}


	/**
	 * @return the packetSize
	 */
	public Integer getPacketSize() {
		return packetSize;
	}


	/**
	 * @param packetSize the packetSize to set
	 */
	public void setPacketSize(Integer packetSize) {
		this.packetSize = packetSize;
	}


	/**
	 * @return the requiredSecret
	 */
	public String getRequiredSecret() {
		return requiredSecret;
	}


	/**
	 * @param requiredSecret the requiredSecret to set
	 */
	public void setRequiredSecret(String requiredSecret) {
		this.requiredSecret = requiredSecret;
	}


	/**
	 * @return the tomcatAuthentication
	 */
	public Boolean getTomcatAuthentication() {
		return tomcatAuthentication;
	}


	/**
	 * @param tomcatAuthentication the tomcatAuthentication to set
	 */
	public void setTomcatAuthentication(Boolean tomcatAuthentication) {
		this.tomcatAuthentication = tomcatAuthentication;
	}


	/**
	 * @return the tomcatAuthorization
	 */
	public Boolean getTomcatAuthorization() {
		return tomcatAuthorization;
	}


	/**
	 * @param tomcatAuthorization the tomcatAuthorization to set
	 */
	public void setTomcatAuthorization(Boolean tomcatAuthorization) {
		this.tomcatAuthorization = tomcatAuthorization;
	}
	
	
	public class AjpConnectorCustomizer implements TomcatConnectorCustomizer {
		
		TomcatConnectorCustomizer superCustomizer = null;
		
		public AjpConnectorCustomizer(TomcatConnectorCustomizer superCustomizer){
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProperty("ajpFlush", String.valueOf(ajpFlush));
			connector.setProperty("packetSize", String.valueOf(packetSize));
			if(requiredSecret != null)
				connector.setProperty("requiredSecret", requiredSecret);
			connector.setProperty("tomcatAuthentication", 
					String.valueOf(tomcatAuthentication));
			connector.setProperty("tomcatAuthorization",
					String.valueOf(tomcatAuthorization ));
			
		}
	}

}
