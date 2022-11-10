package de.hbrs.se.rabbyte.exception;

import java.security.GeneralSecurityException;

public class HashingException extends Exception  {
    public HashingException(GeneralSecurityException exception) {
        super(exception);
    }
}
