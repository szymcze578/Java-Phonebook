package pl.java.phonebook.Exceptions;

public class ContactAlreadyExistsException extends RuntimeException{
    public ContactAlreadyExistsException(String message) {
        super(message);
    }
}
