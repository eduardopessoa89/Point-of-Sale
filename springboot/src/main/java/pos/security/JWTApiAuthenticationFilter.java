package pos.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/*Filtro onde todas requisições serão capturadas para autenticar*/
public class JWTApiAuthenticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* Estabelece a autenticação para a requisição*/
		
		Authentication authentication = new JWTTokenAuthenticationService()
											.getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);
		
		/*coloca o processo de autenticação no spring security*/
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		/*continua o processo*/
		
		chain.doFilter(request, response);
	}

}
