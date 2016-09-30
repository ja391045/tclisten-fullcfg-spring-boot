/*
 * <copyright file="ConnectorConfig.java" company="CWIE, LLC">
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
 * <date>Aug 1, 2016</date>
 * <summary></summary>
 */
package tclisten.autoconfigure;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;


/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 * 
 * Configuration item for "connectors" in their most generic sense.  Some properties are common to all
 * Tomcat connectors, while others are only available to specific types of connectors.  This is a catch-all
 * for the common properties.
 * 
 * For a look at all properties which can be assigned to Tomcat connectors @see 
 * <a href="https://tomcat.apache.org/tomcat-8.0-doc/config/http.html#Attributes">
 * Apache Tomcat 8 Configuration Reference, Attributes
 * </a>
 */

public class ConnectorProps {
	
	protected Boolean allowTrace = false;
	
	@Size(min=0, max=Integer.MAX_VALUE)
	protected Long asyncTimeout = 30000L;
	
	protected Boolean enableLookups = false;
	
	protected Integer maxHeaderCount = 100;
	
	protected Integer maxParameterCount = 10000;
	
	protected Integer maxPostSize = 2097142;
	
	protected Integer maxSavePostSize = 4096;
	
	@Pattern(regexp = "POST|PUT|TRACE|HEAD|DELETE|GET")
	protected String[] parseBodyMethods = {"POST"};
	
	@Size(min=0, max=65535)
	protected Integer port = 0;
	
	protected String proxyName = null;
	
	protected Integer proxyPort = null;
	
	protected Integer redirectPort = null;
	
	@Pattern(regexp = "http|https")
	protected String scheme = "http";
	
	protected Boolean secure = false;
	
	protected String uriEncoding = null;
	
	protected Boolean useBodyEncodingForURI = false;
	
	protected Boolean useIPVHosts = false;
	
	protected Boolean xpoweredBy = false;
	
	@Min(1)
	protected Integer acceptCount = 100;
	
	@Size(min=1, max=2)
	protected Integer acceptorThreadCount = 1;
	
	@Size(min=Thread.MIN_PRIORITY, max=Thread.MAX_PRIORITY)
	protected Integer acceptorThreadPriority = Thread.NORM_PRIORITY;
	
	protected Boolean bindOnInit = false;
	
	@Min(-1)
	protected Long connectionLinger = -1L;
	
	@Min(-1)
	protected Long connectionTimeout = -1L;
	
	protected String executor = null;
	
	protected Long executorTerminationTimeoutMillis = 5000L;
	
	protected Long keepAliveTimeout = null;
	
	/* Default for NIO/BIO, overridden in APR/Native */
	protected Integer maxConnections = 10000;
	
	protected Integer maxThreads = 200;
	
	protected Integer minSpareThreads = 10;
	
	protected Integer processorCache = 200;
	
	protected Boolean tcpNoDelay = true;
	
	@Size(min=Thread.MIN_PRIORITY, max=Thread.MAX_PRIORITY)
	protected Integer threadPriority = Thread.NORM_PRIORITY;
	
	/* TODO: Figure out how to validate this for IPv4/6 */
	protected String address = null;
	
	private String join(char d, String[] txt){
		StringBuilder out = new StringBuilder();
		for(int i = 0; i < txt.length; i++){
			out.append(txt[i]);
			if(i + 1 < txt.length)
				out.append(d);
		}
		return out.toString();
	}

	/**
	 * Generic no-args constructor.
	 */
	public ConnectorProps() {}

