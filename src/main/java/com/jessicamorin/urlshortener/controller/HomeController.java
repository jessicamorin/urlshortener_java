package com.jessicamorin.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("message", "URL Shortener API is running.", "info", "Use /api/shorten to create short URLs and /api/{shortUrl} to retrieve the original URL.");
    }
}
