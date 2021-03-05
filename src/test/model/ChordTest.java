package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChordTest {

    Chord ch;

    @BeforeEach
    void runBefore(){
        ch = new Chord("C#");
    }

    @Test
    void test(){
        assertEquals(ch.getName(),"C#");
        ch.changeName("Aminor");
        assertEquals(ch.getName(),"Aminor");
    }

}
