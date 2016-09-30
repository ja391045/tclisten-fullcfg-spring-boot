package tclisten.autoconfigure;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/*
 * <copyright file="NioHttpConnectorProps.java" company="CWIE, LLC">
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

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
public class NioHttpConnectorProps extends HttpConnectorProps {
	
	protected HttpProtocol protocol = HttpProtocol.NIO;
	
	protected Integer pollerThreadCount = 1;
	
	@Size(min=Thread.MIN_PRIORITY, max=Thread.MAX_PRIORITY)
	protected Integer pollerThreadPriority = Thread.NORM_PRIORITY;
	
	protected Integer selectorTimeout = 1000;
	
	protected Boolean useComet = true;
	
	protected Boolean useSendfile = true;
	
	protected NioJavaSocketProps socket = null;
	
	protected NioSelectorPoolProps selectorPool = null;
	
	@Valid
	protected JavaSslProps ssl = null;
	
	
	/**
	 * Generic no-arg constructor.
	 */
	public NioHttpConnectorProps() {}


	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.HttpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new NioHttpCustomizer(customizer);
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
	 * @return the pollerThreadCount
	 */
	public Integer getPollerThreadCount() {
		return pollerThreadCount;
	}


	/**
	 * @param pollerThreadCount the pollerThreadCount to set
	 */
	public void setPollerThreadCount(Integer pollerThreadCount) {
		this.pollerThreadCount = pollerThreadCount;
	}


	/**
	 * @return the pollerThreadPriority
	 */
	public Integer getPollerThreadPriority() {
		return pollerThreadPriority;
	}


	/**
	 * @param pollerThreadPriority the pollerThreadPriority to set
	 */
	public void setPollerThreadPriority(Integer pollerThreadPriority) {
		this.pollerThreadPriority = pollerThreadPriority;
	}


	/**
	 * @return the selectorTimeout
	 */
	public Integer getSelectorTimeout() {
		return selectorTimeout;
	}


	/**
	 * @param selectorTimeout the selectorTimeout to set
	 */
	public void setSelectorTimeout(Integer selectorTimeout) {
		this.selectorTimeout = selectorTimeout;
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
	
	public class NioHttpCustomizer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer = null;

		public NioHttpCustomizer(TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProtocol(protocol.toString());
			connector.setProperty("pollerThreadCount", String.valueOf(pollerThreadCount));
			connector.setProperty("pollerThreadPriority", String.valueOf(pollerThreadPriority));
			connector.setProperty("selectorTimeout", String.valueOf(selectorTimeout));
			connector.setProperty("useComet", String.valueOf(useComet));
			connector.setProperty("useSendfile", String.valueOf(useSendfile));
			if(socket != null)
				socket.getCustomizer().customize(connector);
			if(selectorPool != null)
				selectorPool.getCustomizer().customize(connector);				
			Object sslEnabled = connector.getProperty("SSLEnabled");
			if(ssl != null && sslEnabled != null && (Boolean) sslEnabled){
				ssl.getCustomizer().customize(connector);
			}
		}
	}
}
