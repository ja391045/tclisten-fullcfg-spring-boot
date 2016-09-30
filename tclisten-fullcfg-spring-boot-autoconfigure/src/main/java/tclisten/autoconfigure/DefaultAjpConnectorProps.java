/*
 * <copyright file="DefaultAjpConnectorProps.java" company="CWIE, LLC">
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
 * <date>Aug 12, 2016</date>
 * <summary></summary>
 */
package tclisten.autoconfigure;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 * Generic configuration called up when no other config is provided.
 */
public class DefaultAjpConnectorProps extends AjpConnectorProps {
	
	protected final AjpProtocol protocol = AjpProtocol.DEFAULT;
	
	protected final Integer port = 8009;
	
	protected final Integer redirectPort = 8443;
	
	protected final String address = "0.0.0.0";

	/**
	 *  Generic no-args constructor.
	 */
	public DefaultAjpConnectorProps() {}

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.AjpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		return new TomcatConnectorCustomizer() {
			
			@Override
			public void customize(Connector connector) {
				connector.setProtocol(protocol.toString());
				connector.setPort(port);
				connector.setRedirectPort(redirectPort);
				connector.setProperty("address", address);
			}
		};
	}

	/**
	 * @return the protocol
	 */
	public AjpProtocol getProtocol() {
		return protocol;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @return the redirectPort
	 */
	public Integer getRedirectPort() {
		return redirectPort;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
}
