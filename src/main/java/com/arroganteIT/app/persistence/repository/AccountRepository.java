package com.arroganteIT.app.persistence.repository;

import com.arroganteIT.app.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
