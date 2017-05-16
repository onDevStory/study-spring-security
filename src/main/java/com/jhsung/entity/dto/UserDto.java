package com.jhsung.entity.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

public class UserDto {

	@Data
	public static class Email {
		@NotBlank
		@Pattern(regexp = "^[a-z0-9_+.-]+@+([a-z0-9-]+\\.)+[a-z0-9]{2,4}$")
		private String email;
	}

	@Data
	public static class ForCreate {
		@NotBlank
		@Pattern(regexp = "^[a-z0-9_+.-]+@+([a-z0-9-]+\\.)+[a-z0-9]{2,4}$")
		private String email;

		@NotBlank
		@Size(min = 6, max = 15)
		// validation
		private String password;

		@NotBlank
		// validation
		private String userName;
	}
	
	@Data
	public static class SignIn {
		private String email;
		private String password;
	}

	@Data
	public static class ExceptPassword {
		private Long id;
		private String email;
		private String userName;
		private boolean verified;
		private Date created;
		private Date updated;
	}

}
