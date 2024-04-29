package com.arroganteIT.app.persistence.repository;

import com.arroganteIT.app.persistence.entity.ExchangeRatesUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ExchangeRatesUserRepository extends JpaRepository<ExchangeRatesUser, Long> {

    @Query("select u from ExchangeRatesUser u where u.name.firstName = :firstName and " +
            "u.name.lastName = :lastName")
    Page<ExchangeRatesUser> findByFirstNameAndLastName(@Param("firstName") String firstName,
                                                       @Param("lastName") String lastName,
                                                       Pageable pageable);

    @Query("select u from ExchangeRatesUser u where lower(u.email) = lower(:email)")
    ExchangeRatesUser retrieveByEmail(@Param("email") String email);

    @Query("select u from ExchangeRatesUser u where u.status = 1")
    Collection<ExchangeRatesUser> findAllActiveUsers();
}
