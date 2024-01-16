package selenide.api.boock_store_application;

import java.util.ArrayList;

//2 response (получаем) <-
public class RegistrationResponse {
    public String userID;
    public String username;
    public ArrayList<Object> books;

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Object> getBooks() {
        return books;
    }
}
