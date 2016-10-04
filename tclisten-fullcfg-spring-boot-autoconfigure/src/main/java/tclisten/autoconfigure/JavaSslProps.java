/*
 * <copyright file="JavaSslProps.java" company="John Anderson">
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
 * <date>Oct 3, 2016</date>
 * <summary></summary>
 */
package tclisten.autoconfigure;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * @author <a href="mailto:ja391045@gmail.com">John Anderson</a>
 * Contains SSL options for all "pure Java" protocols, (BIO/NIO/NIO2).
 */
public class JavaSslProps implements SslProps {
	
	protected String algorithm = KeyManagerFactory.getDefaultAlgorithm();
	
	protected Boolean allowUnsafeLegacyRenegotiation = false;
	
	protected Boolean useServerCipherSuitesOrder = false;
	
	protected String ciphers = null;
	
	protected Boolean clientAuth = false;
	
	protected String clientCertProvider = null;
	
	protected String crlFile = null;
	
	protected String keyAlias = null;
	
	protected String keyPass = "changeit";
	
	protected String keystoreFile = null;
	
	protected String keystorePass = null;
	
	protected String keystoreProvider = null;
	
	protected String keystoreType = "JKS";
	
	protected Integer sessionCacheSize = 0;
	
	protected Integer sessionTimeout = 86400;
	
	protected String sslEnabledProtocols = null;
	
	protected String trustManagerClassName = null;
	
	protected Integer trustMaxCertLength = 5;
	
	protected String truststoreAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
	
	protected String truststoreFile = null;
	
	protected String truststorePass = null;
	
	protected String truststoreProvider = null;
	
	protected String truststoreType = "JKS";
	
	protected String sslImplementationName = "org.apache.tomcat.util.net.jsse.JSSEImplementation";
	
	protected String sslProtocol = "TLS";

	/**
	 * @return the sslImplementationName
	 */
	public String getSslImplementationName() {
		return sslImplementationName;
	}

	/**
	 * @param sslImplementationName the sslImplementationName to set
	 */
	public void setSslImplementationName(String sslImplementationName) {
		this.sslImplementationName = sslImplementationName;
	}

	/**
	 * @return the sslProtocol
	 */
	public String getSslProtocol() {
		return sslProtocol;
	}

	/**
	 * @param sslProtocol the sslProtocol to set
	 */
	public void setSslProtocol(String sslProtocol) {
		this.sslProtocol = sslProtocol;
	}

	/**
	 * Generic no-args constructor.
	 */
	public JavaSslProps() {}

	/* (non-Javadoc)
	 * @see tclisten.autoconfigure.SslProps#getCustomizer()
	 */
	@Override
	public TomcatConnectorCustomizer getCustomizer() {
		return new TomcatConnectorCustomizer() {
			
			@Override
			public void customize(Connector connector) {
				connector.setProperty("algorithm", algorithm);
				connector.setProperty("allowUnsafeLegacyRenegotiation",
						String.valueOf(allowUnsafeLegacyRenegotiation));
				if(useServerCipherSuitesOrder != null)
					connector.setProperty("useServerCipherSuitesOrder", 
							String.valueOf(useServerCipherSuitesOrder));
				if(ciphers != null)
					connector.setProperty("ciphers", ciphers);
				connector.setProperty("clientAuth", String.valueOf(clientAuth));
				if(clientCertProvider != null)
					connector.setProperty("clientCertProvider", clientCertProvider);
				if(crlFile != null)
					connector.setProperty("crlFile", crlFile);
				if(keyAlias != null)
					connector.setProperty("keyAlias", keyAlias);
				connector.setProperty("keyPass", keyPass);
				if(keystoreFile != null)
					connector.setProperty("keystoreFile", keystoreFile);
				if(keystorePass != null)
					connector.setProperty("keystorePass", keystorePass);
				if(keystoreProvider != null)
					connector.setProperty("keystoreProvider", keystoreProvider);
				connector.setProperty("keystoreType", keystoreType);
				connector.setProperty("sessionCacheSize", String.valueOf(sessionCacheSize));
				connector.setProperty("sessionTimeout", String.valueOf(sessionTimeout));
				if(sslEnabledProtocols != null)
					connector.setProperty("sslEnabledProtocols", sslEnabledProtocols);
				connector.setProperty("sslImplementationName", sslImplementationName);
				connector.setProperty("sslProtocol", sslProtocol);
				if(trustManagerClassName != null)
					connector.setProperty("trustManagerClassName", trustManagerClassName);
				connector.setProperty("trustMaxCertLength", String.valueOf(trustMaxCertLength));
				connector.setProperty("truststoreAlgorithm", truststoreAlgorithm);
				if(truststoreFile != null)
					connector.setProperty("truststoreFile", truststoreFile);
				if(truststorePass != null)
					connector.setProperty("truststorePass", truststorePass);
				if(truststoreProvider != null)
					connector.setProperty("truststoreProvider", truststoreProvider);
				connector.setProperty("truststoreType", truststoreType);
			}
		};
	}

