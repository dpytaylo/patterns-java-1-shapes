package com.example.sphere.exception;

public class SphereFactoryException extends Exception {
    public SphereFactoryException() {
        super();
    }

    public SphereFactoryException(String exception) {
        super(exception);
    }

    public SphereFactoryException(Exception exception) {
        super(exception);
    }
}
