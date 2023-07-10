package ru.byCooper.employeeProject.exception;


public class EmployeeValidateException extends RuntimeException{
    public EmployeeValidateException() {
    }

    public EmployeeValidateException(String message) {
        super(message);
    }

    public EmployeeValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeValidateException(Throwable cause) {
        super(cause);
    }

    public EmployeeValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
