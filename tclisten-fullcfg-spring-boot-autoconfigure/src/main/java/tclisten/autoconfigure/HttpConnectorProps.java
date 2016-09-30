/*
 * <copyright file="HttpConnectorProps.java" company="CWIE, LLC">
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
package tclisten.autoconfigure;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
public class HttpConnectorProps extends ConnectorProps {
	
	protected String[] allowedTrailerHeaders = {};
	
	protected String[] compressableMimeType = {
			"text/html", "text/xml", "text/plain", "text/css", "text/javascript",
			"application/javascript"
	};
	
	@Pattern(regexp = "off|on|force")
	protected String compression = "off";
	
	@Min(0)
	protected Long compressionMinSize = 2048L;
	
	/* Overrides ConnectorProps.connectionTimeout */
	@Min(-1)
	protected Long connectionTimeout = 60000L;
	
	@Min(-1)
	protected Long connectionUploadTimeout = 60000L;
	
	protected Boolean disableUploadTimeout = true;
	
	@Min(-1)
	protected Integer maxExtensionSize = 8192;
	
	protected Integer maxHttpHeaderSize = 8192;
	
	@Min(-1)
	protected Integer maxKeepAliveRequests = 100;
	
	@Min(-1)
	protected Long maxSwallowSize = 2097152L;
	
	@Min(-1)
	protected Integer maxTrailerSize = 8192;
	
	protected String noCompressionUserAgents = null;
	
	protected String restrictedUserAgents = null;
	
	protected String server = "Apache-Coyote/1.1";
	
	protected Integer socketBuffer = 9000;
	
	protected Boolean sslEnabled = false;
	
	protected Integer upgradeAsyncWriteBufferSize = 8192;
	
	/**
	 * Generic no-args constructor.
	 */
	public HttpConnectorProps() {}
	
	private String join(char d, String[] txt){
		StringBuilder out = new StringBuilder();
		for(int i = 0; i < txt.length; i++){
			out.append(txt[i]);
			if(i + 1 < txt.length)
				out.append(d);
		}
		return out.toString();
	}

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.ConnectorProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new HttpCustomizer(customizer);
	}



	/**
	 * @return the allowedTrailerHeaders
	 */
	public String[] getAllowedTrailerHeaders() {
		return allowedTrailerHeaders;
	}

	/**
	 * @param allowedTrailerHeaders the allowedTrailerHeaders to set
	 */
	public void setAllowedTrailerHeaders(String[] allowedTrailerHeaders) {
		this.allowedTrailerHeaders = allowedTrailerHeaders;
	}

	/**
	 * @return the compressableMimeType
	 */
	public String[] getCompressableMimeType() {
		return compressableMimeType;
	}

	/**
	 * @param compressableMimeType the compressableMimeType to set
	 */
	public void setCompressableMimeType(String[] compressableMimeType) {
		this.compressableMimeType = compressableMimeType;
	}

	/**
	 * @return the compression
	 */
	public String getCompression() {
		return compression;
	}

	/**
	 * @param compression the compression to set
	 */
	public void setCompression(String compression) {
		this.compression = compression;
	}

	/**
	 * @return the compressionMinSize
	 */
	public Long getCompressionMinSize() {
		return compressionMinSize;
	}

	/**
	 * @param compressionMinSize the compressionMinSize to set
	 */
	public void setCompressionMinSize(Long compressionMinSize) {
		this.compressionMinSize = compressionMinSize;
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
	 * @return the connectionUploadTimeout
	 */
	public Long getConnectionUploadTimeout() {
		return connectionUploadTimeout;
	}

	/**
	 * @param connectionUploadTimeout the connectionUploadTimeout to set
	 */
	public void setConnectionUploadTimeout(Long connectionUploadTimeout) {
		this.connectionUploadTimeout = connectionUploadTimeout;
	}

	/**
	 * @return the disableUploadTimeout
	 */
	public Boolean getDisableUploadTimeout() {
		return disableUploadTimeout;
	}

	/**
	 * @param disableUploadTimeout the disableUploadTimeout to set
	 */
	public void setDisableUploadTimeout(Boolean disableUploadTimeout) {
		this.disableUploadTimeout = disableUploadTimeout;
	}

	/**
	 * @return the maxExtensionSize
	 */
	public Integer getMaxExtensionSize() {
		return maxExtensionSize;
	}

	/**
	 * @param maxExtensionSize the maxExtensionSize to set
	 */
	public void setMaxExtensionSize(Integer maxExtensionSize) {
		this.maxExtensionSize = maxExtensionSize;
	}

	/**
	 * @return the maxHttpHeaderSize
	 */
	public Integer getMaxHttpHeaderSize() {
		return maxHttpHeaderSize;
	}

	/**
	 * @param maxHttpHeaderSize the maxHttpHeaderSize to set
	 */
	public void setMaxHttpHeaderSize(Integer maxHttpHeaderSize) {
		this.maxHttpHeaderSize = maxHttpHeaderSize;
	}

	/**
	 * @return the maxKeepAliveRequests
	 */
	public Integer getMaxKeepAliveRequests() {
		return maxKeepAliveRequests;
	}

	/**
	 * @param maxKeepAliveRequests the maxKeepAliveRequests to set
	 */
	public void setMaxKeepAliveRequests(Integer maxKeepAliveRequests) {
		this.maxKeepAliveRequests = maxKeepAliveRequests;
	}

	/**
	 * @return the maxSwallowSize
	 */
	public Long getMaxSwallowSize() {
		return maxSwallowSize;
	}

	/**
	 * @param maxSwallowSize the maxSwallowSize to set
	 */
	public void setMaxSwallowSize(Long maxSwallowSize) {
		this.maxSwallowSize = maxSwallowSize;
	}

	/**
	 * @return the maxTrailerSize
	 */
	public Integer getMaxTrailerSize() {
		return maxTrailerSize;
	}

	/**
	 * @param maxTrailerSize the maxTrailerSize to set
	 */
	public void setMaxTrailerSize(Integer maxTrailerSize) {
		this.maxTrailerSize = maxTrailerSize;
	}

	/**
	 * @return the noCompressionUserAgents
	 */
	public String getNoCompressionUserAgents() {
		return noCompressionUserAgents;
	}

	/**
	 * @param noCompressionUserAgents the noCompressionUserAgents to set
	 */
	public void setNoCompressionUserAgents(String noCompressionUserAgents) {
		this.noCompressionUserAgents = noCompressionUserAgents;
	}

	/**
	 * @return the restrictedUserAgents
	 */
	public String getRestrictedUserAgents() {
		return restrictedUserAgents;
	}

	/**
	 * @param restrictedUserAgents the restrictedUserAgents to set
	 */
	public void setRestrictedUserAgents(String restrictedUserAgents) {
		this.restrictedUserAgents = restrictedUserAgents;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the socketBuffer
	 */
	public Integer getSocketBuffer() {
		return socketBuffer;
	}

	/**
	 * @param socketBuffer the socketBuffer to set
	 */
	public void setSocketBuffer(Integer socketBuffer) {
		this.socketBuffer = socketBuffer;
	}

	/**
	 * @return the sslEnabled
	 */
	public Boolean getSslEnabled() {
		return sslEnabled;
	}

	/**
	 * @param sslEnabled the sslEnabled to set
	 */
	public void setSslEnabled(Boolean sslEnabled) {
		this.sslEnabled = sslEnabled;
	}

	/**
	 * @return the upgradeAsyncWriteBufferSize
	 */
	public Integer getUpgradeAsyncWriteBufferSize() {
		return upgradeAsyncWriteBufferSize;
	}

	/**
	 * @param upgradeAsyncWriteBufferSize the upgradeAsyncWriteBufferSize to set
	 */
	public void setUpgradeAsyncWriteBufferSize(Integer upgradeAsyncWriteBufferSize) {
		this.upgradeAsyncWriteBufferSize = upgradeAsyncWriteBufferSize;
	}
	
	public class HttpCustomizer implements TomcatConnectorCustomizer {
		
		TomcatConnectorCustomizer superCustomizer = null;

		public HttpCustomizer(TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			if(allowedTrailerHeaders != null && allowedTrailerHeaders.length > 0)
				connector.setProperty("allowedTrailerHeaders", 
						join(',', allowedTrailerHeaders));
			if(compressableMimeType != null && compressableMimeType.length > 0)
				connector.setProperty("compressableMimeType",
					join(',', compressableMimeType));
			connector.setProperty("compression", compression);
			connector.setProperty("compressionMinSize", String.valueOf(compressionMinSize));
			connector.setProperty("connectionTimeout", String.valueOf(connectionTimeout));
			connector.setProperty("connectionUploadTimeout", String.valueOf(connectionUploadTimeout));
			connector.setProperty("disableUploadTimeout", String.valueOf(disableUploadTimeout));
			connector.setProperty("maxExtensionSize", String.valueOf(maxExtensionSize));
			connector.setProperty("maxHttpHeaderSize", String.valueOf(maxHttpHeaderSize));
			connector.setProperty("maxKeepAliveRequests", String.valueOf(maxKeepAliveRequests));
			connector.setProperty("maxSwallowSize", String.valueOf(maxSwallowSize));
			connector.setProperty("maxTrailerSize", String.valueOf(maxTrailerSize));
			if(noCompressionUserAgents != null)
				connector.setProperty("noCompressionUserAgents", noCompressionUserAgents);
			if(restrictedUserAgents != null)
				connector.setProperty("restrictedUserAgents", restrictedUserAgents);
			connector.setProperty("server", server);
			connector.setProperty("socketBuffer", String.valueOf(socketBuffer));
			connector.setProperty("SSLEnabled", String.valueOf(sslEnabled));
			connector.setProperty("upgradeAsyncWriteBufferSize",
					String.valueOf(upgradeAsyncWriteBufferSize));
			
		}			
	}
}
