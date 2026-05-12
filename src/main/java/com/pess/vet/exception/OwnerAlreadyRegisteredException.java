package com.pess.vet.exception;

public class OwnerAlreadyRegisteredException extends RuntimeException {
    public OwnerAlreadyRegisteredException(String message) {
        super(message);
    }
}
