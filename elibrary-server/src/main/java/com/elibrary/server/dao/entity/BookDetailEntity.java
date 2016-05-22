package com.elibrary.server.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "BookDetailEntity")
@Table(name = "BOOK_DETAIL")
@NamedQueries({
        @NamedQuery(
                name = "bookDetails",
                query = "select detail from BookDetailEntity detail where detail.book.id = :bookId"
        )
})

public class BookDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String BOOK_DETAILS = "bookDetails";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LANGUAGE", nullable = false)
    private String language;

    @Column(name = "FORMAT", nullable = false)
    private String format;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "PUBLICATION_DATE")
    private Date publicationDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private BookEntity book;

    public BookDetailEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

}
