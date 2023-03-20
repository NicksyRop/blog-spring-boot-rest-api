package com.api.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class ResourceNotFoundException extends RuntimeException{


    private String resourceName;
    private  String fieldName;
    private String fieldVale;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldVale) {

        super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fieldVale));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldVale = fieldVale;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldVale() {
        return fieldVale;
    }
}
