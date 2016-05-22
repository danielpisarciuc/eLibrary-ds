package com.elibrary.common.dto;

import java.util.List;
import java.util.Objects;

public class BookDto {

    private Long bookId;
    private String isbn;
    private String title;
    private List<BookAuthorDto> bookAuthors;
    private List<BookDetailDto> bookDetails;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public List<BookAuthorDto> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthorDto> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public List<BookDetailDto> getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(List<BookDetailDto> bookDetails) {
        this.bookDetails = bookDetails;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", bookAuthors=" + bookAuthors +
                ", bookDetails=" + bookDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto)) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(getBookId(), bookDto.getBookId()) &&
                Objects.equals(getIsbn(), bookDto.getIsbn()) &&
                Objects.equals(getTitle(), bookDto.getTitle()) &&
                Objects.equals(getBookAuthors(), bookDto.getBookAuthors()) &&
                Objects.equals(getBookDetails(), bookDto.getBookDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getIsbn(), getTitle(), getBookAuthors(), getBookDetails());
    }
}
