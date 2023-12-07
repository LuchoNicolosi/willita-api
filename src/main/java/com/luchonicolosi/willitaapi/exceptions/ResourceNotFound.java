package com.luchonicolosi.willitaapi.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFound extends RuntimeException {
    private final String resource;
    private final Long resourceId;

    public ResourceNotFound(String resource, Long resourceId) {
        super();
        this.resource = resource;
        this.resourceId = resourceId;
    }
}
