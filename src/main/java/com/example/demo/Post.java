package com.example.demo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data // generates getters, setters, toString, equals, hashCode
//@Entity
public class Post {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String createdAt;

    // Constructors, getters, and setters
}
