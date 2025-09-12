package com.jessicamorin.urlshortener.dto;

import lombok.Data;

@Data
public class UrlDataResponse {
    private String domain;
    private String shortUrl;
    private String originalUrl;
}
