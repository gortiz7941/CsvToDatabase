package edu.fgcu.dataengineering;

public class BookParser {

  private String isbn;
  private String publisher_name;
  private String author_name;
  private String author_email;
  private int book_year;
  private String book_title;
  private double book_price;

  protected void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  protected void setPublisherName(String publisherName) {
    this.publisher_name = publisherName;
  }

  protected void setAuthorName(String authorName) {
    this.author_name = authorName;
  }

  protected void setAuthorEmail(String authorEmail) {
    this.author_email = authorEmail;
  }

  protected void setBookYear(int bookYear) {
    this.book_year = bookYear;
  }

  protected void setBookTitle(String bookTitle) {
    this.book_title = bookTitle;
  }

  protected void setBookPrice(double bookPrice) {
    this.book_price = bookPrice;
  }

  protected String getIsbn() {
    return isbn;
  }

  protected String getPublisherName() {
    return publisher_name;
  }

  protected String getAuthorName() {
    return author_name;
  }

  protected String getAuthorEmail() {
    return author_email;
  }

  protected int getBookYear() {
    return book_year;
  }

  protected String getBookTitle() {
    return book_title;
  }

  protected double getBookPrice() {
    return book_price;
  }


}
