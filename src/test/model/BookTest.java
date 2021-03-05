package model;

import model.Composition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    Book b1;
    Composition c1;

    @BeforeEach
    void runBefore(){
        b1 = new Book();
        c1 = new Composition("Random","EADGBE",2);
    }

    @Test
    void test(){
        assertEquals(b1.bookSize(),0);
        b1.addComposition(c1);
        assertEquals(b1.bookSize(),1);
       b1.removeComposition(c1);
       assertEquals(b1.bookSize(),0);
    }

    @Test
    void testgetComposition(){
        b1.addComposition(c1);
        assertEquals(b1.getComposition(0),c1);
    }

    @Test
    void testgetCompositions(){
        assertEquals(b1.getCompositions().size(),0);
    }

}
