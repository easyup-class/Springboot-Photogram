package com.cos.photogramstart.web.dto.auth;



import com.cos.photogramstart.domain.user.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // Getter, Setter
public class SignupDto {
	// https://bamdule.tistory.com/35 (@Valid 어노테이션 종류)
	@Size(min = 2, max = 20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
