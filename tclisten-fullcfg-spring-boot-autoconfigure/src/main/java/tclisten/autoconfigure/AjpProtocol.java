/*
 * <copyright file="AjpProtocols.java" company="John Anderson">
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
public enum AjpProtocol {
	DEFAULT("AJP/1.3"),
	BIO("AjpProtocol"),
	NIO("AjpNioProtocol"),
	NIO2("AjpNio2Protocol"),
	APR("AjpAprProtocol");
	
	
	private final String protocol;
	
	private AjpProtocol(final String protocol){
		this.protocol = protocol;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		if(this == AjpProtocol.DEFAULT)
			return this.protocol;
		return "org.apache.coyote.ajp." + this.protocol;
	}
	
	
}
