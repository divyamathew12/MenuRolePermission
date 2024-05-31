package com.example.demo.entity;

import com.example.demo.token.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "button_permission")
public class ButtonPermission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean canAdd;

	private boolean canView;

	private boolean canEdit;

	private boolean canDelete;

	private boolean canApprove;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "menu_roles_id", referencedColumnName = "id"),
			@JoinColumn(name = "menu_id", referencedColumnName = "menu_id"),
			@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	})
	private Menu_Roles menu_roles;


}