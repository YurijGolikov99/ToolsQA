package selenide.api_module.data.book_store_application;

import java.util.Date;

//конвертируем JSON в Java на сайте POJO https://json2csharp.com/code-converters/json-to-pojo и копируем сюда
/**
 * {
 *   "books": [
 *     {
 *       "isbn": "string",
 *       "title": "string",
 *       "subTitle": "string",
 *       "author": "string",
 *       "publish_date": "2024-05-21T07:14:24.428Z",
 *       "publisher": "string",
 *       "pages": 0,
 *       "description": "string",
 *       "website": "string"
 *     }
 *   ]
 * }
 */
public class BooksData {

    //переменные делаются приватными, согласна правилам ООП
    //название переменных должно быть такое же как и в теле
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private Date publish_date;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;

    //создаём конструктор
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

    // Создаём геттеры для каждой книги, чтобы получать интересующие нас данные
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

    // Создаём геттеры для каждой книги, чтобы ...
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
