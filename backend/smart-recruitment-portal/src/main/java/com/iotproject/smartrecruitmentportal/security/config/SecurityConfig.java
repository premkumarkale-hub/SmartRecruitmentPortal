package com.iotproject.smartrecruitmentportal.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.iotproject.smartrecruitmentportal.security.CustomAuthenticationEntryPoint;
import com.iotproject.smartrecruitmentportal.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
			.csrf(csrf -> csrf.disable())
			
			.sessionManagement(Session ->
					Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/api/auth/**",
									 "/api/users/register"
					).permitAll()
					.anyRequest().authenticated()
			)
			.exceptionHandling(exception -> 
					exception.authenticationEntryPoint(authenticationEntryPoint))
			.addFilterBefore(
					jwtAuthenticationFilter, 
					UsernamePasswordAuthenticationFilter.class
			);
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
}
