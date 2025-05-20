package com.cafeselivros.api.repository;

import com.cafeselivros.api.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    // Métodos customizados se necessário
}
