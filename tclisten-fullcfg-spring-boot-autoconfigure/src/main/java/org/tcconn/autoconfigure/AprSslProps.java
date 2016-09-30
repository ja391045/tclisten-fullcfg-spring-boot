/*
 * <copyright file="AprSslProps.java" company="CWIE, LLC">
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
package org.tcconn.autoconfigure;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 * 
 * Contains SSL options for all APR/Native SSL options.
 */
public class AprSslProps implements SslProps {
	
	protected String SSLCACertificateFile = null;
	
	protected String SSLCACertificatePath = null;
	
	protected String SSLCARevocationFile = null;
	
	protected String SSLCARevocationPath = null;
	
	protected String SSLCertificateChainFile = null;
	
	protected String SSLCertificateFile = null;
	
	protected String SSLCertificateKeyFile = null;
	
	protected String SSLCipherSuite = "HIGH:!aNULL:!eNULL:!EXPORT:!DES:!RC4:!MD5";
	
	protected Boolean SSLDisableCompression = false;
	
	protected Boolean SSLHonorCipherOrder = false;
	
	protected String SSLPassword = null;
	
	protected String SSLProtocol = "all";
	
	protected String SSLVerifyClient = "none";
	
	protected Integer SSLVerifyDepth = 10;
	
	protected Boolean SSLDisableSessionTickets = false;
	
	
	/**
	 * Generic no-args constructor. 
	 */
	public AprSslProps() {}


	/* (non-Javadoc)
	 * @see org.tcconn.autoconfigure.SslProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		return new TomcatConnectorCustomizer() {
			
			@Override
			public void customize(Connector connector) {
				if(SSLCACertificateFile != null)
					connector.setProperty("SSLCACertificateFile", SSLCACertificateFile);
				if(SSLCACertificatePath != null)
					connector.setProperty("SSLCACertificatePath", SSLCACertificatePath);
				if(SSLCARevocationFile != null)
					connector.setProperty("SSLCARevocationFile", SSLCARevocationFile);
				if(SSLCARevocationPath != null)
					connector.setProperty("SSLCARevocationPath", SSLCARevocationPath);
				if(SSLCertificateChainFile != null)
					connector.setProperty("SSLCertificateChainFile", SSLCertificateChainFile);
				if(SSLCertificateFile != null)
					connector.setProperty("SSLCertificateFile", SSLCertificateFile);
				if(SSLCertificateKeyFile != null)
					connector.setProperty("SSLCertificateKeyFile", SSLCertificateKeyFile);
				connector.setProperty("SSLCipherSuite", SSLCipherSuite);
				connector.setProperty("SSLDisableCompression", String.valueOf(SSLDisableCompression));
				connector.setProperty("SSLHonorCipherOrder", String.valueOf(SSLHonorCipherOrder));
				if(SSLPassword != null)
					connector.setProperty("SSLPassword", SSLPassword);
				connector.setProperty("SSLProtocol", SSLProtocol);
				connector.setProperty("SSLVerifyClient", SSLVerifyClient);
				connector.setProperty("SSLVerifyDepth", String.valueOf(SSLVerifyDepth));
				connector.setProperty("SSLDisableSessionTickets", String.valueOf(SSLDisableSessionTickets));
			}
		};
	}


	/**
	 * @return the sSLCACertificateFile
	 */
	public String getSSLCACertificateFile() {
		return SSLCACertificateFile;
	}


	/**
	 * @param sSLCACertificateFile the sSLCACertificateFile to set
	 */
	public void setSSLCACertificateFile(String sSLCACertificateFile) {
		SSLCACertificateFile = sSLCACertificateFile;
	}


	/**
	 * @return the sSLCACertificatePath
	 */
	public String getSSLCACertificatePath() {
		return SSLCACertificatePath;
	}


	/**
	 * @param sSLCACertificatePath the sSLCACertificatePath to set
	 */
	public void setSSLCACertificatePath(String sSLCACertificatePath) {
		SSLCACertificatePath = sSLCACertificatePath;
	}


	/**
	 * @return the sSLCARevocationFile
	 */
	public String getSSLCARevocationFile() {
		return SSLCARevocationFile;
	}


	/**
	 * @param sSLCARevocationFile the sSLCARevocationFile to set
	 */
	public void setSSLCARevocationFile(String sSLCARevocationFile) {
		SSLCARevocationFile = sSLCARevocationFile;
	}


	/**
	 * @return the sSLCARevocationPath
	 */
	public String getSSLCARevocationPath() {
		return SSLCARevocationPath;
	}


