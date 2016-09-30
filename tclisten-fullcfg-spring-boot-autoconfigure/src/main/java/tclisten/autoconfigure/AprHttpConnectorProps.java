/*
 * <copyright file="AprHttpConnectorProps.java" company="CWIE, LLC">
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

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
public class AprHttpConnectorProps extends HttpConnectorProps {
	
	protected HttpProtocol protocol = HttpProtocol.APR;
	
	protected Boolean deferAccept = true;
	
	protected Integer pollerSize = 8192;
	
	protected Integer pollerThreadCount = null;
	
	protected Long pollTime = 2000L;
	
	protected Long sendfileSize = 1024L;
	
	protected Integer sendfileThreadCount = null;
	
	@Size(min=Thread.MIN_PRIORITY, max=Thread.MAX_PRIORITY)
	protected Integer threadPriority = Thread.NORM_PRIORITY;
	
	protected Boolean useComet = true;
	
	protected Boolean useSendfile = true;
	
	protected Integer maxConnections = 8192;
	
	@Valid
	protected AprSslProps ssl = null;

	/**
	 * Generic no-arg constructor.
	 */
	public AprHttpConnectorProps() {}
	
	
	

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.HttpConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new AprHttpCustomizer(customizer);
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
	 * @return the deferAccept
	 */
	public Boolean getDeferAccept() {
		return deferAccept;
	}

	/**
	 * @param deferAccept the deferAccept to set
	 */
	public void setDeferAccept(Boolean deferAccept) {
		this.deferAccept = deferAccept;
	}

	/**
	 * @return the pollerSize
	 */
	public Integer getPollerSize() {
		return pollerSize;
	}

	/**
	 * @param pollerSize the pollerSize to set
	 */
	public void setPollerSize(Integer pollerSize) {
		this.pollerSize = pollerSize;
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
	 * @return the sendfileSize
	 */
	public Long getSendfileSize() {
		return sendfileSize;
	}

	/**
	 * @param sendfileSize the sendfileSize to set
	 */
	public void setSendfileSize(Long sendfileSize) {
		this.sendfileSize = sendfileSize;
	}

	/**
	 * @return the sendfileThreadCount
	 */
	public Integer getSendfileThreadCount() {
		return sendfileThreadCount;
	}

	/**
	 * @param sendfileThreadCount the sendfileThreadCount to set
	 */
	public void setSendfileThreadCount(Integer sendfileThreadCount) {
		this.sendfileThreadCount = sendfileThreadCount;
	}

	/**
	 * @return the threadPriority
	 */
	public Integer getThreadPriority() {
		return threadPriority;
	}

	/**
	 * @param threadPriority the threadPriority to set
	 */
	public void setThreadPriority(Integer threadPriority) {
		this.threadPriority = threadPriority;
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
	 * @return the ssl
	 */
	public AprSslProps getSsl() {
		return ssl;
	}

	/**
	 * @param ssl the ssl to set
	 */
	public void setSsl(AprSslProps ssl) {
		this.ssl = ssl;
	}

	/**
	 * @return the maxConnections
	 */
	public Integer getMaxConnections() {
		return maxConnections;
	}

	/**
	 * @param maxConnections the maxConnections to set
	 */
	public void setMaxConnections(Integer maxConnections) {
		this.maxConnections = maxConnections;
	}
	
	public class AprHttpCustomizer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer = null;

		public AprHttpCustomizer(TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProtocol(protocol.toString());
			connector.setProperty("deferAccept", String.valueOf(deferAccept));
			connector.setProperty("pollerSize", String.valueOf(pollerSize));
			if(pollerThreadCount != null)
				connector.setProperty("pollerThreadCount", String.valueOf(pollerThreadCount));
			connector.setProperty("pollTime",String.valueOf(pollTime));
			connector.setProperty("sendfileSize", String.valueOf(sendfileSize));
			if(sendfileThreadCount != null)
				connector.setProperty("sendfileThreadCount", String.valueOf(sendfileThreadCount));
			connector.setProperty("threadPriority", String.valueOf(threadPriority));
			connector.setProperty("useComet", String.valueOf(useComet));
			connector.setProperty("useSendfile", String.valueOf(useSendfile));
			connector.setProperty("maxConnections", String.valueOf(maxConnections));
			Object sslEnabled = connector.getProperty("SSLEnabled");
			if(ssl != null && sslEnabled != null && (Boolean) sslEnabled){
				ssl.getCustomizer().customize(connector);
			}
		}
	}
}
