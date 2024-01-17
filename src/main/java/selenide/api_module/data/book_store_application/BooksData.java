package selenide.api_module.data.book_store_application;

import java.util.Date;

//конвертируем JSON в Java на сайте POJO https://json2csharp.com/code-converters/json-to-pojo и копируем сюда
public class BooksData {
    //переменные делаются приватными, согласна правилам ООП
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private Date publish_date;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;

    //создаём конструктор и геттеры для каждой книги, чтобы получать интересующие нас данные
    public BooksData(String isbn, String title, String subTitle,
                     String author, Date publish_date, String publisher,
                     Integer pages, String description, String website) {
        this.isbn = isbn;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.publish_date = publish_date;
        this.publisher = publisher;
        this.pages = pages;
        this.description = description;
        this.website = website;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublish_date() {
        return publish_date;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getPages() {
        return pages;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }
}
