package ru.mera.service.exception;

import java.io.IOException;

public class ServiceNotAvailableException extends IOException {

    public ServiceNotAvailableException() {
    }

    public ServiceNotAvailableException(Exception e) {
        super(e);
    }
}
