package com.arroganteIT.app.persistence.repository;

import com.arroganteIT.app.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

//    @Query("select a from Account a where a.unique_key = :unique_key")
//    Account findByUniqueKey(@Param("unique_key") String uniqueKey);
}
