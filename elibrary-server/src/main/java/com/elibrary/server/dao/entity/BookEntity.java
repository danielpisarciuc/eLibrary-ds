package com.elibrary.server.dao.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "BookEntity")
@Table(name = "BOOK")
public class BookEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ISBN", nullable = false)
    private String isbn;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "book")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    private Set<AuthorEntity> authorEntities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    private Set<BookDetailEntity> detailEntities = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<AuthorEntity> getAuthorEntities() {
        return authorEntities;
    }

    public void setAuthorEntities(Set<AuthorEntity> authorEntities) {
        this.authorEntities = authorEntities;
    }

    public Set<BookDetailEntity> getDetailEntities() {
        return detailEntities;
    }

    public void setDetailEntities(Set<BookDetailEntity> detailEntities) {
        this.detailEntities = detailEntities;
    }

}
