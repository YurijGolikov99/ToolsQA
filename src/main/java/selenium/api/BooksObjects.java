package selenium.api;

import java.util.Date;

abstract public class BooksObjects {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private Date publish_date;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;
    public BooksObjects(String isbn,
                        String title,
                        String subTitle,
                        String author,
                        Date publish_date,
                        String publisher,
                        Integer pages,
                        String description,
                        String website) {
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
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubTitle() {
        return subTitle;
    }
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getPublish_date() {
        return publish_date;
    }
    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public Integer getPages() {
        return pages;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
}