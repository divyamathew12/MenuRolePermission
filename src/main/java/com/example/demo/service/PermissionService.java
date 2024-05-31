package com.example.demo.service;

//import com.example.demo.entity.ButtonPermission;
//import com.example.demo.repository.PermissionRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PermissionService {
//
//	private final PermissionRepository permissionRepository;
//
//	public PermissionService(PermissionRepository permissionRepository) {
//		this.permissionRepository = permissionRepository;
//	}
//
//	public boolean hasAddPermission(Long menu_roles_id) {
//		return hasPermission(menu_roles_id).isCanAdd();
//	}
//	public boolean hasViewPermission(Long menu_roles_id) {
//		return hasPermission(menu_roles_id).isCanView();
//	}
//
//  	public boolean hasDeletePermission(Long menu_roles_id) {
//		return hasPermission(menu_roles_id).isCanDelete();
//	}
//
//	public boolean hasApprovePermission(Long menu_roles_id) {
//		return hasPermission(menu_roles_id).isCanApprove();
//	}
//
//	private ButtonPermission hasPermission(Long menu_roles_id) {
//		ButtonPermission buttonPermission = permissionRepository.findByMenu_Roles_Id(menu_roles_id);
//  	  	if (buttonPermission == null) {
//	  		throw new RuntimeException("Permission not found for menuId: " + menu_roles_id);
//		}
//		return buttonPermission;
//	}
//}