/*
 * <copyright file="AjpOnlyTest.java" company="CWIE, LLC">
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
package org.tcconn.tests;

import java.net.Socket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:johna@ccbill.com">John Anderson</a>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(profiles = {"ajponly"})
@WebIntegrationTest
public class AjpOnlyTest {


	@Test
	public void testAjpConnector() throws Exception {
		assertTrue(this.sendAndReceiveAjp("127.0.0.1", 8980));
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
