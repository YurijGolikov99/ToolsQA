package selenide.api_module.data.book_store_application_dto;

import java.util.ArrayList;

/**
 * 2) response (получаем) <-
 */
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
