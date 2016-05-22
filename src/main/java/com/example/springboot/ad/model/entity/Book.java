package com.example.springboot.ad.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private int id;
    private String name;
    private BookDetail bookDetail;
}
