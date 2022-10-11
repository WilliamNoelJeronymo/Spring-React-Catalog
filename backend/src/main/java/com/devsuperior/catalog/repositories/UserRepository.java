package com.devsuperior.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.catalog.entites.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

}
