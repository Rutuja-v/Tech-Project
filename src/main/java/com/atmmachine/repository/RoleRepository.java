package com.atmmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atmmachine.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

}
