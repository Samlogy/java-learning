package com.example.demo;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
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
