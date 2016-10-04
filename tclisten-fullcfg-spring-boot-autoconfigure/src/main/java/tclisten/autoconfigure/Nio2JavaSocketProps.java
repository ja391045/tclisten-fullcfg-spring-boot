/*
 * <copyright file="Nio2JavaSocketProps.java" company="John Anderson">
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
public class Nio2JavaSocketProps extends JavaSocketProps {

	protected Boolean directBuffer = false;
	
	protected Integer appReadBufSize = 8192;
	
	protected Integer appWriteBufSize = 8192;
	
	protected Integer bufferPoolSize = 500;
	
	protected Integer processorCache = 500;
	
	protected Integer socketWrapperCache = 500;
	
	/**
	 * Generic no-arg constructor.
	 */
	public Nio2JavaSocketProps() {}
	
	

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.JavaSocketProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		TomcatConnectorCustomizer customizer = super.getCustomizer();
		return new Nio2JavaSocketCustomizer(customizer);
	}



	/**
	 * @return the directBuffer
	 */
	public Boolean getDirectBuffer() {
		return directBuffer;
	}

	/**
	 * @param directBuffer the directBuffer to set
	 */
	public void setDirectBuffer(Boolean directBuffer) {
		this.directBuffer = directBuffer;
	}

	/**
	 * @return the appReadBufSize
	 */
	public Integer getAppReadBufSize() {
		return appReadBufSize;
	}

	/**
	 * @param appReadBufSize the appReadBufSize to set
	 */
	public void setAppReadBufSize(Integer appReadBufSize) {
		this.appReadBufSize = appReadBufSize;
	}

	/**
	 * @return the appWriteBufSize
	 */
	public Integer getAppWriteBufSize() {
		return appWriteBufSize;
	}

	/**
	 * @param appWriteBufSize the appWriteBufSize to set
	 */
	public void setAppWriteBufSize(Integer appWriteBufSize) {
		this.appWriteBufSize = appWriteBufSize;
	}

	/**
	 * @return the bufferPoolSize
	 */
	public Integer getBufferPoolSize() {
		return bufferPoolSize;
	}

	/**
	 * @param bufferPoolSize the bufferPoolSize to set
	 */
	public void setBufferPoolSize(Integer bufferPoolSize) {
		this.bufferPoolSize = bufferPoolSize;
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
	 * @return the socketWrapperCache
	 */
	public Integer getSocketWrapperCache() {
		return socketWrapperCache;
	}

	/**
	 * @param socketWrapperCache the socketWrapperCache to set
	 */
	public void setSocketWrapperCache(Integer socketWrapperCache) {
		this.socketWrapperCache = socketWrapperCache;
	}

	public class Nio2JavaSocketCustomizer implements TomcatConnectorCustomizer {
		
		private TomcatConnectorCustomizer superCustomizer = null;

		public Nio2JavaSocketCustomizer(
				TomcatConnectorCustomizer superCustomizer) {
			this.superCustomizer = superCustomizer;
		}

		/* (non-Javadoc)
		 * @see org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer#customize(org.apache.catalina.connector.Connector)
		 */
		@Override
		public void customize(Connector connector) {
			this.superCustomizer.customize(connector);
			connector.setProperty("socket.directBuffer", String.valueOf(directBuffer));
			connector.setProperty("socket.appReadBufSize", String.valueOf(appReadBufSize));
			connector.setProperty("socket.appWriteBufSize", String.valueOf(appWriteBufSize));
			connector.setProperty("socket.bufferPoolSize", String.valueOf(bufferPoolSize));
			connector.setProperty("socket.processorCache", String.valueOf(processorCache));
			connector.setProperty("socket.socketWrapperCache", String.valueOf(socketWrapperCache));
		}
	}
}
