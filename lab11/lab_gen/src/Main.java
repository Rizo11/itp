import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Library<Book> bookLibrary = new Library<>();
        bookLibrary.add_media(new Book("31232321", MediaType.Book, 15, "Hobbit"));

        ArrayList<Book> allBooks = bookLibrary.getAll();

        for (Book b: allBooks) {
            System.out.println(b.toString());
        }


    }
}

enum MediaType {
    Book,
    Newspapers,
    Video
}

abstract class Media {
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    int size;
    MediaType type;
    String title;

    @Override
    public String toString() {
        return "Title: " + this.title + "\nSize: " + this.size + "\nType: " + this.type;
    }
}

class Book extends Media {

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    String ISBN;

    public Book(String isbn, MediaType typeOfMedia, int size1, String Title) {
        super.setSize(size1);
        super.setTitle(Title);
        super.setType(typeOfMedia);
        setISBN(isbn);
    }
}

class Newspaper extends Media {
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    String publisher;
}

class Video extends Media {
    public int getLength_minutes() {
        return Length_minutes;
    }

    public void setLength_minutes(int length_minutes) {
        Length_minutes = length_minutes;
    }

    int Length_minutes;
}




class Library<T extends Media> {
    public ArrayList<T> lib = new ArrayList<>();

    public ArrayList<T> getAll() {
        return lib;
    }

    public void add_media(T newMedia) {
        lib.add(newMedia);
    }
}