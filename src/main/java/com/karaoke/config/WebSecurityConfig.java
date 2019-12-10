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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.karaoke.common.Contants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
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
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("*")
	                    .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
	        } 
	    };
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

			//httpSecurity.cors().and().authorizeRequests()
			httpSecurity.csrf().disable().authorizeRequests()
				.antMatchers("/customer","/getAllVip","/findUserByToken", "/getOrderByRoom", "/addNewOrderItem",
						"/findUserByPhone")
				.hasAnyAuthority(Contants.ROLE_ADMIN, Contants.ROLE_STAFF, Contants.ROLE_CUSTOMER, Contants.ROLE_CASHIER)
				
				.antMatchers("/payment").hasAnyAuthority(Contants.ROLE_ADMIN, Contants.ROLE_CASHIER)
				
				.antMatchers("/admin", "/getUserById", "/getAllUser", "/addNewUser", "/updateUser", "/addNewVip",
						"/updateVip", "/addNewCategory", "/updateCategory", "/addNewRoom", "/updateRoom",
						"/addNewItem", "/updateItem", "/pay", "/getTotalMonth",
						"/updateUserForAdmin", "/updateImageVip", "/search", "/updateImageCategory",
						"/updateImageItem","/uploadImageRoom").hasAuthority(Contants.ROLE_ADMIN)

				.antMatchers("/", "/authenticate", "/register",  "/findVipById", "/getAllCategory", "/getAllRoom", "/changeStatusRoom",
						"/getAllItem", "/getAllOrders", "/addNewOrder", "/updateOrder", "/getOrderByName",
						 "/getAllOrderItem", "/addNewOrderItem", "/updateOrderItem",
						"/confirm-account", "/topic/public", "/topic/**", "/ws/**", "/chat.addUser",
						"/chat.sendMessage").permitAll()
				
				.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);


		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		httpSecurity.cors();

	}
	
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}