package com.cafeselivros.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String googleBooksId;
    private String title;
    private List<String> authors;
    private String description;
    private Integer pageCount;
    private String language;
    private String publishedDate;
    private String infoLink;
    private String previewLink;
}
