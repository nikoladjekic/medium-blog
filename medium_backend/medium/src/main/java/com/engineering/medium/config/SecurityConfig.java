package com.engineering.medium.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private DataSource securityDataSource;
	
	
	/**
	 * Spring security settings with user authentication from database data source.
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	
	/**
	 * Spring security cors filters and level of access definition for every rest controller
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.cors().and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/loginUser/**").authenticated()
        .antMatchers("/user/**").hasRole("ADMIN")
        .antMatchers("/blog/list/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST,"/blog/*").hasAnyRole("ADMIN","EMPLOYEE")
        .antMatchers(HttpMethod.POST,"/domain/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/comment/list/*").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/comment/admin/**").hasRole("ADMIN")
        .antMatchers("/keyword/list/**").hasAnyRole("ADMIN","EMPLOYEE")
        .antMatchers(HttpMethod.POST,"/keyword/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE,"/keyword/**").hasRole("ADMIN")
        .antMatchers("/tag/list/**").hasAnyRole("ADMIN","EMPLOYEE")
        .antMatchers(HttpMethod.POST,"/tag/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE,"/tag/**").hasRole("ADMIN")
        .antMatchers("/logcounter/list/**").hasRole("ADMIN")
		.and()
		.httpBasic()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/medium/domain/home/**")
        .antMatchers("/medium/domain/list/**")
        .antMatchers(HttpMethod.POST, "/comment/*")
        .antMatchers(HttpMethod.GET, "/comment/blog/**");
    }
	
	/**
	 * Cors filter settings
	 * 
	 */
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addExposedHeader(HttpHeaders.AUTHORIZATION);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
	
	@Bean
	public UserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		return jdbcUserDetailsManager;
	}
	
	
	
}
