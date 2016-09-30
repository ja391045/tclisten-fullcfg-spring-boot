/*
 * <copyright file="BasicHttpTests.java" company="CWIE, LLC">
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
 * <date>Aug 12, 2016</date>
 * <summary></summary>
 */
package tclisten.tests;

import static org.junit.Assert.*;

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
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(profiles = {"basichttp"})
@WebIntegrationTest
public class BasicHttpTests {

	private RestTemplate rt;
	
	@Before
	public void setUp(){
		rt = new RestTemplate();
	}
	
	@Test
	public void testDefaultConfig() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("http://127.0.0.1:8182/"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
	}
}
