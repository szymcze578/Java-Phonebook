package pl.java.phonebook.App;

public class TeleBookApp {
    public static void main(String[] args) {
        TeleBookController bookController = new TeleBookController();
        bookController.mainLoop();
    }
}
