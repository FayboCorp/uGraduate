package com.web_dev_494.uGraduate.exceptions;

public class StudentNotFound extends RuntimeException {

    public StudentNotFound(String message) {
        super(message);
    }

    public StudentNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFound(Throwable cause) {
        super(cause);
    }
}
