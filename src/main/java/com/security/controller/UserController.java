package com.security.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.configs.JwtTokenUtil;
import com.security.dto.request.LoginRequestDto;
import com.security.dto.request.SignupRequestDto;
import com.security.dto.response.LoginSuccessfulResponseDto;
import com.security.enums.eRoles;
import com.security.model.Roles;
import com.security.model.User;
import com.security.repository.RoleRepository;
import com.security.repository.UserRepository;
import com.security.service.UserDetailsImpl;

@RestController
public class UserController {
	
	  @Autowired
	  private AuthenticationManager authenticationManager;
	  @Autowired
	  private JwtTokenUtil jwtTokenUtil;
	  @Autowired
	  private PasswordEncoder encoder;
	  
	  @Autowired
	  private UserRepository userRepository;
	  
	  @Autowired
	  private RoleRepository roleRepository;
	  
	  @PostMapping(value = "/signup/user")
	  public ResponseEntity<?> signupAccount(@RequestBody SignupRequestDto signup){
		    
		     User user = new User();
		     user.setFullName(signup.getUserName());
		     user.setEmail(signup.getEmail());
		     user.setPassword(encoder.encode(signup.getPassword()));
		     
		     Set<Roles> roles = new HashSet<>();
		     for(String role : signup.getRoles()) {
		    	
		    	 if(role.equals("ROLE_ADMIN")) {
		    		Roles selectedRole =  roleRepository.findByName(eRoles.ROLE_ADMIN);
		    		roles.add(selectedRole);
		    	 }
		    	 else if(role.equals("ROLE_MANAGER")) {
		    		 Roles selectedRole =  roleRepository.findByName(eRoles.ROLE_MANAGER);
			    		roles.add(selectedRole);
		    	 }
   	    	    else {
   	    	     Roles selectedRole =  roleRepository.findByName(eRoles.ROLE_GENERAL);
		    		roles.add(selectedRole);
                } 
		     }
		     System.out.println(roles.toString());
		     for(Roles role : roles) {
		    	 System.out.println(role.getName());
		     }
		     
		     user.setRoles(roles);
		     userRepository.save(user);
		     return ResponseEntity.ok("user created succesfully");
	  }
	  
	  @PostMapping(value = "/login")
	  public ResponseEntity<?> signIn(@RequestBody LoginRequestDto login){
		  
		        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		        
		        String jwtToken = jwtTokenUtil.generateJwtToken(authentication);
		        
		        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		       
		        Set<String> assignedRoles = new HashSet<>();
		     
		        for(GrantedAuthority authority : userDetails.getAuthorities()) {
		        	assignedRoles.add(authority.getAuthority());
		        }
		        return ResponseEntity.ok(new LoginSuccessfulResponseDto(jwtToken, userDetails.getUsername(), userDetails.getEmail(), assignedRoles));
		  
	  }

}
