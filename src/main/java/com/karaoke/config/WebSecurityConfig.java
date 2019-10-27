package com.karaoke.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.karaoke.common.Contants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable().
				authorizeRequests().antMatchers("/customer").hasAnyAuthority(Contants.ROLE_ADMIN,
						Contants.ROLE_CUSTOMER).
				antMatchers("/authenticate", "/register").permitAll().
				
				antMatchers("/admin","/getUserById", "/getAllUser", "/updateUser", "/updateVip",
						"/addNewCategory", "/updateCategory", "/addNewRoom", "/updateRoom",
						"/addNewItem", "/updateItem", "/pay").hasAuthority(Contants.ROLE_ADMIN).
				
				antMatchers("/", "/getAllVip", "/findVipById", "/getAllCategory",
						"/getAllRoom", "/changeStatusRoom", "/getAllItem",
						"/getAllOrders", "/addNewOrder", "/updateOrder",
						"/getOrderByName", "/getTotalMonth", "/getAllOrderItem",
						"/addNewOrderItem", "/updateOrderItem",
						"/confirm-account", "/topic/public", "/topic/**", "/ws/**",
						"/chat.addUser", "/chat.sendMessage").permitAll().
				anyRequest().authenticated().and().
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().headers()
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
	            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET"))
	            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
	            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
	            .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"))
				;

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}