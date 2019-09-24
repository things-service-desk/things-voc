/* Copyright © HatioLab Inc. All rights reserved. */
package com.hatiolab.srm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = { "xyz.elidom.*", "com.hatiolab.srm.*" })
@ImportResource({ "classpath:/WEB-INF/application-context.xml", "classpath:/WEB-INF/dataSource-context.xml" })
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}