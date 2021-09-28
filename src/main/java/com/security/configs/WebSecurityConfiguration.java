package com.security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	   @Autowired
	   private AuthTokenFilter authTokenFilter;
	   @Autowired
	   private UserDetailsServiceImpl userDetailsService;
	   @Autowired
	   private AuthEntryPointJwt authEntryPointJwt;
	
	   @Override
	   public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		      BCryptPasswordEncoder encoder = passwordEncoder();
//		       authenticationManagerBuilder.inMemoryAuthentication()
//		       .withUser("saeed")
//		       .password(encoder.encode("s123"))
//		       .authorities(new SimpleGrantedAuthority("admin"));
		       authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	   }
	   @Override
	   public void configure(HttpSecurity http) throws Exception {
		      http
		      .cors()
		      .and()
		      .csrf().disable()
		      .exceptionHandling().authenticationEntryPoint(authEntryPointJwt)
		      .and()
		      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		      .and()
		      .authorizeRequests().antMatchers("/login/**").permitAll()
		      .antMatchers("/signup/**").permitAll()
		      .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		      .antMatchers("/manager/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
		      .antMatchers("/employee/**").hasAuthority("ROLE_EMPLOYEE")
		      .antMatchers("/everyone/**").permitAll()
		      .anyRequest().authenticated();
		      http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
	   }
	   
	   @Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	   @Bean
	   public BCryptPasswordEncoder passwordEncoder() {
		      return new BCryptPasswordEncoder();
	   }
}
