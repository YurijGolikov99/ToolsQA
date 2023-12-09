package selenideTests.ui;

import org.junit.Test;
import selenide.pages.boockStoreApplication.BookStorePage;

public class BookStoreTests {
    private static final String URL = "https://demoqa.com/books";

    @Test
    public void checkAttributeHashMap(){

        BookStorePage bookStorePage = new BookStorePage(URL);
    }
}
