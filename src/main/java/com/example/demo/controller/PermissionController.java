package com.example.demo.controller;

//import com.example.demo.entity.Menu;
//import com.example.demo.entity.Menu_Roles;
//import com.example.demo.service.PermissionService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/permissions")
//public class PermissionController {
//
//	private final PermissionService permissionService;
//
//	public PermissionController(PermissionService permissionService) {
//		this.permissionService = permissionService;
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PostMapping("/add/{menu_roles_id}")
//	public ResponseEntity<Boolean> hasAddPermission(@PathVariable Menu_Roles menu_roles) {
//		boolean hasPermission = permissionService.hasAddPermission(menu_roles.getId());
//		return ResponseEntity.ok(hasPermission);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
//	@GetMapping("/view/{menu_roles_id}")
//	public ResponseEntity<Boolean> hasViewPermission(@PathVariable Menu_Roles menu_roles) {
//		boolean hasPermission = permissionService.hasViewPermission(menu_roles.getId());
//		return ResponseEntity.ok(hasPermission);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@DeleteMapping("/delete/{menu_roles_id}")
//	public ResponseEntity<Boolean> hasDeletePermission(@PathVariable Menu_Roles menu_roles) {
//		boolean hasPermission = permissionService.hasDeletePermission(menu_roles.getId());
//		return ResponseEntity.ok(hasPermission);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_SUPERADMIN)")
//	@GetMapping("/approve/{menu_roles_id}")
//	public ResponseEntity<Boolean> hasApprovePermission(@PathVariable Menu_Roles menu_roles) {
//		boolean hasPermission = permissionService.hasApprovePermission(menu_roles.getId());
//		return ResponseEntity.ok(hasPermission);
//	}
//
//}