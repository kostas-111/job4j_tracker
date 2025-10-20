package ru.job4j.tracker.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	private String name;

	@ManyToOne()
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<UserMessenger> messengers = new ArrayList<>();
}
