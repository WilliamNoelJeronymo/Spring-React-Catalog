package com.devsuperior.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.catalog.entites.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>{

}
