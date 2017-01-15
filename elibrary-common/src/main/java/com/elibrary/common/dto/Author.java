package com.elibrary.common.dto;


import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Author implements Serializable {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

}
