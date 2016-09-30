/*
 * <copyright file="TomcatConfig.java" company="CWIE, LLC">
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
package org.tcconn.autoconfigure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 * Top level of 'tcconn' config hierarchy.  This class is mainly used as a container for
 * connectors.  
 */

@ConfigurationProperties(prefix="tc", ignoreUnknownFields=false, ignoreInvalidFields=false)
public class TcProps implements Ordered {
	
	protected List<AprAjpConnectorProps> aprAjpConnectors = new ArrayList<AprAjpConnectorProps>();
	
	protected List<AprHttpConnectorProps> aprHttpConnectors = new ArrayList<AprHttpConnectorProps>();
	
	protected List<BioAjpConnectorProps> bioAjpConnectors = new ArrayList<BioAjpConnectorProps>();
	
	protected List<BioHttpConnectorProps> bioHttpConnectors = new ArrayList<BioHttpConnectorProps>();
	
	protected List<NioAjpConnectorProps> nioAjpConnectors = new ArrayList<NioAjpConnectorProps>();
	
	protected List<NioHttpConnectorProps> nioHttpConnectors = new ArrayList<NioHttpConnectorProps>();
	
	protected List<Nio2AjpConnectorProps> nio2AjpConnectors = new ArrayList<Nio2AjpConnectorProps>();
	
	protected List<Nio2HttpConnectorProps> nio2HttpConnectors = new ArrayList<Nio2HttpConnectorProps>();
	
	/**
	 * Generic no-args constructor.
	 */
	public TcProps() {}

	/**
	 * @return the aprAjpConnectors
	 */
	public List<AprAjpConnectorProps> getAprAjpConnectors() {
		return aprAjpConnectors;
	}

	/**
	 * @param aprAjpConnectors the aprAjpConnectors to set
	 */
	public void setAprAjpConnectors(List<AprAjpConnectorProps> aprAjpConnectors) {
		this.aprAjpConnectors = aprAjpConnectors;
	}

	/**
	 * @return the aprHttpConnectors
	 */
	public List<AprHttpConnectorProps> getAprHttpConnectors() {
		return aprHttpConnectors;
	}

	/**
	 * @param aprHttpConnectors the aprHttpConnectors to set
	 */
	public void setAprHttpConnectors(List<AprHttpConnectorProps> aprHttpConnectors) {
		this.aprHttpConnectors = aprHttpConnectors;
	}

	/**
	 * @return the bioAjpConnectors
	 */
	public List<BioAjpConnectorProps> getBioAjpConnectors() {
		return bioAjpConnectors;
	}

	/**
	 * @param bioAjpConnectors the bioAjpConnectors to set
	 */
	public void setBioAjpConnectors(List<BioAjpConnectorProps> bioAjpConnectors) {
		this.bioAjpConnectors = bioAjpConnectors;
	}

	/**
	 * @return the bioHttpConnectors
	 */
	public List<BioHttpConnectorProps> getBioHttpConnectors() {
		return bioHttpConnectors;
	}

	/**
	 * @param bioHttpConnectors the bioHttpConnectors to set
	 */
	public void setBioHttpConnectors(List<BioHttpConnectorProps> bioHttpConnectors) {
		this.bioHttpConnectors = bioHttpConnectors;
	}

	/**
	 * @return the nioAjpConnectors
	 */
	public List<NioAjpConnectorProps> getNioAjpConnectors() {
		return nioAjpConnectors;
	}

	/**
	 * @param nioAjpConnectors the nioAjpConnectors to set
	 */
	public void setNioAjpConnectors(List<NioAjpConnectorProps> nioAjpConnectors) {
		this.nioAjpConnectors = nioAjpConnectors;
	}

	/**
	 * @return the nioHttpConnectors
	 */
	public List<NioHttpConnectorProps> getNioHttpConnectors() {
		return nioHttpConnectors;
	}

	/**
	 * @param nioHttpConnectors the nioHttpConnectors to set
	 */
	public void setNioHttpConnectors(List<NioHttpConnectorProps> nioHttpConnectors) {
		this.nioHttpConnectors = nioHttpConnectors;
	}

	/**
	 * @return the nio2AjpConnectors
	 */
	public List<Nio2AjpConnectorProps> getNio2AjpConnectors() {
		return nio2AjpConnectors;
	}

	/**
	 * @param nio2AjpConnectors the nio2AjpConnectors to set
	 */
	public void setNio2AjpConnectors(List<Nio2AjpConnectorProps> nio2AjpConnectors) {
		this.nio2AjpConnectors = nio2AjpConnectors;
	}

	/**
	 * @return the nio2HttpConnectors
	 */
	public List<Nio2HttpConnectorProps> getNio2HttpConnectors() {
		return nio2HttpConnectors;
	}

	/**
	 * @param nio2HttpConnectors the nio2HttpConnectors to set
	 */
	public void setNio2HttpConnectors(
			List<Nio2HttpConnectorProps> nio2HttpConnectors) {
		this.nio2HttpConnectors = nio2HttpConnectors;
	}

	/* (non-Javadoc)
	 * @see org.springframework.core.Ordered#getOrder()
	 */
	@Override
	public int getOrder() {
		return 0;
	}
}
