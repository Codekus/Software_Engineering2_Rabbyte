package de.hbrs.se.rabbyte.exception;


import de.hbrs.se.rabbyte.dtos.RegistrationResultDTO;

public class DatabaseUserException extends Exception {

    private static final long serialVersionUID = -8460356990632230194L;

    private RegistrationResultDTO.Result result = RegistrationResultDTO.Result.DATABASE_USER_EXCEPTION;
    public DatabaseUserException(String message) {
        super(message);
    }

    public DatabaseUserException(String message, Throwable cause) {
        super(message,cause);
    }

    public DatabaseUserException(String message, Throwable cause, RegistrationResultDTO.Result result) {
        super(message, cause);
        this.result = result;
    }

    public RegistrationResultDTO.Result getErrorCode() {
        return result;
    }


}

