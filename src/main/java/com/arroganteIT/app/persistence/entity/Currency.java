package com.arroganteIT.app.persistence.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "currency")
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    @NotNull
    private String code; // currency code (USD, EUR, etc.)

    @Basic
    @Column(name = "rate")
    @NotNull
    private double exchangeRate; // currency exchange rate

    @OneToMany(mappedBy = "account")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (o instanceof Currency) {
            if (((Currency) o).getCode().equals(getCode())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