	/**
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * @param algorithm the algorithm to set
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * @return the allowUnsafeLegacyRenegotiation
	 */
	public Boolean getAllowUnsafeLegacyRenegotiation() {
		return allowUnsafeLegacyRenegotiation;
	}

	/**
	 * @param allowUnsafeLegacyRenegotiation the allowUnsafeLegacyRenegotiation to set
	 */
	public void setAllowUnsafeLegacyRenegotiation(
			Boolean allowUnsafeLegacyRenegotiation) {
		this.allowUnsafeLegacyRenegotiation = allowUnsafeLegacyRenegotiation;
	}

	/**
	 * @return the useServerCipherSuitesOrder
	 */
	public Boolean getUseServerCipherSuitesOrder() {
		return useServerCipherSuitesOrder;
	}

	/**
	 * @param useServerCipherSuitesOrder the useServerCipherSuitesOrder to set
	 */
	public void setUseServerCipherSuitesOrder(Boolean useServerCipherSuitesOrder) {
		this.useServerCipherSuitesOrder = useServerCipherSuitesOrder;
	}

	/**
	 * @return the ciphers
	 */
	public String getCiphers() {
		return ciphers;
	}

	/**
	 * @param ciphers the ciphers to set
	 */
	public void setCiphers(String ciphers) {
		this.ciphers = ciphers;
	}

	/**
	 * @return the clientAuth
	 */
	public Boolean getClientAuth() {
		return clientAuth;
	}

	/**
	 * @param clientAuth the clientAuth to set
	 */
	public void setClientAuth(Boolean clientAuth) {
		this.clientAuth = clientAuth;
	}

	/**
	 * @return the clientCertProvider
	 */
	public String getClientCertProvider() {
		return clientCertProvider;
	}

	/**
	 * @param clientCertProvider the clientCertProvider to set
	 */
	public void setClientCertProvider(String clientCertProvider) {
		this.clientCertProvider = clientCertProvider;
	}

	/**
	 * @return the crlFile
	 */
	public String getCrlFile() {
		return crlFile;
	}

	/**
	 * @param crlFile the crlFile to set
	 */
	public void setCrlFile(String crlFile) {
		this.crlFile = crlFile;
	}

	/**
	 * @return the keyAlias
	 */
	public String getKeyAlias() {
		return keyAlias;
	}

	/**
	 * @param keyAlias the keyAlias to set
	 */
	public void setKeyAlias(String keyAlias) {
		this.keyAlias = keyAlias;
	}

	/**
	 * @return the keyPass
	 */
	public String getKeyPass() {
		return keyPass;
	}

	/**
	 * @param keyPass the keyPass to set
	 */
	public void setKeyPass(String keyPass) {
		this.keyPass = keyPass;
	}

	/**
	 * @return the keystoreFile
	 */
	public String getKeystoreFile() {
		return keystoreFile;
	}

	/**
	 * @param keystoreFile the keystoreFile to set
	 */
	public void setKeystoreFile(String keystoreFile) {
		this.keystoreFile = keystoreFile;
	}

	/**
	 * @return the keystorePass
	 */
	public String getKeystorePass() {
		return keystorePass;
	}

