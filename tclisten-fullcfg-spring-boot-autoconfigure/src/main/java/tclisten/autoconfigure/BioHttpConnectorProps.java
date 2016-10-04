/*
 * <copyright file="BioHttpConnectorProps.java" company="John Anderson">
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
 * <date>Oct 3, 2016</date>
 * <summary></summary>
 */
package tclisten.autoconfigure;

import javax.validation.Valid;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:ja391045@gmail.com">John Anderson</a>
 *
 */
public class BioHttpConnectorProps extends HttpConnectorProps {

	protected HttpProtocol protocol = HttpProtocol.BIO;
	
	protected Integer disableKeepAlivePercentage = 75;
	
	@Valid
	protected JavaSslProps ssl = null;
	
	/**
	 * Generic no-arg constructor.
	 */
	public BioHttpConnectorProps() {}

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.HttpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new BioHttpCustomizer(customizer);
	}

	/**
	 * @return the protocol
	 */
	public HttpProtocol getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(HttpProtocol protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the disableKeepAlivePercentage
	 */
	public Integer getDisableKeepAlivePercentage() {
		return disableKeepAlivePercentage;
	}

	/**
	 * @param disableKeepAlivePercentage the disableKeepAlivePercentage to set
	 */
	public void setDisableKeepAlivePercentage(Integer disableKeepAlivePercentage) {
		this.disableKeepAlivePercentage = disableKeepAlivePercentage;
	}

	/**
	 * @return the ssl
	 */
	public JavaSslProps getSsl() {
		return ssl;
	}

	/**
	 * @param ssl the ssl to set
	 */
	public void setSsl(JavaSslProps ssl) {
		this.ssl = ssl;
	}
	
	
	public class BioHttpCustomizer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer = null;

		public BioHttpCustomizer(TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProtocol(protocol.toString());
			connector.setProperty("disableKeepAlivePercentage", String.valueOf(disableKeepAlivePercentage));
			Object sslEnabled = connector.getProperty("SSLEnabled");
			if(ssl != null && sslEnabled != null && (Boolean) sslEnabled){
				ssl.getCustomizer().customize(connector);
			}
		}
	}
}
