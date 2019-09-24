/* Copyright © HatioLab Inc. All rights reserved. */
package com.hatiolab.srm.web.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.hatiolab.srm.config.ModuleProperties;
import com.hatiolab.srm.system.auth.AuthProvider;
import xyz.elidom.sys.config.ModuleConfigSet;
import xyz.elidom.sys.system.auth.AuthProviderFactory;
import xyz.elidom.sys.system.service.api.IEntityFieldCache;
import xyz.elidom.sys.system.service.api.IServiceFinder;

/**
 * Application Startup시 Framework 초기화 작업 
 * 
 * @author shortstop
 */
@Component
public class AppInitializer {

	/**
	 * Logger
	 */
	private Logger logger = LoggerFactory.getLogger(AppInitializer.class);
	
	@Autowired
	@Qualifier("rest")
	private IServiceFinder restFinder;
	
	@Autowired
	private IEntityFieldCache entityFieldCache;
	
	@Autowired
	private ModuleProperties module;
	
	@Autowired
	private ModuleConfigSet configSet;
	
	@Autowired
	private AuthProviderFactory authProviderFactory;	
		
	@EventListener({ ContextRefreshedEvent.class })
	public void ready(ContextRefreshedEvent event) {
		this.logger.info("sample module initializing ready...");
		this.configSet.addConfig(this.module.getName(), this.module);
		this.scanServices();
	}
	
	@EventListener({ApplicationReadyEvent.class})
    void contextRefreshedEvent(ApplicationReadyEvent event) {
		this.logger.info("sample module initializing started...");
		this.addExtensions();
		this.logger.info("sample module initializing finished");
    }
	
	/**
	 * 확장 포인트 추가
	 */
	private void addExtensions() {
		// Auth Provider 교체 
		this.authProviderFactory.setAuthProvider(new AuthProvider());
	}
	
	/**
	 * 모듈 서비스 스캔
	 */
	private void scanServices() {
		this.entityFieldCache.scanEntityFieldsByBasePackage(this.module.getBasePackage());
		this.restFinder.scanServicesByPackage(this.module.getName(), this.module.getBasePackage());
	}
}