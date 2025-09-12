package com.jessicamorin.urlshortener.service;

import com.jessicamorin.urlshortener.model.UrlMapping;
import com.jessicamorin.urlshortener.repository.UrlRepository;
import com.jessicamorin.urlshortener.util.CodeGenerator;
import org.springframework.stereotype.Service;
import com.jessicamorin.urlshortener.exception.ApiException;
import org.springframework.dao.DuplicateKeyException;
import com.jessicamorin.urlshortener.dto.UrlDataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class UrlService {

    @Value("${urlshortener.domain}")
    private String domain;
    
    private final UrlRepository repository;
    private static final int SHORT_URL_MAX_LENGTH = 10;

    public UrlService(UrlRepository repository) {
        this.repository = repository;
    }

    public UrlMapping findOrCreate(String originalUrl) {
        while (true) {
            try {
                return createShortUrl(originalUrl);
            } catch (DuplicateKeyException e) {
                UrlMapping existing = repository.findByOriginalUrl(originalUrl).orElse(null);
                if (existing != null) {
                    return existing;
                }
            }
        }
    }

    public UrlMapping createShortUrl(String originalUrl) {
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortUrl(CodeGenerator.generateCode(SHORT_URL_MAX_LENGTH));
        return repository.save(mapping);
    }
    
    public UrlMapping findByOriginalUrl(String originalUrl) {
        return repository.findByOriginalUrl(originalUrl)
                         .orElseThrow(() -> new ApiException(404, "Original URL not found: " + originalUrl));
    }
    
    public UrlMapping findByShortUrl(String shortUrl) {
        return repository.findByShortUrl(shortUrl)
                         .orElseThrow(() -> new ApiException(404, "Short URL not found: " + shortUrl));
    }
    
    public UrlDataResponse toDataResponse(UrlMapping mapping) {
        UrlDataResponse data = new UrlDataResponse();
        String shortUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                           .path("/api/" + mapping.getShortUrl())
                           .toUriString();

        data.setDomain(domain);
        data.setShortUrl(shortUrl);
        data.setOriginalUrl(mapping.getOriginalUrl());
        
        return data;
    }
}
