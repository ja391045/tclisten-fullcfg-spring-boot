/*
 * <copyright file="SslTests.java" company="John Anderson">
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
 * <date>Sep 20, 2016</date>
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
@ActiveProfiles(profiles = {"ssl"})
@WebIntegrationTest
public class SslTests {
	
	private RestTemplate rt;
	
	@Before
	public void setUp() throws Exception {
		
		String[] sslProto = {"TLSv1.2"};
		NoopHostnameVerifier hnVerify = new NoopHostnameVerifier();
		SSLContext sslCtx = SSLContexts.custom().loadTrustMaterial(
			new File("src/test/resources/truststore.jks"), 
			"changeme".toCharArray(),
			new TrustSelfSignedStrategy()
		).useProtocol(sslProto[0]).build();
		SSLConnectionSocketFactory sslSf = new SSLConnectionSocketFactory(
			sslCtx,
			sslProto,
			null,
			hnVerify
		);
		
		HttpClient client = HttpClients.custom().setSSLSocketFactory(sslSf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = 
				new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(client);
		
		rt = new RestTemplate();
		rt.setRequestFactory(requestFactory);
	}
	
	@Test
	public void nioHttps() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("https://127.0.0.1:8443"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	@Test
	public void nio2Https() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("https://127.0.0.1:9443"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	@Test
	public void bioHttps() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("https://127.0.0.1:10443"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
	
	@Test
	public void aprHttps() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("https://127.0.0.1:11443"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}

}
