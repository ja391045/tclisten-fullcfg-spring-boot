/*
 * <copyright file="Nio2AjpConnectorProps.java" company="CWIE, LLC">
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
public class Nio2AjpConnectorProps extends AjpConnectorProps {
	
	protected AjpProtocol protocol = AjpProtocol.NIO2; 
	
	protected Boolean useCaches = false;
	
	protected Nio2JavaSocketProps socket = null;

	/**
	 * Generic no-args constructor.
	 */
	public Nio2AjpConnectorProps() {}

	/* (non-Javadoc)
	 * @see org.tcconn.autoconfigure.AjpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new Nio2AjpCustomizer(customizer);
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
	 * @return the useCaches
	 */
	public Boolean getUseCaches() {
		return useCaches;
	}

	/**
	 * @param useCaches the useCaches to set
	 */
	public void setUseCaches(Boolean useCaches) {
		this.useCaches = useCaches;
	}

	/**
	 * @return the socket
	 */
	public Nio2JavaSocketProps getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Nio2JavaSocketProps socket) {
		this.socket = socket;
	}
	
	public class Nio2AjpCustomizer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer = null;

		public Nio2AjpCustomizer(TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProtocol(protocol.toString());
			connector.setProperty("useCaches", String.valueOf(useCaches));
			if(socket != null)
				socket.getCustomizer().customize(connector);
		}
		
		
	}
}
