/*
 * <copyright file="NioAjpConnectorProps.java" company="John Anderson">
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
 * <date>Aug 2, 2016</date>
 * <summary></summary>
 */
package tclisten.autoconfigure;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:ja391045@gmail.com">John Anderson</a>
 *
 */
public class NioAjpConnectorProps extends AjpConnectorProps {

	protected AjpProtocol protocol = AjpProtocol.NIO;
	
	protected NioJavaSocketProps socket = null;
	
	protected NioSelectorPoolProps selectorPool = null;
	
	/**
	 * Generic no-args constructor. 
	 */
	public NioAjpConnectorProps() {}

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.AjpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new NioAjpCustomizer(customizer);
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
	 * @return the socket
	 */
	public NioJavaSocketProps getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(NioJavaSocketProps socket) {
		this.socket = socket;
	}

	/**
	 * @return the selectorPool
	 */
	public NioSelectorPoolProps getSelectorPool() {
		return selectorPool;
	}

	/**
	 * @param selectorPool the selectorPool to set
	 */
	public void setSelectorPool(NioSelectorPoolProps selectorPool) {
		this.selectorPool = selectorPool;
	}

	public class NioAjpCustomizer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer;

		public NioAjpCustomizer(TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProtocol(protocol.toString());
			if(socket != null)
				socket.getCustomizer().customize(connector);
			if(selectorPool != null)
				selectorPool.getCustomizer().customize(connector);
		}
	}
}
