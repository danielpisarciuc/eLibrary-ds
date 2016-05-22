package com.elibrary.common.dto;

import java.util.List;
import java.util.Objects;

public class BookDDto {

    private Long bookId;
    private String isbn;
    private String title;
    private List<BookAuthorDtoD> bookAuthors;
    private List<BookDetailDtoD> bookDetails;

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

    public List<BookAuthorDtoD> getBookAuthors() {
        return bookAuthors;
    }

    public void setBookAuthors(List<BookAuthorDtoD> bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public List<BookDetailDtoD> getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(List<BookDetailDtoD> bookDetails) {
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
        if (!(o instanceof BookDDto)) return false;
        BookDDto bookDto = (BookDDto) o;
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
