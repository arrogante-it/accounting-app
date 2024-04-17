package com.arroganteIT.app.persistence.entity;

import com.arroganteIT.app.persistence.embeddableClasses.Address;
import com.arroganteIT.app.persistence.embeddableClasses.UserName;
import com.arroganteIT.app.persistence.enums.Gender;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ex_user")
@Data
public class ExchangeRatesUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Size(max = 1)
    @NotNull
    private int status;

//    @Convert(converter = UserNameConverter.class)
//    private UserNameFields name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", length = 75)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name", length = 75))
    })
    private UserName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "address_country", length = 50)),
            @AttributeOverride(name = "city", column = @Column(name = "address_city", length = 50)),
            @AttributeOverride(name = "street", column = @Column(name = "address_street", length = 50))
    })
    private Address address;


    @Basic
    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @Basic
    @NotNull
    private String email;

    @NotNull
    private LocalDate birthday;

    @Basic
    @Transient
    @NotNull
    private int age;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    @OneToMany(mappedBy = "exuser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (o instanceof ExchangeRatesUser) {
            if (((ExchangeRatesUser) o).getEmail().equals(getEmail())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hashCode(email);
    }
}
