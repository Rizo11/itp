import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        List<Book> books = new ArrayList<Book>(){};
        books.add(new Book("Me", "My Life", 2020, 20, "good"));
        books.add(new Book("Me Too", "My Life", 2020, 20, "good"));


    }
}

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    public String author;
    public String title;
    public Integer issueYear;
    public Integer pageNumber;
    public String bookMark;

    public Book(String author, String title, Integer issueYear, Integer pageNumber, String bookMark) {
        this.author = author;
        this.title = title;
        this.issueYear = issueYear;
        this.pageNumber = pageNumber;
        this.bookMark = bookMark;
    }

    public String toString() {
        return "book = " + this.title + " " + this.author + " " + this.issueYear;
    }
}

class ReaderLibrary {
    /*private static ObjectOutputStream out = null;
    public ReaderLibrary() {
        out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("storage"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
    public void addMyReadBooks(List<Book> books, String serializedFilePath) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(serializedFilePath));
        System.out.println(books);
        out.writeObject(books);
    }

    public  void getMySerializedBooks(String serializedFilePath) {
        ObjectInputStream receive = null;
        try {
            receive = new ObjectInputStream(new FileInputStream(serializedFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Book> myObjReceived = null;
        try {
            myObjReceived = (List<Book>)receive.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("myObjReceived: " + myObjReceived);

    }

    public void displayMyBooks(List<Book> books) {
        for (Book b: books) {
            System.out.println(b);
        }
    }
}
