
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    String title;
    String author;
    List<HardCopy> copies;
    private String genre;
    String imagePath;

    // default constructor
    public Book() {
        title = "";
        author = "";
        copies = null;
        genre = "";
    }

    public Book(String title, String author, int numberOfCopies, String genre, String imagePath) {
        this.title = title;
        this.author = author;
        this.copies = new ArrayList<>();
        this.genre = genre;
        this.imagePath = imagePath;

        for (int i = 0; i < numberOfCopies; i++) {
            copies.add(new HardCopy(this));
        }
    }

    public List<HardCopy> getAvailableCopies() {
        List<HardCopy> availableCopies = new ArrayList<>();

        for (HardCopy copy : copies) {
            if (!copy.isBorrowed()) {
                availableCopies.add(copy);
            }
        }

        return availableCopies;
    }

    // borrowBook() method
    public void borrowBook(String username) {
        List<HardCopy> availableCopies = getAvailableCopies();

        if (availableCopies.size() > 0) {
            availableCopies.get(0).borrowBookCopy(LocalDate.now(), username);
        } else {
            System.out.println("No copies available.");
        }
    }

    // returnBook() method
    public void returnBook(String username) {
        for (HardCopy copy : copies) {
            if (copy.isBorrowed() && copy.getBorrower().equals(username)) {
                copy.returnBookCopy();
                break;
            }
        }
    }

    // get & set methods
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

    public List<HardCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<HardCopy> copies) {
        this.copies = copies;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isBorrowedByUser(String username) {
        for (HardCopy copy : copies) {
            if (copy.isBorrowed() && copy.getBorrower().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
