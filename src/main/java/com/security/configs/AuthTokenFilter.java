package com.security.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
  		    String jwtToken = parseToken(request);
		    try {
		    	if(jwtToken != null && jwtTokenUtil.validateJwtToken(jwtToken)) {		    		
			    	String userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
			    	UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			    	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		    	    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    	    SecurityContextHolder.getContext().setAuthentication(authentication);
		    	}
		    	
		    }
		    catch(Exception ex) {
		    	logger.error("Cannot set user authentication: {}", ex);
		    }
		    filterChain.doFilter(request, response);
		
	}

	private String parseToken(HttpServletRequest request) {
		    String header = request.getHeader("Authorization");
		    if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
		    	return header.substring(7, header.length());
		    }	
		    return null;
	} 
}
