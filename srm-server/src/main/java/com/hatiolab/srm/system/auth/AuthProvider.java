/* Copyright © HatioLab Inc. All rights reserved. */
package com.hatiolab.srm.system.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xyz.elidom.base.BaseConfigConstants;
import xyz.elidom.sec.entity.Role;
import xyz.elidom.sec.system.auth.SecAuthProvider;
import xyz.elidom.stomp.util.StompUtil;
import xyz.elidom.sys.entity.Domain;
import xyz.elidom.sys.entity.User;
import xyz.elidom.sys.util.SettingUtil;
import xyz.elidom.sys.util.ValueUtil;

/**
 * 인증을 사용하는 인증 기능 프로바이더
 *
 * @author shortstop
 */
public class AuthProvider extends SecAuthProvider {

	@Override
	public Object sessionUserInfo(Domain currentDomain, User user) {
		user.setDomainId(currentDomain.getId());
		Map<String, Object> sessionInfo = this.userToMap(user);
		Map<String, Object> domainInfo = this.domainToMap(currentDomain);
		sessionInfo.put("domain", domainInfo);

		List<Role> roles = Role.getRoles(user.getId());
		List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>(3);
		for(Role role : roles) {
			roleList.add(ValueUtil.newMap("id,name,description", role.getId(), role.getName(), role.getDescription()));
		}

		sessionInfo.put("roles", roleList);
		return sessionInfo;
	}

	/**
	 * 사용자 객체를 Map으로 변환
	 *
	 * @param user 사용자 정보
	 * @return
	 */
	private Map<String, Object> userToMap(User user) {
		Map<String, Object> sessionInfo = ValueUtil.newMap(
				"id,domain_id,login,email,name,dept,division,locale,super_user,admin_flag,operator_flag,exclusive_role",
				user.getId(),
				user.getDomainId(),
				user.getLogin(),
				user.getEmail(),
				user.getName(),
				user.getDept(),
				user.getDivision(),
				user.getLocale(),
				user.getSuperUser(),
				user.getAdminFlag(),
				user.getOperatorFlag(),
				user.getExclusiveRole());

		StringBuilder stompUrl = new StringBuilder();
		stompUrl.append("http://");
		stompUrl.append(StompUtil.getClientStompUrl());
		stompUrl.append("/elidom/stomp");
		sessionInfo.put("stomp_url", stompUrl.toString());
		return sessionInfo;
	}

	/**
	 * 도메인 객체를 Domain으로 변환
	 *
	 * @param domain
	 * @return
	 */
	private Map<String, Object> domainToMap(Domain domain) {
		Map<String, Object> domainInfo = ValueUtil.newMap("id,name,brandName,theme", domain.getId(), domain.getName(), domain.getBrandName(), domain.getTheme());
		domainInfo.put("file_service_base_url", SettingUtil.getValue(BaseConfigConstants.FILE_SERVICE_PATH));
		return domainInfo;
	}

}
