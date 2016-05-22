package com.example.springboot.ad.service;

import com.example.springboot.ad.model.entity.Book;
import com.example.springboot.ad.model.entity.BookDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Getter
@Setter
public class BookService {

    private static Map<Integer, Book> booksMap = new HashMap<>();

    public void generateSomeData() {
        for (int i = 0; i < 20; i++) {
            booksMap.put(i, new Book(i, "Book " + i, new BookDetail(i, i*20)));
        }
    }

    public void addBookToMap(Book b) throws Exception {
        if (null != booksMap.get(b.getId())) {
            throw new Exception("Book with ID: " + b.getId() + " already exists in the records");
        }
        booksMap.put(b.getId(), b);
    }

    public Book getBookFromMap(int id) {
        return booksMap.get(id);
    }

    public Map<Integer, Book> getBooksMap() {
        if (0 == booksMap.size()) {
            generateSomeData();
        }
        return booksMap;
    }
}
