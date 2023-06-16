package com.example.demo;

import lombok.*;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Configuration
public class AppConfiguration {
//    @Value("${my.env.myEmail}")
    private String myEmail;
//    @Value("${my.env.myUsername}")
    private String myUsername;
//    @Value("${my.env.devUrl}")
    private String devUrl;
//    @Value("${my.env.prodUrl}")
    private String prodUrl;
}
