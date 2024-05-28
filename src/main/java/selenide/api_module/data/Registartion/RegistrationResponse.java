package selenide.api_module.data.Registartion;

import selenide.api_module.data.book_store_application.BooksData;

import java.util.ArrayList;

//2 response (получаем) <-
public class RegistrationResponse {

    public String userID;
    public String username;
    public ArrayList<BooksData> books;

    // с конструктором почему-то падает
//    public RegistrationResponse(String userID, ArrayList<Object> books, String username) {
//        this.userID = userID;
//        this.books = books;
//        this.username = username;
//    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<BooksData> getBooks() {
        return books;
    }
}
