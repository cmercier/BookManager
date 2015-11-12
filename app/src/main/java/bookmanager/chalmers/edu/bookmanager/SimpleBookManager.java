package bookmanager.chalmers.edu.bookmanager;

import java.util.ArrayList;

/**
 * Created by clement on 09/11/2015.
 */
public class SimpleBookManager implements BookManager {

    // Singleton
    private static SimpleBookManager ourInstance = new SimpleBookManager();
    public static SimpleBookManager getInstance() {
        return ourInstance;
    }

    // Attributs

    private ArrayList<Book> books;

    // Constructors


    private SimpleBookManager() {
        books = new ArrayList<Book>();
    }

    @Override
    public int count() {
        return books.size();
    }

    @Override
    public Book getBook(int index) {
        return books.get(index);
    }

    @Override
    public Book createBook() {
        Book book = new Book();
        books.add(book);
        return book;
    }

    public Book addBook(Book b) {
        books.add(b);
        return b;
    }

    public void clear() {
        books.clear();
    }


    @Override
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }

    //TODO check the behavior
    @Override
    public void moveBook(int from, int to) {
        Book b = books.get(from);
        books.remove(from);
        books.add(to, b);
    }

    @Override
    public int getMinPrice() {
        int min = books.get(0).getPrice();
        for (Book b : books) {
            if (b.getPrice() < min)
                min = b.getPrice();
        }
        return min;
    }

    @Override
    public int getMaxPrice() {
        int max = books.get(0).getPrice();
        for (Book b : books) {
            if (b.getPrice() > max)
                max = b.getPrice();
        }
        return max;
    }

    //TODO need to verify the cast
    @Override
    public float getMeanPrice() {
        float sum = getTotalCost();
        if (books.size() == 0)
            return 0;
        return sum / books.size();
    }

    @Override
    public int getTotalCost() {
        int sum = 0;
        for (Book b : books) {
            sum += b.getPrice();
        }
        return sum;
    }

    @Override
    public void saveChanges() {

    }
}
