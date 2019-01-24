package com.infosys.emailapi.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.emailapi.persistence.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
