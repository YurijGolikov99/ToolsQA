package selenide.api_module.data.book_store_application;

public class DeleteUser {

    public String userID;

    public DeleteUser(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}
