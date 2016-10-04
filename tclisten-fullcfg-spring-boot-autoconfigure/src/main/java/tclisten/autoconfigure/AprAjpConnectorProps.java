/*
 * <copyright file="AprAjpConnectorProps.java" company="John Anderson">
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
 * <date>Aug 4, 2016</date>
 * <summary></summary>
 */
package tclisten.autoconfigure;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:ja391045@gmail.com">John Anderson</a>
 *
 */
public class AprAjpConnectorProps extends AjpConnectorProps {

	protected AjpProtocol protocol = AjpProtocol.APR;
	
	protected Long pollTime = 2000L;
	
	protected Long pollerSize = 8192L;
	
	/**
	 * Generic no-args constructor. 
	 */
	public AprAjpConnectorProps() {}
	
	

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.AjpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new AprAjpCustomizer(customizer);
	}



	/**
	 * @return the protocol
	 */
	public AjpProtocol getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(AjpProtocol protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the pollTime
	 */
	public Long getPollTime() {
		return pollTime;
	}

	/**
	 * @param pollTime the pollTime to set
	 */
	public void setPollTime(Long pollTime) {
		this.pollTime = pollTime;
	}

	/**
	 * @return the pollerSize
	 */
	public Long getPollerSize() {
		return pollerSize;
	}

	/**
	 * @param pollerSize the pollerSize to set
	 */
	public void setPollerSize(Long pollerSize) {
		this.pollerSize = pollerSize;
	}

	public class AprAjpCustomizer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer = null;

		public AprAjpCustomizer(TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProtocol(protocol.toString());
			connector.setProperty("pollTime", String.valueOf(pollTime));
			connector.setProperty("pollerSize", String.valueOf(pollerSize));
		}
	}
}
