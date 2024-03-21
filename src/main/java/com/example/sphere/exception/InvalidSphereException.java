package com.example.sphere.exception;

public class InvalidSphereException extends Exception {
    public InvalidSphereException() {
        super("Invalid sphere");
    }

    public InvalidSphereException(String exception) {
        super("Invalid sphere: " + exception);
    }
}
