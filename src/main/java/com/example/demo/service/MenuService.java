package com.example.demo.service;

import com.example.demo.entity.Menu;
import com.example.demo.handler.ResourceNotFoundException;
import com.example.demo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;

	public Menu createMenu(Menu menu) {
		return menuRepository.save(menu);
	}

	public Menu getMenu(Long id) {
		try {
			return menuRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Menu not found with id: " + id));
		}
		catch (NoSuchElementException ex) {
			throw new ResourceNotFoundException("Menu not found with id: " + id);
		}
	}

	public Menu updateMenu(Long id, Menu menu) {
		Menu existingMenu = menuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Menu not found with id: " + id));
		existingMenu.setName(menu.getName());
		existingMenu.setUrl(menu.getUrl());
		existingMenu.setParent(menu.getParent());
		menuRepository.save(existingMenu);

		return menu;
	}

	public void deleteMenu(Long id) {
		Menu menu = menuRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Menu not found with id: " + id));
		menuRepository.delete(menu);
	}
}