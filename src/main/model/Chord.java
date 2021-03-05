package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Chord {

    private String name;

    //Constructs a new chord with a name
    public Chord(String name) {
        this.name = name;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: edits name of chord
    public void changeName(String n) {
        this.name = n;
    }

    //Gets name of chord
    public String getName() {
        return this.name;
    }

}
