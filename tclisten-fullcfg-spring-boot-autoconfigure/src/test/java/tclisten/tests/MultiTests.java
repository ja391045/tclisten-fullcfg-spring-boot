/*
 * <copyright file="MultiTests.java" company="John Anderson">
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

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
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
@ActiveProfiles(profiles = {"testmulti"})
@WebIntegrationTest
public class MultiTests {

	private RestTemplate rt;
	
	@Before
	public void setUp(){
		rt = new RestTemplate();
	}
	
	@Test
	public void testMultiConfig() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("http://127.0.0.1:8183/"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
		resp = rt.getRequestFactory().createRequest(new URI("http://127.0.0.1:8184"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
		resp = rt.getRequestFactory().createRequest(new URI("http://127.0.0.1:8185"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
		
		
	}
}
