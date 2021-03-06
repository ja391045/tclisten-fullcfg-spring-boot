/*
 * <copyright file="HttpProtocol.java" company="John Anderson">
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

/**
 * @author <a href="mailto:ja391045@gmail.com">John Anderson</a>
 *
 */
public enum HttpProtocol {
	DEFAULT("HTTP/1.1"),
	BIO("Http11Protocol"),
	NIO("Http11NioProtocol"),
	NIO2("Http11Nio2Protocol"),
	APR("Http11AprProtocol");
	
	private final String protocol;
	
	private HttpProtocol(final String protocol){
		this.protocol = protocol;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		if(this == HttpProtocol.DEFAULT)
			return this.protocol;
		return "org.apache.coyote.http11." + this.protocol;
	}
}