	/**
	 * @param sSLCARevocationPath the sSLCARevocationPath to set
	 */
	public void setSSLCARevocationPath(String sSLCARevocationPath) {
		SSLCARevocationPath = sSLCARevocationPath;
	}


	/**
	 * @return the sSLCertificateChainFile
	 */
	public String getSSLCertificateChainFile() {
		return SSLCertificateChainFile;
	}


	/**
	 * @param sSLCertificateChainFile the sSLCertificateChainFile to set
	 */
	public void setSSLCertificateChainFile(String sSLCertificateChainFile) {
		SSLCertificateChainFile = sSLCertificateChainFile;
	}


	/**
	 * @return the sSLCertificateFile
	 */
	public String getSSLCertificateFile() {
		return SSLCertificateFile;
	}


	/**
	 * @param sSLCertificateFile the sSLCertificateFile to set
	 */
	public void setSSLCertificateFile(String sSLCertificateFile) {
		SSLCertificateFile = sSLCertificateFile;
	}


	/**
	 * @return the sSLCertificateKeyFile
	 */
	public String getSSLCertificateKeyFile() {
		return SSLCertificateKeyFile;
	}


	/**
	 * @param sSLCertificateKeyFile the sSLCertificateKeyFile to set
	 */
	public void setSSLCertificateKeyFile(String sSLCertificateKeyFile) {
		SSLCertificateKeyFile = sSLCertificateKeyFile;
	}


	/**
	 * @return the sSLCipherSuite
	 */
	public String getSSLCipherSuite() {
		return SSLCipherSuite;
	}


	/**
	 * @param sSLCipherSuite the sSLCipherSuite to set
	 */
	public void setSSLCipherSuite(String sSLCipherSuite) {
		SSLCipherSuite = sSLCipherSuite;
	}


	/**
	 * @return the sSLDisableCompression
	 */
	public Boolean getSSLDisableCompression() {
		return SSLDisableCompression;
	}


	/**
	 * @param sSLDisableCompression the sSLDisableCompression to set
	 */
	public void setSSLDisableCompression(Boolean sSLDisableCompression) {
		SSLDisableCompression = sSLDisableCompression;
	}


	/**
	 * @return the sSLHonorCipherOrder
	 */
	public Boolean getSSLHonorCipherOrder() {
		return SSLHonorCipherOrder;
	}


	/**
	 * @param sSLHonorCipherOrder the sSLHonorCipherOrder to set
	 */
	public void setSSLHonorCipherOrder(Boolean sSLHonorCipherOrder) {
		SSLHonorCipherOrder = sSLHonorCipherOrder;
	}


	/**
	 * @return the sSLPassword
	 */
	public String getSSLPassword() {
		return SSLPassword;
	}


	/**
	 * @param sSLPassword the sSLPassword to set
	 */
	public void setSSLPassword(String sSLPassword) {
		SSLPassword = sSLPassword;
	}


	/**
	 * @return the sSLProtocol
	 */
	public String getSSLProtocol() {
		return SSLProtocol;
	}


	/**
	 * @param sSLProtocol the sSLProtocol to set
	 */
	public void setSSLProtocol(String sSLProtocol) {
		SSLProtocol = sSLProtocol;
	}


	/**
	 * @return the sSLVerifyClient
	 */
	public String getSSLVerifyClient() {
		return SSLVerifyClient;
	}


	/**
	 * @param sSLVerifyClient the sSLVerifyClient to set
	 */
	public void setSSLVerifyClient(String sSLVerifyClient) {
		SSLVerifyClient = sSLVerifyClient;
	}


	/**
	 * @return the sSLVerifyDepth
	 */
	public Integer getSSLVerifyDepth() {
		return SSLVerifyDepth;
	}


	/**
	 * @param sSLVerifyDepth the sSLVerifyDepth to set
	 */
	public void setSSLVerifyDepth(Integer sSLVerifyDepth) {
		SSLVerifyDepth = sSLVerifyDepth;
	}


	/**
	 * @return the sSLDisableSessionTickets
	 */
	public Boolean getSSLDisableSessionTickets() {
		return SSLDisableSessionTickets;
	}


	/**
	 * @param sSLDisableSessionTickets the sSLDisableSessionTickets to set
	 */
	public void setSSLDisableSessionTickets(Boolean sSLDisableSessionTickets) {
		SSLDisableSessionTickets = sSLDisableSessionTickets;
	}

}
