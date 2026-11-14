package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    void booksWithSameIsbnAreEqualAndFindableInMap() {
        Book book1 = new Book("123", "Война и мир");
        Book book2 = new Book("123", "Отцы и дети");
        assertEquals(book1, book2);

        Map<Book, String> library = new HashMap<>();
        library.put(book1, "Полка А");

        String shelf = library.get(book2);
        assertEquals("Полка А", shelf);
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book book1 = new Book("123", "Название");
        Book book2 = new Book("456", "Название");

        assertNotEquals(book1, book2);
    }
}
