/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.utils;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.util.resource.Labels;

import com.stfc.backend.entity.Data;
import com.stfc.backend.entity.Role;

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
			if (roleID != null && roleID.equals(item.getRoleID())) {
				return item.getRoleName();
			}
		}
		return "";
	}

	/**
	 * 
	 * @return
	 */
	public static List<Data> createListTypeBanner() {
		List<Data> listObject = new ArrayList();
		listObject.add(new Data(-1, Labels.getLabel("option")));
		listObject.add(new Data(1, Labels.getLabel("banner.type.logo")));
		listObject.add(new Data(2, Labels.getLabel("banner.type.banner")));

		return listObject;
	}

	/**
	 * 
	 * @param roleID
	 * @return
	 */
	public static String getTypeName(Integer typeID) {
		List<Data> listRole = createListTypeBanner();
		for (Data item : listRole) {
			if (typeID != null && typeID.equals(item.getValue())) {
				return item.getName();
			}
		}
		return "";
	}

}
