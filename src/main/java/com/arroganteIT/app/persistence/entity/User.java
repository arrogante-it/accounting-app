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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Convert(converter = UserNameConverter.class)
//    private UserNameFields userName;

    @Basic
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name", length = 75)),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name", length = 75))
    })
    private UserName userName;

    @Basic
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
    @Column
    @NotNull
    private String email;

    @Basic
    @Column
    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate birthday;

    @Basic
    @Column
    @Transient
    @NotNull
    private int age;

    @Basic(optional = false)
    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @ToString.Exclude
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null) return false;

        if (o instanceof User) {
            if (((User) o).getEmail().equals(getEmail())) {
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
