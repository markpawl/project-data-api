package com.webage.filter;

import java.io.IOException;
import java.security.Key;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.webage.logging.ApiLogger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
@Order(1)
public class AuthFilter implements Filter{

	public static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private String auth_scope = "com.webage.auth.apis";
	private String api_scope = "com.webage.data.apis";
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// get authorization header
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		
		if( uri.startsWith("/api/token") || uri.startsWith("/api/register")) {
			chain.doFilter(request, response);
			return;			
		}else{
			String authheader = req.getHeader("authorization");
			if(authheader != null && authheader.length() > 7 && authheader.startsWith("Bearer")) {
				String jwt_token = authheader.substring(7, authheader.length());
				if(verifyToken(jwt_token)) {
					String request_scopes = getScopes(jwt_token);
					if(request_scopes.contains(api_scope) || request_scopes.contains(auth_scope)) {
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}		
		// continue
		res.sendError(HttpServletResponse.SC_FORBIDDEN, "failed authentication");

	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ApiLogger.log("AuthFilter.init");
		
	}

	@Override
	public void destroy() {
		ApiLogger.log("AuthFilter.destroy");	
	}

    public boolean verifyToken(String token) {
    	try {
    	    Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    	    return true;
    	} catch (JwtException e) {
    		return false;
    	}    	
    }
    
    public String getScopes(String token) {
    	try {
    	    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    	    String scopes = claims.get("scopes", String.class);
    	    return scopes;
    	} catch (JwtException e) {
    		return null;
    	}    	
    } 	
	
}
