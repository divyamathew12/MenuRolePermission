package com.example.demo.repository;


import com.example.demo.entity.Menu;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

	 Optional<Menu> findById(Long id);

}
