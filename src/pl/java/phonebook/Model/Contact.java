package pl.java.phonebook.Model;

import pl.java.phonebook.Exceptions.EmptyStringsException;
import pl.java.phonebook.Exceptions.InvalidPhoneNumberException;

public class Contact {
    private static final int PHONE_NUMBER_LENGTH = 9;
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        if (name.equals("") || phoneNumber.equals(""))
            throw new EmptyStringsException("Nazwa ani numer telefonu nie mogą być puste");
        if (phoneNumber.length() != PHONE_NUMBER_LENGTH)
            throw new InvalidPhoneNumberException("Numer telefonu powinien mieć " + PHONE_NUMBER_LENGTH + " znaków");
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name + " - " + phoneNumber;
    }

    public String toCsv() {
        return name + ";" + phoneNumber;
    }
}
