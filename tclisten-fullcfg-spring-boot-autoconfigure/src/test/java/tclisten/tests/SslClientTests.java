/*
 * <copyright file="SslClientTests.java" company="John Anderson">
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
 * <date>Sep 21, 2016</date>
 * <summary></summary>
 */
package tclisten.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URI;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import tclisten.Application;

/**
 * @author <a href="mailto:ja391045@gmail.com">John Anderson</a>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(profiles = {"sslclient"})
@WebIntegrationTest
public class SslClientTests {
	
	private RestTemplate rt;
	
	@Before
	public void setUp() throws Exception {
		NoopHostnameVerifier noophnv = new NoopHostnameVerifier();
		SSLContext sslContext = SSLContexts.custom()
			.loadTrustMaterial(
				new File("src/test/resources/truststore.jks"), 
				"changeme".toCharArray(),
				new TrustSelfSignedStrategy()
			)
			.loadKeyMaterial(
				new File("src/test/resources/keystore.jks"),
				"changeme".toCharArray(),
				"changeme".toCharArray()
			)
			.useProtocol("TLSv1.2")
			.build();
		SSLConnectionSocketFactory sslsf = 
			new SSLConnectionSocketFactory(sslContext, new String[] {"TLSv1.2"}, null, noophnv);
		
		HttpClient client = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.build();
		
		HttpComponentsClientHttpRequestFactory requestFactory = 
				new HttpComponentsClientHttpRequestFactory();
		
		requestFactory.setHttpClient(client);
		
		rt = new RestTemplate();
		rt.setRequestFactory(requestFactory);
	}
	
	@Test
	public void nioHttps() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("https://127.0.0.1:8543"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	@Test
	public void nio2Https() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("https://127.0.0.1:9543"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	@Test
	public void bioHttps() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("https://127.0.0.1:10543"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	@Test
	public void aprHttps() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("https://127.0.0.1:11543"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
}
