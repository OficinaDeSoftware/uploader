package com.ms.uploader.infrastructure;

import org.springframework.http.HttpStatus;

public record RestErrorMessage (
        HttpStatus status,
        String message ) { }
