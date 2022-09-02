package dev.muldev.pockafka.exceptions;

public class UserAlreadyRegisteredException extends Exception{
    public UserAlreadyRegisteredException(String email){
        super(email + " is registered, please login");
    }
}
