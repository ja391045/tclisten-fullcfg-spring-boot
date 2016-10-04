/*
 * <copyright file="Application.java" company="John Anderson">
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
 * <date>Sep 30, 2016</date>
 * <summary></summary>
 */
package tclisten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:ja391045@gmail.com">John Anderson</a>
 *
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
