package com.example.blogclient.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http = http.cors().and().csrf().disable();
		
	    http = http
	    		.sessionManagement()
	    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    		.and();
		
		http.authorizeRequests()
			
			.antMatchers("/").permitAll()
			.antMatchers("/public/**").permitAll()
			.antMatchers("/private/**").hasRole("USER")
			.anyRequest().authenticated()
			.and()
			.formLogin();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

}