/*
 * <copyright file="AutoconfigureTests.java" company="CWIE, LLC">
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
 * <date>Aug 11, 2016</date>
 * <summary></summary>
 */
package org.tcconn.tests;

import java.net.Socket;
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

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(profiles = {"testdefault"})
@WebIntegrationTest
public class AutoconfigureTests {

	private RestTemplate rt;
	
	@Before
	public void setUp(){
		rt = new RestTemplate();
	}
	
	@Test
	public void testDefaultConfig() throws Exception {
		ClientHttpResponse resp = rt.getRequestFactory().createRequest(new URI("http://localhost:8080/"), HttpMethod.GET).execute();
		assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
		// I don't have an AJP client, so I'll have to just peek at the socket.
		assertTrue(this.sendAndReceiveAjp("localhost", 8009));	
	}
	
	
	private boolean sendAndReceiveAjp(String hostname, int port) throws Exception {
		byte[] cPing = { 0x12, 0x34, 0x00, 0x01, 0x0A };
		byte[] received = new byte[5];
		int rSz = 0;
		
		Socket client = new Socket(hostname, port);
		client.getOutputStream().write(cPing);
		rSz = client.getInputStream().read(received, 0, 5);
		client.close();
		if(rSz != 5)
			return false;
		//Check for successful CPONG;
		if(received[4] != 0x09)
			return false;
		return true;
	}
}
