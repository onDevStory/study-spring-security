package com.jhsung.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.jhsung.entity.mapped.MappedSuperclassTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends MappedSuperclassTimestamp {

	@Id
	@GeneratedValue
	@Column(name = "userId")
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String userName;

	private boolean verified;

	// secured userInfo
	public static User secured(User user) {
		user.setId(null);
		user.setPassword(null);
		return user;
	}

}
