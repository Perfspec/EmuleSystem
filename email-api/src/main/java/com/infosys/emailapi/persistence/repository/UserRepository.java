package com.infosys.emailapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.emailapi.persistence.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
