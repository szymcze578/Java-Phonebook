package pl.java.phonebook.App;

import pl.java.phonebook.Exceptions.*;
import pl.java.phonebook.IO.CsvReader;
import pl.java.phonebook.IO.DataReader;
import pl.java.phonebook.IO.Printer;
import pl.java.phonebook.Model.Contact;
import pl.java.phonebook.Model.Option;
import pl.java.phonebook.Model.TeleBook;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TeleBookController {
    private TeleBook teleBook;
    private Printer printer = new Printer();
    private DataReader dataReader = new DataReader(printer);
    CsvReader fileReader = new CsvReader();

    public TeleBookController() {
        try {
            teleBook = fileReader.importData();
            printer.printLine("Zaimportowano dane z pliku.");
        } catch (DataImportException e) {
            printer.printError(e.getMessage());
            printer.printError("Zaiinicjonano nową bazę.");
            teleBook = new TeleBook();
        }
    }

    public void mainLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD -> addContact();
                case PRINT -> printContacts();
                case FIND_BY_NAME -> findByName();
                case FIND_BY_PHONE -> findByPhone();
                case DELETE -> deleteContact();
                case QUIT -> exit();
            }
        } while (option != Option.QUIT);
    }

    private void printContacts() {
        printer.printContacts(teleBook.getContactList());
    }

    private void deleteContact() {
        try{
            Contact contact = dataReader.readAndCreateContact();
            if(teleBook.removeContact(contact)){
                printer.printLine("Usunięto rekord");
            }else{
                printer.printLine("Brak wskazanego kontaktu.");
            }
        }
        catch (EmptyStringsException | InvalidPhoneNumberException e){
           printer.printError(e.getMessage());
        }
    }
    private void exit() {
        try {
            fileReader.exportContacts(teleBook);
            printer.printLine("Eksport danych do pliku zakończony powodzeniem.");
        } catch (DataImportException e) {
            printer.printError(e.getMessage());
        }
        printer.printLine("Koniec programu.");
        dataReader.close();
    }

    private void findByName() {
        System.out.println("Podaj fragment nazwy:");
        String nameFragment = dataReader.getString();
        List<Contact> contacts = teleBook.findByName(nameFragment);
        if (!contacts.isEmpty()) {
            printer.printLine("Znalezione rekordy: ");
            for (Contact contact : contacts) {
                printer.printLine(contact.toString());
            }
        } else
            printer.printLine("Nie znaleziono żadnych rekorów.");
    }

    private void findByPhone() {
        System.out.println("Podaj numer telefonu: ");
        String phoneNumber = dataReader.getString();
        String notFoundMessage = "Nie znaleziono rekordu o numerze telefonu " + phoneNumber;
        teleBook.findByPhoneNumber(phoneNumber)
                .map(Contact::toString)
                .ifPresentOrElse(
                        printer::printLine,
                        ()-> printer.printError(notFoundMessage)
                );
    }

    private void addContact() {
        try {
            Contact contact = dataReader.readAndCreateContact();
            teleBook.addContact(contact);
            printer.printLine("Rekord dodany");
        } catch (InvalidPhoneNumberException | EmptyStringsException | ContactAlreadyExistsException e) {
            printer.printError(e.getMessage());
        }
    }

    public void printOptions() {
        printer.printLine(">>>Opcje:");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private Option getOption() {
        boolean optionOK = false;
        Option option = null;
        while (!optionOK) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOK = true;
            } catch (NoSuchOptionException e) {
                printer.printError(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printError("Wprowadzono wartość, która nie jest liczbą, spróbuj ponownie:");
            }
        }
        return option;
    }
}
