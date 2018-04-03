/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.website.backend.utils;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.util.resource.Labels;

import com.stfc.website.backend.entity.Object;
import com.stfc.website.backend.entity.Role;

/**
 *
 * @author viettx
 */
public class FunctionUtil {

	/**
	 *
	 * @return
	 */
	public static List<Role> createDataRole() {
		List<Role> listRole = new ArrayList();

		listRole.add(new Role(-1, Labels.getLabel("option")));
		listRole.add(new Role(0, Labels.getLabel("user.role.super.admin")));
		listRole.add(new Role(1, Labels.getLabel("user.role.admin")));
		listRole.add(new Role(2, Labels.getLabel("user.role.editor")));
		listRole.add(new Role(3, Labels.getLabel("user.role.author")));
		listRole.add(new Role(4, Labels.getLabel("user.role.contributor")));
		listRole.add(new Role(5, Labels.getLabel("user.role.subscriber")));

		return listRole;
	}

	/**
	 * 
	 * @param roleID
	 * @return
	 */
	public static String getRoleName(Integer roleID) {
		List<Role> listRole = createDataRole();
		for (Role item : listRole) {
			if (roleID.equals(item.getRoleID())) {
				return item.getRoleName();
			}
		}
		return "";
	}

	/**
	 * 
	 * @return
	 */
	public static List<Object> createListTypeBanner() {
		List<Object> listObject = new ArrayList();
		listObject.add(new Object(-1, Labels.getLabel("option")));
		listObject.add(new Object(1, Labels.getLabel("banner.type.logo")));
		listObject.add(new Object(2, Labels.getLabel("banner.type.banner")));

		return listObject;
	}

	/**
	 * 
	 * @param roleID
	 * @return
	 */
	public static String getTypeName(Integer roleID) {
		List<Object> listRole = createListTypeBanner();
		for (Object item : listRole) {
			if (roleID.equals(item.getValue())) {
				return item.getName();
			}
		}
		return "";
	}

}
