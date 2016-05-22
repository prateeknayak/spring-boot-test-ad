package com.example.springboot.ad.controller;

import com.example.springboot.ad.exceptions.ClientNotAuthenticatedException;
import com.example.springboot.ad.exceptions.InvalidTokenException;
import com.example.springboot.ad.exceptions.TokenAlreadyExistsException;
import com.example.springboot.ad.model.entity.Book;
import com.example.springboot.ad.model.request.UIAuthenticationRequest;
import com.example.springboot.ad.model.security.AuthToken;
import com.example.springboot.ad.service.ADAuthService;
import com.example.springboot.ad.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ADAuthService authenticationService;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST)
    public Map<String, String> getLogin(@RequestBody UIAuthenticationRequest request) throws TokenAlreadyExistsException {
        AuthToken authToken = authenticationService.authenticateUser(request);
        defaultMapToReturn.put("token", authToken.getTokenValue());
        return defaultMapToReturn;
    }

    @PreAuthorize("hasAuthority('ROLE_DOMAIN_USER')")
    @RequestMapping(
            value = "/books",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, Book> getListOfBooks() {
       return bookService.getBooksMap();
    }

//    @RequestMapping(
//            value = "/gen",
//            method = RequestMethod.GET)
//    public Map<String, String> generate() {
//        bookService.generateSomeData();
//        return defaultMapToReturn;
//    }


//    @RequestMapping(
//            value = "/principal",
//            method = RequestMethod.GET)
//    public Map<String, String> checkPrincipal(Authentication authentication) {
//
//        log.info("Trying to debug principal");
//        return defaultMapToReturn;
//    }



//    @PreAuthorize("@ADAuthService.verifyAuthentication(#token)")
    @RequestMapping(
            value = "/bookAuth",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, Book> getAuthentication(@RequestHeader("X-AUTH") String token) throws ClientNotAuthenticatedException {
        authenticationService.verifyAuthentication(token);
        return bookService.getBooksMap();
    }

    @RequestMapping(
            value = "/invalidateToken",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> invalidateToken(@RequestHeader("X-AUTH") String token, Authentication authentication) throws InvalidTokenException, ClientNotAuthenticatedException {
        authenticationService.verifyAuthentication(token);
        authenticationService.logoutUser(token);
        return defaultMapToReturn;
    }


    @ExceptionHandler(value =   {ClientNotAuthenticatedException.class, ServletRequestBindingException.class, IllegalArgumentException.class})
    public Map<String, String> authenticationFailure(Exception ex) {
        log.error("{} , {} ", ex.getMessage(), ex.getCause());
        defaultMapToReturn.put("DefaultString", "Client not authenticated please login");
        return defaultMapToReturn;
    }
}
