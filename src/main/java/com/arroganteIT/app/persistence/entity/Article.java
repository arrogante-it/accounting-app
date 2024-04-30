package com.arroganteIT.app.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "articles")
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 25)
    @Size(min = 2, max = 25)
    @NotNull
    private String title;

    @Column(name = "text", length = 10000)
    @Size(max = 10000)
    @NotNull
    private String text;

    @Column(name = "publish_date")
    @NotNull
    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Category category;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null) return false;

        if (o instanceof Article) {
            if (((Article) o).getTitle().equals(getTitle())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
