package com.iotproject.smartrecruitmentportal.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.iotproject.smartrecruitmentportal.security.CustomAccessDeniedHandler;
import com.iotproject.smartrecruitmentportal.security.CustomAuthenticationEntryPoint;
import com.iotproject.smartrecruitmentportal.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomAuthenticationEntryPoint authenticationEntryPoint;
	private final CustomAccessDeniedHandler accessDeniedHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
			.csrf(csrf -> csrf.disable())
			
			.sessionManagement(Session ->
					Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
			.authorizeHttpRequests(auth -> auth
					
					// Public Endpoints
					.requestMatchers("/api/auth/**",
									 "/api/users/register"
					)
					.permitAll()
					
					// Company Module
					.requestMatchers(HttpMethod.POST, "/api/companies/**")
						.hasRole("ADMIN")
						
					.requestMatchers(HttpMethod.PUT, "/api/companies/**")
						.hasRole("ADMIN")
						
					.requestMatchers( HttpMethod.DELETE, "/api/companies/**")
						.hasRole("ADMIN")
						
					.requestMatchers(HttpMethod.GET, "/api/companies/**")
						.hasAnyRole("ADMIN", "CANDIDATE", "RECRUITER")
							
					// Job Module
					.requestMatchers(HttpMethod.POST, "/api/jobs/**")
						.hasAnyRole("ADMIN", "RECRUITER")
							
					.requestMatchers(HttpMethod.PUT, "/api/jobs/**")
						.hasAnyRole("ADMIN", "RECRUITER")
							
					.requestMatchers( HttpMethod.DELETE, "/api/jobs/**")
						.hasAnyRole("ADMIN", "RECRUITER")
							
					.requestMatchers(HttpMethod.GET, "/api/jobs/**")
						.hasAnyRole("ADMIN", "CANDIDATE", "RECRUITER")
					
					.anyRequest().authenticated()
			)
			.exceptionHandling(exception -> 
					exception.authenticationEntryPoint(authenticationEntryPoint)
							  .accessDeniedHandler(accessDeniedHandler))
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
