package intuit.com.exceptions;

public class IntuitValidationException extends Exception{
    public IntuitValidationException(String message) {super(message);}
    public IntuitValidationException(String message, Throwable cause) {super(message, cause);}
}
