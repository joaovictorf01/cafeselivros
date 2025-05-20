package com.cafeselivros.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "googleBooksClient", url = "https://www.googleapis.com/books/v1")
public interface GoogleBooksClient {

    @GetMapping("/volumes")
    Map<String, Object> searchBooks(@RequestParam("q") String query, @RequestParam(value = "key", required = false) String apiKey);

    @GetMapping("/volumes/{id}")
    Map<String, Object> getBookById(@PathVariable("id") String id, @RequestParam(value = "key", required = false) String apiKey);
}
