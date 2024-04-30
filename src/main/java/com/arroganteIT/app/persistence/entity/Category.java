package com.arroganteIT.app.persistence.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    @Size(min = 2, max = 50)
    @NotNull
    private String name; // category name(news, analytics, review)

    @Column(name = "description", length = 250)
    @Size(min = 10, max = 250)
    @NotNull
    private String description; // category description

    @ToString.Exclude
    @OneToMany(mappedBy = "account")
    private Account account;

    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private List<Article> articlesList;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null) return false;

        if (o instanceof Category) {
            if (((Category) o).getName().equals(getName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
