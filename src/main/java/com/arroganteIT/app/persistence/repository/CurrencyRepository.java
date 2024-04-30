package com.arroganteIT.app.persistence.repository;

import com.arroganteIT.app.persistence.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    @Query("select c from Currency c where c.code = :code")
    Currency findByCode(@Param("code") String code);
}
