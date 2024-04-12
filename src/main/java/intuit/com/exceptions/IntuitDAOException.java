package intuit.com.exceptions;

public class IntuitDAOException extends Exception {
    public IntuitDAOException(String message) {super(message);}
    public IntuitDAOException(String message, Throwable cause) {super(message, cause);}
}
