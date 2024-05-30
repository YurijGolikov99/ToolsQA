package selenide.api_module.data.book_store_application;


import java.util.ArrayList;

public class RegistrationResponse {

    public String userID;
    public String username;
    public ArrayList<BooksObjects> books;

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<BooksObjects> getBooks() {
        return books;
    }
}
