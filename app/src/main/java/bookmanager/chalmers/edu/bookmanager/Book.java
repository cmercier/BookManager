package bookmanager.chalmers.edu.bookmanager;

/**
 * Created by clement on 09/11/2015.
 */
public class Book {

    // Attributs

    private String author;
    private String title;
    private int price;
    private String isbn;
    private String course;

    // Constructors

    public Book() {
    }

    public Book(String author, String title, String isbn, int price, String course) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.course = course;
    }


    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
