package com.pastry.pastryapi.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFound extends RuntimeException {
    private final String resource;
    private final String resourceId;

    public ResourceNotFound(String resource, String resourceId) {
        super();
        this.resource = resource;
        this.resourceId = resourceId;
    }
}
