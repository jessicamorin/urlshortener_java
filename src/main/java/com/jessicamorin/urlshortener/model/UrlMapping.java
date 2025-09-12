package com.jessicamorin.urlshortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Document(collection = "urls")
public class UrlMapping {

    @Id
    private ObjectId id;
   
    @Indexed(unique = true)
    private String originalUrl;   
      
    @Indexed(unique = true)
    private String shortUrl;
}
