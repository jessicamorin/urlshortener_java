package com.jessicamorin.urlshortener.controller;

import com.jessicamorin.urlshortener.model.UrlMapping;
import com.jessicamorin.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;
import com.jessicamorin.urlshortener.dto.ApiResponse;
import com.jessicamorin.urlshortener.dto.UrlDataResponse;

@RestController
@RequestMapping("/api")
public class UrlShortenerApiController {
    
    private final UrlService urlService;

    public UrlShortenerApiController(UrlService urlService) {
        this.urlService = urlService;
    }
    
    @PostMapping("/shorten")
    public ApiResponse<UrlDataResponse> shorten(@RequestBody UrlMapping url) {
        UrlMapping mapping = urlService.findOrCreate(url.getOriginalUrl());
        UrlDataResponse data = urlService.toDataResponse(mapping);
        return ApiResponse.success(data);
    }

    @GetMapping("/{shortUrl}")
    public ApiResponse<UrlDataResponse> getOriginalUrl(@PathVariable String shortUrl) {
        UrlMapping mapping = urlService.findByShortUrl(shortUrl);
        UrlDataResponse data = urlService.toDataResponse(mapping);
        return ApiResponse.success(data);
    }
}