	/**
	 * @param keystorePass the keystorePass to set
	 */
	public void setKeystorePass(String keystorePass) {
		this.keystorePass = keystorePass;
	}

	/**
	 * @return the keystoreProvider
	 */
	public String getKeystoreProvider() {
		return keystoreProvider;
	}

	/**
	 * @param keystoreProvider the keystoreProvider to set
	 */
	public void setKeystoreProvider(String keystoreProvider) {
		this.keystoreProvider = keystoreProvider;
	}

	/**
	 * @return the keystoreType
	 */
	public String getKeystoreType() {
		return keystoreType;
	}

	/**
	 * @param keystoreType the keystoreType to set
	 */
	public void setKeystoreType(String keystoreType) {
		this.keystoreType = keystoreType;
	}

	/**
	 * @return the sessionCacheSize
	 */
	public Integer getSessionCacheSize() {
		return sessionCacheSize;
	}

	/**
	 * @param sessionCacheSize the sessionCacheSize to set
	 */
	public void setSessionCacheSize(Integer sessionCacheSize) {
		this.sessionCacheSize = sessionCacheSize;
	}

	/**
	 * @return the sessionTimeout
	 */
	public Integer getSessionTimeout() {
		return sessionTimeout;
	}

	/**
	 * @param sessionTimeout the sessionTimeout to set
	 */
	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	/**
	 * @return the sslEnabledProtocols
	 */
	public String getSslEnabledProtocols() {
		return sslEnabledProtocols;
	}

	/**
	 * @param sslEnabledProtocols the sslEnabledProtocols to set
	 */
	public void setSslEnabledProtocols(String sslEnabledProtocols) {
		this.sslEnabledProtocols = sslEnabledProtocols;
	}

	/**
	 * @return the trustManagerClassName
	 */
	public String getTrustManagerClassName() {
		return trustManagerClassName;
	}

	/**
	 * @param trustManagerClassName the trustManagerClassName to set
	 */
	public void setTrustManagerClassName(String trustManagerClassName) {
		this.trustManagerClassName = trustManagerClassName;
	}

	/**
	 * @return the trustMaxCertLength
	 */
	public Integer getTrustMaxCertLength() {
		return trustMaxCertLength;
	}

	/**
	 * @param trustMaxCertLength the trustMaxCertLength to set
	 */
	public void setTrustMaxCertLength(Integer trustMaxCertLength) {
		this.trustMaxCertLength = trustMaxCertLength;
	}

	/**
	 * @return the truststoreAlgorithm
	 */
	public String getTruststoreAlgorithm() {
		return truststoreAlgorithm;
	}

	/**
	 * @param truststoreAlgorithm the truststoreAlgorithm to set
	 */
	public void setTruststoreAlgorithm(String truststoreAlgorithm) {
		this.truststoreAlgorithm = truststoreAlgorithm;
	}

	/**
	 * @return the truststoreFile
	 */
	public String getTruststoreFile() {
		return truststoreFile;
	}

	/**
	 * @param truststoreFile the truststoreFile to set
	 */
	public void setTruststoreFile(String truststoreFile) {
		this.truststoreFile = truststoreFile;
	}

	/**
	 * @return the truststorePass
	 */
	public String getTruststorePass() {
		return truststorePass;
	}

	/**
	 * @param truststorePass the truststorePass to set
	 */
	public void setTruststorePass(String truststorePass) {
		this.truststorePass = truststorePass;
	}

	/**
	 * @return the truststoreProvider
	 */
	public String getTruststoreProvider() {
		return truststoreProvider;
	}

	/**
	 * @param truststoreProvider the truststoreProvider to set
	 */
	public void setTruststoreProvider(String truststoreProvider) {
		this.truststoreProvider = truststoreProvider;
	}

	/**
	 * @return the truststoreType
	 */
	public String getTruststoreType() {
		return truststoreType;
	}

	/**
	 * @param truststoreType the truststoreType to set
	 */
	public void setTruststoreType(String truststoreType) {
		this.truststoreType = truststoreType;
	}
}
