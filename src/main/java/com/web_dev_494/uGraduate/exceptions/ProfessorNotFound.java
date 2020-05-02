package com.web_dev_494.uGraduate.exceptions;

public class ProfessorNotFound extends RuntimeException {

    public ProfessorNotFound(String message) {
        super(message);
    }

    public ProfessorNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfessorNotFound(Throwable cause) {
        super(cause);
    }
}
