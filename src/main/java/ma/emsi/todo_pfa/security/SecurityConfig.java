package ma.emsi.todo_pfa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import ma.emsi.todo_pfa.filter.CustomAuthentificationFilter;


@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//private static final String GET = "get";
	//private static final String POST = "post";
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthentificationFilter customAuthentificationFilter = new CustomAuthentificationFilter(
				authenticationManagerBean());
		customAuthentificationFilter.setFilterProcessesUrl("/user/login");
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/user/login**", "/user/save").permitAll();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		/*
		 * http.authorizeRequests().antMatchers(GET, "/api/farmer",
		 * "/api/farmer/**").authenticated(); http.authorizeRequests().antMatchers(GET,
		 * "/api/users").hasAnyAuthority("ADMIN");
		 * http.authorizeRequests().antMatchers(POST,
		 * "/api/users**").hasAnyAuthority("ADMIN");
		 * http.authorizeRequests().antMatchers(GET,
		 * "/api/espace**").hasAnyAuthority("ADMIN");
		 * http.authorizeRequests().antMatchers(POST,
		 * "/api/espace**").hasAnyAuthority("ADMIN");
		 */
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(customAuthentificationFilter);
		http.addFilterBefore(new ma.emsi.todo_pfa.filter.CustomAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