	public TomcatConnectorCustomizer getCustomizer() {
		return new TomcatConnectorCustomizer() {
			
			@Override
			public void customize(Connector connector) {
				connector.setScheme(scheme);
				connector.setAllowTrace(allowTrace);
				connector.setAsyncTimeout(asyncTimeout);
				connector.setEnableLookups(enableLookups);
				connector.setMaxHeaderCount(maxHeaderCount);
				connector.setMaxParameterCount(maxParameterCount);
				connector.setMaxPostSize(maxPostSize);
				connector.setMaxSavePostSize(maxSavePostSize);
				connector.setParseBodyMethods(join(',', parseBodyMethods));
				connector.setPort(port);
				if(proxyName != null)
					connector.setProxyName(proxyName);
				if(proxyPort != null)
					connector.setProxyPort(proxyPort);
				if(redirectPort != null)
					connector.setRedirectPort(redirectPort);
				connector.setScheme(scheme);
				connector.setSecure(secure);
				if(uriEncoding != null)
					connector.setURIEncoding(uriEncoding);
				connector.setUseBodyEncodingForURI(useBodyEncodingForURI);
				connector.setUseIPVHosts(useIPVHosts);
				connector.setXpoweredBy(xpoweredBy);
				connector.setProperty("acceptCount", String.valueOf(acceptCount));
				connector.setProperty("acceptorThreadCount", String.valueOf(acceptorThreadCount));
				connector.setProperty("acceptorThreadPriority", String.valueOf(acceptorThreadPriority));
				connector.setProperty("bindOnInit", String.valueOf(bindOnInit));
				connector.setProperty("connectionLinger", String.valueOf(connectionLinger));
				connector.setProperty("connectionTimeout", String.valueOf(connectionTimeout));
				if(executor != null)
					connector.setProperty("executor", executor);
				if(executorTerminationTimeoutMillis != null)
					connector.setProperty("executorTerminationTimeoutMillis"
							, String.valueOf(executorTerminationTimeoutMillis));
				if(keepAliveTimeout != null)
					connector.setProperty("keepAliveTimeout", String.valueOf(keepAliveTimeout));
				connector.setProperty("maxConnections", String.valueOf(maxConnections));
				connector.setProperty("maxThreads", String.valueOf(maxThreads));
				connector.setProperty("minSpareThreads", String.valueOf(minSpareThreads));
				connector.setProperty("processorCache", String.valueOf(processorCache));
				connector.setProperty("tcpNoDelay", String.valueOf(tcpNoDelay));
				connector.setProperty("threadPriority", String.valueOf(threadPriority));
				connector.setProperty("address", address);
			}
		};
	}
	/**
	 * @return the allowTrace
	 */
	public Boolean getAllowTrace() {
		return allowTrace;
	}

	/**
	 * @param allowTrace the allowTrace to set
	 */
	public void setAllowTrace(Boolean allowTrace) {
		this.allowTrace = allowTrace;
	}

	/**
	 * @return the asyncTimeout
	 */
	public Long getAsyncTimeout() {
		return asyncTimeout;
	}

	/**
	 * @param asyncTimeout the asyncTimeout to set
	 */
	public void setAsyncTimeout(Long asyncTimeout) {
		this.asyncTimeout = asyncTimeout;
	}

	/**
	 * @return the enableLookups
	 */
	public Boolean getEnableLookups() {
		return enableLookups;
	}

	/**
	 * @param enableLookups the enableLookups to set
	 */
	public void setEnableLookups(Boolean enableLookups) {
		this.enableLookups = enableLookups;
	}

	/**
	 * @return the maxHeaderCount
	 */
	public Integer getMaxHeaderCount() {
		return maxHeaderCount;
	}

	/**
	 * @param maxHeaderCount the maxHeaderCount to set
	 */
	public void setMaxHeaderCount(Integer maxHeaderCount) {
		this.maxHeaderCount = maxHeaderCount;
	}

	/**
	 * @return the maxParameterCount
	 */
	public Integer getMaxParameterCount() {
		return maxParameterCount;
	}

	/**
	 * @param maxParameterCount the maxParameterCount to set
	 */
	public void setMaxParameterCount(Integer maxParameterCount) {
		this.maxParameterCount = maxParameterCount;
	}

