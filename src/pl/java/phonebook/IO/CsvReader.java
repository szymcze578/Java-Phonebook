package pl.java.phonebook.IO;

import pl.java.phonebook.Exceptions.DataExportException;
import pl.java.phonebook.Exceptions.DataImportException;
import pl.java.phonebook.Model.Contact;
import pl.java.phonebook.Model.TeleBook;

import java.io.*;
import java.util.Collection;

public class CsvReader {
    private static final String FILENAME = "Telebook.csv";

    public TeleBook importData(){
        TeleBook teleBook = new TeleBook();
        importContacts(teleBook);
        return teleBook;
    }

    private void importContacts(TeleBook teleBook){
        try(BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while((line = reader.readLine())!=null){
                Contact contact = createContact(line);
                teleBook.addContact(contact);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku: "+ FILENAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku: "+FILENAME);
        }
    }

    private Contact createContact(String line){
        String[] split = line.split(";");
        return new Contact(split[0],split[1]);
    }

    public void exportContacts(TeleBook teleBook){
        Collection<Contact> contacts = teleBook.getContacts().values();
        exportToCsv(contacts);
    }

    private void exportToCsv(Collection<Contact> collection){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
        for(Contact contact :collection){
            writer.write(contact.toCsv());
            writer.newLine();
        }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu do pliku: "+ FILENAME);
        }
    }

}
