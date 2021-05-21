package fr.greta91.cda.agriotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.greta91.cda.agriotes.filters.JwtTokenAuthFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	JwtTokenAuthFilter jwtTokenAuthFilter;
	
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		System.out.println(bcryptPasswordEncoder().encode("User1000@"));
		System.out.println(bcryptPasswordEncoder().encode("Employe1000@"));
		
		// On désactive la protection contre les CSRF.
		// Dans l'état actuel du logiciel, elle est inutile
		// Le probléme est que cette protection est stateful...
		http.csrf().disable();
		// on autorise cors
		http.cors();//cross origin request sharing
		// pas de session, on utilise JWT pour ça...
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// on se débarrasse de la sécurité par défaut.
		http.httpBasic().disable();
		// on désactive aussi la page de login
		http.formLogin().disable();	//chain of reponsibility
		// on met en place le filtre qui va récupérer le token s'il existe et authentifier l'utilisateur.
		http.addFilterBefore(jwtTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
//		// régles d'accès proprement dites
//		// vu leur tête, on pourrait aussi les placer au niveau des méthodes, comme annotations.
		http.authorizeRequests()
			.mvcMatchers(HttpMethod.GET, "/api/public/**").permitAll()
			.mvcMatchers(HttpMethod.POST, "/api/auth/login").permitAll()		
			.mvcMatchers(HttpMethod.POST, "/api/auth/register").permitAll()		
//			.mvcMatchers(HttpMethod.POST, "/api/employe/produits/**").permitAll()
			.mvcMatchers(HttpMethod.GET, "/api/formateur/**").access("hasRole('ROLE_FORMATEUR')")
			.mvcMatchers(HttpMethod.POST, "/api/formateur/**").access("hasRole('ROLE_FORMATEUR')")
			.mvcMatchers(HttpMethod.PUT, "/api/formateur/**").access("hasRole('ROLE_FORMATEUR')")
			.mvcMatchers(HttpMethod.DELETE, "/api/formateur/**").access("hasRole('ROLE_FORMATEUR')")
			
			
			.mvcMatchers("/api/**").authenticated();
	}
	
}