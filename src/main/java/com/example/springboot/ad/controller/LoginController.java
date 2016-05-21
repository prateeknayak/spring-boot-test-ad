package com.example.springboot.ad.controller;

import com.example.springboot.ad.service.BookService;
import com.example.springboot.ad.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    private static Map<String, String> defaultMapToReturn = new HashMap<>();
    static {
        defaultMapToReturn.put("DefaultString", "It Is working");
    }

    @Autowired
    private BookService bookService;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET)
    public Map<String, String> getLogin() {
       return defaultMapToReturn;
    }

    @PreAuthorize("hasAuthority('ROLE_DOMAIN_USER')")
    @RequestMapping(
            value = "/books",
            method = RequestMethod.GET)
    public Map<Integer, Book> getListOfBooks() {
       return bookService.getBooksMap();
    }

    @RequestMapping(
            value = "/gen",
            method = RequestMethod.GET)
    public Map<String, String> generate() {
        bookService.generateSomeData();
        return defaultMapToReturn;
    }

}
