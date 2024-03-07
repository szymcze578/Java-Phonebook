package pl.java.phonebook.IO;

import pl.java.phonebook.Model.Contact;

import java.util.Collection;
import java.util.Objects;

public class Printer {

    public void printContacts(Collection<Contact> contacts) {
         long count = contacts.stream()
                 .filter(Objects::nonNull)
                .map(Contact::toString)
                 .peek(this::printLine).count();
        if (count == 0) {
            printLine("Brak kontaktów.");
        }
    }

    public void printError(String line) {
        System.err.println(line);
    }

    public void printLine(String line) {
        System.out.println(line);
    }
}
