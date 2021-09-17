package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.enums.eRoles;
import com.security.model.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{
	
	   public Roles findByName(eRoles name);

}
