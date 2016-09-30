/*
 * <copyright file="Nio2HttpConnectorProps.java" company="CWIE, LLC">
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

import javax.validation.Valid;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
public class Nio2HttpConnectorProps extends HttpConnectorProps {

	protected HttpProtocol protocol = HttpProtocol.NIO2;
	
	protected Boolean useCaches = false;
	
	protected Boolean useComet = true;
	
	protected Boolean useSendfile = true;
	
	protected Nio2JavaSocketProps socket = null;
	
	protected Integer oomParachute = 1048576;
	
	@Valid
	protected JavaSslProps ssl = null;
	
	/**
	 * Generic no-args constructor.
	 */
	public Nio2HttpConnectorProps() {}

	/* (non-Javadoc)
	 * @see org.tcconn.autoconfigure.HttpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new Nio2HttpCustomer(customizer);
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
	 * @return the useComet
	 */
	public Boolean getUseComet() {
		return useComet;
	}

	/**
	 * @param useComet the useComet to set
	 */
	public void setUseComet(Boolean useComet) {
		this.useComet = useComet;
	}

	/**
	 * @return the useSendfile
	 */
	public Boolean getUseSendfile() {
		return useSendfile;
	}

	/**
	 * @param useSendfile the useSendfile to set
	 */
	public void setUseSendfile(Boolean useSendfile) {
		this.useSendfile = useSendfile;
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

	/**
	 * @return the oomParachute
	 */
	public Integer getOomParachute() {
		return oomParachute;
	}

	/**
	 * @param oomParachute the oomParachute to set
	 */
	public void setOomParachute(Integer oomParachute) {
		this.oomParachute = oomParachute;
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
	
	public class Nio2HttpCustomer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer = null;

		public Nio2HttpCustomer(TomcatConnectorCustomizer superCustomizer) {
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
			connector.setProperty("useComet", String.valueOf(useComet));
			connector.setProperty("useSendfile", String.valueOf(useSendfile));
			connector.setProperty("oomParachute", String.valueOf(oomParachute));
			if(socket != null)
				socket.getCustomizer().customize(connector);
			Object sslEnabled = connector.getProperty("SSLEnabled");
			if(ssl != null && sslEnabled != null && (Boolean) sslEnabled){
				ssl.getCustomizer().customize(connector);
			}
		}
	}
}
