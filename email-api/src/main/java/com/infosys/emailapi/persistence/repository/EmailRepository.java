package com.infosys.emailapi.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.emailapi.constants.Constant;
import com.infosys.emailapi.persistence.domain.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

	@Query(value=Constant.EMAIL_QUERY_GET_BY_USERNAME)
	List<Email> getByUsername(String username);

}
