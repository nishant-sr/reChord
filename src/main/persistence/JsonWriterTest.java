package persistence;

import model.Book;
import model.Composition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Book bk = new Book();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBook() {
        try {
            Book bk = new Book();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBook.json");
            writer.open();
            writer.write(bk);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBook.json");
            bk = reader.read();
            assertEquals(0, bk.getCompositions().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBook() {
        try {
            Book bk = new Book();
            bk.addComposition(new Composition("Song1","Standard",7));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBook.json");
            writer.open();
            writer.write(bk);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBook.json");
            bk = reader.read();
            List<Composition> comps = bk.getCompositions();
            assertEquals(1, comps.size());
            assertEquals("Song1",comps.get(0).getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
