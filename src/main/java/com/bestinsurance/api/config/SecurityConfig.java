package com.bestinsurance.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

	/*@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/").permitAll();
			auth.anyRequest().authenticated();
		}).oauth2Login(withDefaults()).formLogin(withDefaults());

		return http.build();
	}*/

	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception {
	 * 
	 * http.authorizeHttpRequests(authz ->
	 * authz.requestMatchers("/authentication/**").permitAll()
	 * .requestMatchers("/h2/**").permitAll().anyRequest().authenticated()).
	 * httpBasic(withDefaults()); return http.build(); }
	 * 
	 * @Bean public InMemoryUserDetailsManager userDetailsService() { UserDetails
	 * user =
	 * User.builder().username("user").password(passwordEncoder().encode("password")
	 * ).roles("USER") .build(); return new InMemoryUserDetailsManager(user); }
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */

}
