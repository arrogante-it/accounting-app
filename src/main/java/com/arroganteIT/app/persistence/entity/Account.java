package com.arroganteIT.app.persistence.entity;

import com.arroganteIT.app.persistence.enums.AccountStatus;
import com.arroganteIT.app.persistence.enums.AccountType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_key")
    @NotNull
    private String uniqueKey;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountType accountType;

    @Basic
    @Column(columnDefinition = "decimal default 0")
    private double balance;

    @Column(name = "creating_date")
    @NotNull
    private LocalDate creatingDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private AccountStatus accountStatus;

//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "ex_user_id")
//    private ExchangeRatesUser exuser;

    @OneToOne
    @JoinColumn(name = "ex_user_id")
    private ExchangeRatesUser exuser;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private List<Currency> currencyList;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    private List<Category> categoryList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (o instanceof Account) {
            if (((Account) o).getUniqueKey().equals(getUniqueKey())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(uniqueKey);
    }
}
