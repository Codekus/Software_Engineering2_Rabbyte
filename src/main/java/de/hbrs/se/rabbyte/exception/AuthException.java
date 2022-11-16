package de.hbrs.se.rabbyte.exception;

public class AuthException extends Exception{

    public AuthException() {}

    public AuthException(String message)
    {
        super(message);
    }
}
