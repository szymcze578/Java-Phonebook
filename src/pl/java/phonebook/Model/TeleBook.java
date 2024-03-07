package pl.java.phonebook.Model;

import pl.java.phonebook.Exceptions.ContactAlreadyExistsException;

import java.util.*;

public class TeleBook {

    private Map<String, Contact> contacts = new HashMap<>();

    public Map<String, Contact> getContacts() {
        return contacts;
    }

    public Collection<Contact> getContactList(){
        List<Contact> contactList = new ArrayList<>(contacts.values());
        return contactList;
    }
    public List<Contact> findByName(String fragment){
        List<Contact> selectedContacts = new ArrayList<>();
        for (Map.Entry<String, Contact> next : contacts.entrySet()) {
            if (next.getKey().toLowerCase().contains(fragment.toLowerCase())) {
                selectedContacts.add(next.getValue());
            }
        }
        return selectedContacts;
    }

    public void addContact(Contact contact) {
        if(contacts.containsKey(contact.getName())) {
            throw new ContactAlreadyExistsException("Kontakt o takiej nazwie już istnieje: " + contact.getName());
        }
        contacts.put(contact.getName(),contact);
    }

    public Optional<Contact> findByPhoneNumber(String phoneNumber) {
        for (Map.Entry<String, Contact> contacts : contacts.entrySet()) {
            if(contacts.getValue().getPhoneNumber().equals(phoneNumber)){
                return Optional.of(contacts.getValue());
            }
        }
        return Optional.empty();
    }

    public boolean removeContact(Contact contact) {
        if(contacts.containsKey(contact.getName())){
            contacts.remove(contact.getName());
            return true;
        }
        return false;
    }
}
