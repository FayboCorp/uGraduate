package com.web_dev_494.uGraduate.exceptions;

public class SectionNotFound extends RuntimeException{

    public SectionNotFound(String message) {
        super(message);
    }

    public SectionNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public SectionNotFound(Throwable cause) {
        super(cause);
    }
}
