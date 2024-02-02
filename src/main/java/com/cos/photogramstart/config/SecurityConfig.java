package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

// TODO : 시큐리티 최신버전
@RequiredArgsConstructor
@Configuration // IoC
public class SecurityConfig {

	private final OAuth2DetailsService oAuth2DetailsService;

	@Bean
	BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.csrf(c -> c.disable());

		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
				.authenticated().anyRequest().permitAll());

		http.formLogin(form -> form.loginPage("/auth/signin").loginProcessingUrl("/auth/signin").defaultSuccessUrl("/"));

		http.oauth2Login(oauth -> oauth.userInfoEndpoint(end -> end.userService(oAuth2DetailsService)));

		return http.build();
	}

}
 