package com.elibrary.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@JsonSerialize
public class Book implements Serializable {

    private Long id;

    @NonNull
    private String isbn;

    @NonNull
    private String title;

    private List<Author> bookAuthors = new ArrayList<>();
    private List<BookDetail> bookDetails = new ArrayList<>();

    public boolean hasAuthors() {
        return bookAuthors != null && !bookAuthors.isEmpty();
    }

    public boolean hasDetails() {
        return bookDetails != null && !bookDetails.isEmpty();
    }
}
