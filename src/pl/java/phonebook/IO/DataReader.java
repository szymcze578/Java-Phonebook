package pl.java.phonebook.IO;

import pl.java.phonebook.Model.Contact;

import java.util.Scanner;

public class DataReader {
    private Scanner scanner = new Scanner(System.in);

    private Printer printer;

    public DataReader(Printer printer) {
        this.printer = printer;
    }

    public Contact readAndCreateContact() {
        printer.printLine("Podaj nazwę kontaktu: ");
        String name = scanner.nextLine();
        printer.printLine("Podaj numer telefonu: ");
        String phoneNumber = scanner.nextLine();
        return new Contact(name, phoneNumber);
    }

    public String getString() {
        return scanner.nextLine();
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        }
        finally {
            scanner.nextLine();
        }
    }

    public void close(){
        scanner.close();
    }

}
