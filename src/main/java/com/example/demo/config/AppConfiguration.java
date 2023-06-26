package com.example.demo.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfiguration {
    private String myEmail;
    private String myUsername;
    private String devUrl;
    private String prodUrl;
}
