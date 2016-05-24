package com.elibrary.server.dao.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "BookAuthorEntity")
@Table(name = "BOOK_AUTHOR")
@NamedQueries({
        @NamedQuery(
                name = "authorBooks",
                query = "select author from BookAuthorEntity author " +
                        "left join fetch author.book where upper(CONCAT(author.firstName, ' ', author.lastName)) = upper(:authorName)"
        ),
})
public class BookAuthorEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    public static final String AUTHOR_BOOKS = "authorBooks";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private BookEntity book;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
