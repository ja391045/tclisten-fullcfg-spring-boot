/*
 * <copyright file="JavaSocketProps.java" company="CWIE, LLC">
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

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
public class JavaSocketProps {
	protected Integer rxBufSize = null;
	
	protected Integer txBufSize = null;
	
	protected Boolean tcpNoDelay = null;
	
	protected Boolean soKeepAlive = null;
	
	protected Boolean ooBInline = null;
	
	protected Boolean soReuseAddress = null;
	
	protected Boolean soLingerOn = null;
	
	protected Boolean soLingerTime = null;
	
	protected Long soTimeout = null;
	
	protected Integer unlockTimeout = null;
	
	protected Integer performanceConnectionTime = null;
	
	protected Integer performanceLatency = null;
	
	protected Integer performanceBandwidth = null;

	/**
	 * Generic no-arg constructor;
	 */
	public JavaSocketProps() {}
	
	/**
	 * Get a customizer that applies these properties.
	 * @return The customizer.
	 */
	public TomcatConnectorCustomizer getCustomizer(){
		return new TomcatConnectorCustomizer() {
			
			@Override
			public void customize(Connector connector) {
				if(rxBufSize != null)
					connector.setProperty("socket.rxBufSize", String.valueOf(rxBufSize));
				if(txBufSize != null)
					connector.setProperty("socket.txBufSize", String.valueOf(txBufSize));
				if(tcpNoDelay != null)
					connector.setProperty("socket.tcpNoDelay", String.valueOf(tcpNoDelay));
				if(soKeepAlive != null)
					connector.setProperty("socket.soKeepAlive", String.valueOf(soKeepAlive));
				if(ooBInline != null)
					connector.setProperty("socket.ooBInline", String.valueOf(ooBInline));
				if(soReuseAddress != null)
					connector.setProperty("socket.soReuseAddress", String.valueOf(soReuseAddress));
				if(soLingerOn != null)
					connector.setProperty("socket.soLingerOn", String.valueOf(soLingerOn));
				if(soLingerTime != null)
					connector.setProperty("socket.soLingerTime", String.valueOf(soLingerTime));
				if(soTimeout != null)
					connector.setProperty("socket.soTimeout", String.valueOf(soTimeout));
				if(unlockTimeout != null)
					connector.setProperty("socket.unlockTimeout", String.valueOf(unlockTimeout));
				if(performanceConnectionTime != null)
					connector.setProperty("socket.performanceConnectionTime", 
							String.valueOf(performanceConnectionTime));
				if(performanceLatency != null)
					connector.setProperty("socket.performanceLatency",
							String.valueOf(performanceLatency));
				if(performanceBandwidth != null)
					connector.setProperty("socket.performanceBandwidth",
							String.valueOf(performanceBandwidth));
				
			}
		};
	}

	/**
	 * @return the rxBufSize
	 */
	public Integer getRxBufSize() {
		return rxBufSize;
	}

	/**
	 * @param rxBufSize the rxBufSize to set
	 */
	public void setRxBufSize(Integer rxBufSize) {
		this.rxBufSize = rxBufSize;
	}

	/**
	 * @return the txBufSize
	 */
	public Integer getTxBufSize() {
		return txBufSize;
	}

	/**
	 * @param txBufSize the txBufSize to set
	 */
	public void setTxBufSize(Integer txBufSize) {
		this.txBufSize = txBufSize;
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
	 * @return the soKeepAlive
	 */
	public Boolean getSoKeepAlive() {
		return soKeepAlive;
	}

	/**
	 * @param soKeepAlive the soKeepAlive to set
	 */
	public void setSoKeepAlive(Boolean soKeepAlive) {
		this.soKeepAlive = soKeepAlive;
	}

	/**
	 * @return the ooBInline
	 */
	public Boolean getOoBInline() {
		return ooBInline;
	}

	/**
	 * @param ooBInline the ooBInline to set
	 */
	public void setOoBInline(Boolean ooBInline) {
		this.ooBInline = ooBInline;
	}

	/**
	 * @return the soReuseAddress
	 */
	public Boolean getSoReuseAddress() {
		return soReuseAddress;
	}

	/**
	 * @param soReuseAddress the soReuseAddress to set
	 */
	public void setSoReuseAddress(Boolean soReuseAddress) {
		this.soReuseAddress = soReuseAddress;
	}

	/**
	 * @return the soLingerOn
	 */
	public Boolean getSoLingerOn() {
		return soLingerOn;
	}

	/**
	 * @param soLingerOn the soLingerOn to set
	 */
	public void setSoLingerOn(Boolean soLingerOn) {
		this.soLingerOn = soLingerOn;
	}

	/**
	 * @return the soLingerTime
	 */
	public Boolean getSoLingerTime() {
		return soLingerTime;
	}

	/**
	 * @param soLingerTime the soLingerTime to set
	 */
	public void setSoLingerTime(Boolean soLingerTime) {
		this.soLingerTime = soLingerTime;
	}

	/**
	 * @return the soTimeout
	 */
	public Long getSoTimeout() {
		return soTimeout;
	}

	/**
	 * @param soTimeout the soTimeout to set
	 */
	public void setSoTimeout(Long soTimeout) {
		this.soTimeout = soTimeout;
	}

	/**
	 * @return the unlockTimeout
	 */
	public Integer getUnlockTimeout() {
		return unlockTimeout;
	}

	/**
	 * @param unlockTimeout the unlockTimeout to set
	 */
	public void setUnlockTimeout(Integer unlockTimeout) {
		this.unlockTimeout = unlockTimeout;
	}

	/**
	 * @return the performanceConnectionTime
	 */
	public Integer getPerformanceConnectionTime() {
		return performanceConnectionTime;
	}

	/**
	 * @param performanceConnectionTime the performanceConnectionTime to set
	 */
	public void setPerformanceConnectionTime(Integer performanceConnectionTime) {
		this.performanceConnectionTime = performanceConnectionTime;
	}

	/**
	 * @return the performanceLatency
	 */
	public Integer getPerformanceLatency() {
		return performanceLatency;
	}

	/**
	 * @param performanceLatency the performanceLatency to set
	 */
	public void setPerformanceLatency(Integer performanceLatency) {
		this.performanceLatency = performanceLatency;
	}

	/**
	 * @return the performanceBandwidth
	 */
	public Integer getPerformanceBandwidth() {
		return performanceBandwidth;
	}

	/**
	 * @param performanceBandwidth the performanceBandwidth to set
	 */
	public void setPerformanceBandwidth(Integer performanceBandwidth) {
		this.performanceBandwidth = performanceBandwidth;
	}
	
	

}
