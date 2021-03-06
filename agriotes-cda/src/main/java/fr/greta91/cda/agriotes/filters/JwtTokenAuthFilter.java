package fr.greta91.cda.agriotes.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.greta91.cda.agriotes.services.JWTResult;
import fr.greta91.cda.agriotes.services.JWTService;




@Component
public class JwtTokenAuthFilter extends OncePerRequestFilter {
	@Autowired
	JWTService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String autorisation = request.getHeader("Authorization");
		System.out.println(autorisation);
		if (autorisation != null && autorisation.startsWith("bearer ")) {
			autorisation = autorisation.substring("bearer ".length());
			System.out.println(autorisation);
			JWTResult parsed = jwtService.checkAutorisation(autorisation);
			if (parsed.isOk()) {
				Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
				if (parsed.isFormateur()) {
					authorities.add(new SimpleGrantedAuthority("ROLE_FORMATEUR"));
					//authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
				}
				else {
					authorities.add(new SimpleGrantedAuthority("ROLE_ETUDIANT"));
				}
				UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(parsed.getLogin(),
						"", authorities);
				SecurityContextHolder.getContext().setAuthentication(result);
			}
		}
		filterChain.doFilter(request, response);
	}

}