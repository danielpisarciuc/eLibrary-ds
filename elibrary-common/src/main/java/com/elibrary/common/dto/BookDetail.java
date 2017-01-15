package com.elibrary.common.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class BookDetail {

    @NonNull
    private String language;

    @NonNull
    private String format;

    @NonNull
    private String subject;

    @NonNull
    private Date publicationDate;

    @NonNull
    private String description;

}
