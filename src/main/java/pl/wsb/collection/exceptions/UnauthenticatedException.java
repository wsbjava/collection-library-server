package pl.wsb.collection.exceptions;

public class UnauthenticatedException extends Exception {

    public UnauthenticatedException(){
        super("Unauthenticated access...");
    }

    public UnauthenticatedException(String message){
        super(message);
    }


}
