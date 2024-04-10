package com.arroganteIT.app.persistence.repository;

import com.arroganteIT.app.persistence.entity.Account;
import com.arroganteIT.app.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    void save();

    void delete();

    Optional<Account> findById(Long id);

    List<Account> findAll();

    @Query("select a from Account a where a.unique_key = :unique_key")
    User findByUniqueKey(@Param("unique_key") String uniqueKey);
}
