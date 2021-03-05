package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositionTest {

    Composition c1;
    Chord ch1;
    Chord ch2;

    @BeforeEach
    void runBefore() {
        c1 = new Composition("Random", "EADGBE", 3);
        ch1 = new Chord("E");
        ch2 = new Chord("C#");
    }

    @Test
    void testEditName() {
        assertEquals(c1.getName(), "Random");
        c1.editName("Verithanam");
        assertEquals(c1.getName(), "Verithanam");
    }

    @Test
    void testEditTuning() {
        assertEquals(c1.getTuning(), c1.standard);
        c1.editTuning("Drop D");
        assertEquals(c1.getTuning(), "Drop D");
    }

    @Test
    void testEditCapo() {
        assertEquals(c1.getCapo(), 3);
        c1.editCapo(0);
        assertEquals(c1.getCapo(), 0);
    }

    @Test
    void testAddChord() {
        assertEquals(c1.compSize(), 0);
        c1.addChord(ch1);
        assertEquals(c1.compSize(), 1);
    }

    @Test
    void testRemoveChord() {
        c1.addChord(ch1);
        assertEquals(c1.compSize(), 1);
        c1.removeChord(ch1);
        assertEquals(c1.compSize(), 0);
    }

    @Test
    void testgetCapo() {
        assertEquals(c1.getCapo(), 3);
        c1.editCapo(9);
        assertEquals(c1.getCapo(), 9);
    }

    @Test
    void testgetName() {
        assertEquals(c1.getName(), "Random");
        c1.editName("Non");
        assertEquals(c1.getName(), "Non");
    }

    @Test
    void testgetTuning() {
        assertEquals(c1.getTuning(), "EADGBE");
        c1.editTuning("ABCDEF");
        assertEquals(c1.getTuning(), "ABCDEF");
    }

    @Test
    void testgetChord() {
        c1.addChord(ch1);
        assertEquals(c1.getChord(0), ch1);
    }

    @Test
    void testgetChords() {
        assertEquals(c1.getChords().size(), 0);
        c1.addChord(ch1);
        assertEquals(c1.getChords().size(), 1);
    }

    @Test
    void testToJson(){
        JSONObject c1Json = c1.toJson();
        assertEquals(c1Json.getString("name"),"Random");
    }

}
