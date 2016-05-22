package com.elibrary.common.dto;

import java.util.Date;
import java.util.Objects;

public class BookDetailDtoD {

    private Long id;
    private String language;
    private String format;
    private String subject;
    private Date publicationDate;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "BookDetailDto{" +
                "language='" + language + '\'' +
                ", format='" + format + '\'' +
                ", subject='" + subject + '\'' +
                ", publicationDate=" + publicationDate +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDetailDtoD)) return false;
        BookDetailDtoD that = (BookDetailDtoD) o;
        return Objects.equals(getLanguage(), that.getLanguage()) &&
                Objects.equals(getFormat(), that.getFormat()) &&
                Objects.equals(getSubject(), that.getSubject()) &&
                Objects.equals(getPublicationDate(), that.getPublicationDate()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLanguage(), getFormat(), getSubject(), getPublicationDate(), getDescription(), getId());
    }
}