	/**
	 * @return the maxPostSize
	 */
	public Integer getMaxPostSize() {
		return maxPostSize;
	}

	/**
	 * @param maxPostSize the maxPostSize to set
	 */
	public void setMaxPostSize(Integer maxPostSize) {
		this.maxPostSize = maxPostSize;
	}

	/**
	 * @return the maxSavePostSize
	 */
	public Integer getMaxSavePostSize() {
		return maxSavePostSize;
	}

	/**
	 * @param maxSavePostSize the maxSavePostSize to set
	 */
	public void setMaxSavePostSize(Integer maxSavePostSize) {
		this.maxSavePostSize = maxSavePostSize;
	}

	/**
	 * @return the parseBodyMethods
	 */
	public String[] getParseBodyMethods() {
		return parseBodyMethods;
	}
	
	/**
	 * 
	 * @return The parseBodyMethods as comma delimited values.
	 */
	public String getParseBodyMethodsCsv(){
		return this.join(',', parseBodyMethods);
	}

	/**
	 * @param parseBodyMethods the parseBodyMethods to set
	 */
	public void setParseBodyMethods(String[] parseBodyMethods) {
		this.parseBodyMethods = parseBodyMethods;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the proxyName
	 */
	public String getProxyName() {
		return proxyName;
	}

	/**
	 * @param proxyName the proxyName to set
	 */
	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}

	/**
	 * @return the proxyPort
	 */
	public Integer getProxyPort() {
		return proxyPort;
	}

	/**
	 * @param proxyPort the proxyPort to set
	 */
	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * @return the redirectPort
	 */
	public Integer getRedirectPort() {
		return redirectPort;
	}

	/**
	 * @param redirectPort the redirectPort to set
	 */
	public void setRedirectPort(Integer redirectPort) {
		this.redirectPort = redirectPort;
	}

	/**
	 * @return the scheme
	 */
	public String getScheme() {
		return scheme;
	}

	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * @return the secure
	 */
	public Boolean getSecure() {
		return secure;
	}

	/**
	 * @param secure the secure to set
	 */
	public void setSecure(Boolean secure) {
		this.secure = secure;
	}

	/**
	 * @return the uriEncoding
	 */
	public String getUriEncoding() {
		return uriEncoding;
	}

	/**
	 * @param uriEncoding the uriEncoding to set
	 */
	public void setUriEncoding(String uriEncoding) {
		this.uriEncoding = uriEncoding;
	}

	/**
	 * @return the useBodyEncodingForURI
	 */
	public Boolean getUseBodyEncodingForURI() {
		return useBodyEncodingForURI;
	}

	/**
	 * @param useBodyEncodingForURI the useBodyEncodingForURI to set
	 */
	public void setUseBodyEncodingForURI(Boolean useBodyEncodingForURI) {
		this.useBodyEncodingForURI = useBodyEncodingForURI;
	}

	/**
	 * @return the useIPVHosts
	 */
	public Boolean getUseIPVHosts() {
		return useIPVHosts;
	}

	/**
	 * @param useIPVHosts the useIPVHosts to set
	 */
	public void setUseIPVHosts(Boolean useIPVHosts) {
		this.useIPVHosts = useIPVHosts;
	}

	/**
	 * @return the xpoweredBy
	 */
	public Boolean getXpoweredBy() {
		return xpoweredBy;
	}

	/**
	 * @param xpoweredBy the xpoweredBy to set
	 */
	public void setXpoweredBy(Boolean xpoweredBy) {
		this.xpoweredBy = xpoweredBy;
	}

	/**
	 * @return the acceptCount
	 */
	public Integer getAcceptCount() {
		return acceptCount;
	}

	/**
	 * @param acceptCount the acceptCount to set
	 */
	public void setAcceptCount(Integer acceptCount) {
		this.acceptCount = acceptCount;
	}

