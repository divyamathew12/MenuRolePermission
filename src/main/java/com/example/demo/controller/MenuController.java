package com.example.demo.controller;

import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
		Menu createdMenu = menuService.createMenu(menu);
		return new ResponseEntity<Menu>(createdMenu, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @RequestBody Menu menu) {
		Menu updatedMenu = menuService.updateMenu(id, menu);
		return new ResponseEntity<Menu>(updatedMenu, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
	@GetMapping("/view/{id}")
	public ResponseEntity<Menu> getMenu(@PathVariable Long id) {
		Menu menu = menuService.getMenu(id);
		return new ResponseEntity<Menu>(menu, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
		menuService.deleteMenu(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}