/*
 * <copyright file="BioAjpConnectorProps.java" company="CWIE, LLC">
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
 * <date>Aug 4, 2016</date>
 * <summary></summary>
 */
package tclisten.autoconfigure;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
public class BioAjpConnectorProps extends AjpConnectorProps {

	protected AjpProtocol protocol = AjpProtocol.BIO;
	
	/**
	 * Generic no-args constructor.
	 */
	public BioAjpConnectorProps() {}

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.AjpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new BioAjpCustomizer(customizer);
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
	
	
	public class BioAjpCustomizer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer = null;

		
		public BioAjpCustomizer(TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}


		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProtocol(protocol.toString());
		}
	}
}