	/**
	 * @return the accepterThreadCount
	 */
	public Integer getAcceptorThreadCount() {
		return acceptorThreadCount;
	}

	/**
	 * @param acceptorThreadCount the acceptorThreadCount to set
	 */
	public void setAcceptorThreadCount(Integer acceptorThreadCount) {
		this.acceptorThreadCount = acceptorThreadCount;
	}

	/**
	 * @return the acceptorThreadPriority
	 */
	public Integer getAcceptorThreadPriority() {
		return acceptorThreadPriority;
	}

	/**
	 * @param acceptorThreadPriority the acceptorThreadPriority to set
	 */
	public void setAcceptorThreadPriority(Integer acceptorThreadPriority) {
		this.acceptorThreadPriority = acceptorThreadPriority;
	}

	/**
	 * @return the bindOnInit
	 */
	public Boolean getBindOnInit() {
		return bindOnInit;
	}

	/**
	 * @param bindOnInit the bindOnInit to set
	 */
	public void setBindOnInit(Boolean bindOnInit) {
		this.bindOnInit = bindOnInit;
	}

	/**
	 * @return the connectionLinger
	 */
	public Long getConnectionLinger() {
		return connectionLinger;
	}

	/**
	 * @param connectionLinger the connectionLinger to set
	 */
	public void setConnectionLinger(Long connectionLinger) {
		this.connectionLinger = connectionLinger;
	}

	/**
	 * @return the connectionTimeout
	 */
	public Long getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * @param connectionTimeout the connectionTimeout to set
	 */
	public void setConnectionTimeout(Long connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * @return the executor
	 */
	public String getExecutor() {
		return executor;
	}

	/**
	 * @param executor the executor to set
	 */
	public void setExecutor(String executor) {
		this.executor = executor;
	}

	/**
	 * @return the executorTerminationTimeoutMillis
	 */
	public Long getExecutorTerminationTimeoutMillis() {
		return executorTerminationTimeoutMillis;
	}

	/**
	 * @param executorTerminationTimeoutMillis the executorTerminationTimeoutMillis to set
	 */
	public void setExecutorTerminationTimeoutMillis(
			Long executorTerminationTimeoutMillis) {
		this.executorTerminationTimeoutMillis = executorTerminationTimeoutMillis;
	}

	/**
	 * @return the keepAliveTimeout
	 */
	public Long getKeepAliveTimeout() {
		return keepAliveTimeout;
	}

	/**
	 * @param keepAliveTimeout the keepAliveTimeout to set
	 */
	public void setKeepAliveTimeout(Long keepAliveTimeout) {
		this.keepAliveTimeout = keepAliveTimeout;
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

	/**
	 * @return the maxThreads
	 */
	public Integer getMaxThreads() {
		return maxThreads;
	}

	/**
	 * @param maxThreads the maxThreads to set
	 */
	public void setMaxThreads(Integer maxThreads) {
		this.maxThreads = maxThreads;
	}

	/**
	 * @return the minSpareThreads
	 */
	public Integer getMinSpareThreads() {
		return minSpareThreads;
	}

	/**
	 * @param minSpareThreads the minSpareThreads to set
	 */
	public void setMinSpareThreads(Integer minSpareThreads) {
		this.minSpareThreads = minSpareThreads;
	}

	/**
	 * @return the processorCache
	 */
	public Integer getProcessorCache() {
		return processorCache;
	}

	/**
	 * @param processorCache the processorCache to set
	 */
	public void setProcessorCache(Integer processorCache) {
		this.processorCache = processorCache;
	}

	/**
	 * @return the tcpNoDelay
	 */
	public Boolean getTcpNoDelay() {
		return tcpNoDelay;
	}

	/**
	 * @param tcpNoDelay the tcpNoDelay to set
	 */
	public void setTcpNoDelay(Boolean tcpNoDelay) {
		this.tcpNoDelay = tcpNoDelay;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
