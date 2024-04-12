package intuit.com.web.requesthandlers.interfaces;
import com.sun.net.httpserver.HttpHandler;
import intuit.com.exceptions.IntuitValidationException;

public interface RequestValidator extends HttpHandler {

       void validate() throws RuntimeException;
}
