package pl.wsb.collection.api.handlers;

import org.codehaus.plexus.util.StringUtils;

/**
 * To do - klasy z api bez tego się nie skompiluje
 */
public class ErrorHandler {
    public static Error getErrorResponse(Exception ex) {
        if (ex == null) {
            //return new Error().message("Error occurred...");
            return null;
        } //if
        //return new Error().message(ex.getMessage());
        return null;
    }
    public static Error getErrorResponse(String message) {
        if (StringUtils.isBlank(message)) {
            //return new Error().message("Error occurred...");
            return null;
        } //if
        //return new Error().message(message);
        return null;
    }
}
