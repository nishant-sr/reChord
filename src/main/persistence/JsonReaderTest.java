package persistence;

import model.Book;
import model.Composition;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Book bk = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBook.json");
        try {
            Book bk = reader.read();
            assertEquals(0, bk.getCompositions().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBook.json");
        try {
            Book bk = reader.read();
            List<Composition> comps = bk.getCompositions();
            assertEquals(2, comps.size());
            assertEquals(comps.get(0).getName(),"Song1");
            assertEquals(comps.get(1).getName(),"Song2");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
