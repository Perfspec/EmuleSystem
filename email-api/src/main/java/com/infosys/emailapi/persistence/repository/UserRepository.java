package com.infosys.emailapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.emailapi.persistence.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
