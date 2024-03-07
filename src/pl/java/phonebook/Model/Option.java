package pl.java.phonebook.Model;

import pl.java.phonebook.Exceptions.NoSuchOptionException;

public enum Option {
    ADD(0, "Dodaj kontakt"),
    PRINT(1,"Wyświetl listę kontaków"),
    FIND_BY_NAME(2, "Szukaj po nazwie"),
    FIND_BY_PHONE(3, "Szukaj po telefonie"),
    DELETE(4, "Usuń"),
    QUIT(5, "Koniec");

    private int value;
    private String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static Option createFromInt(int option) {
        try {
            return Option.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Brak opcji o ID: " + option);
        }
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }
}
