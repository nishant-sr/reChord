package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import persistence.Writeable;

public class Composition implements Writeable {

    public final String standard = "EADGBE";
    private String name;
    private List<Chord> chords;
    private String tuning;
    private int capo;

    //REQUIRES: capo >= 0
    //MODIFIES: this
    //EFFECTS: creates a composition with chords, tuning, and capo @ fret
    public Composition(String name, String tuning, int capo) {
        this.name = name;
        this.tuning = tuning;
        this.capo = capo;
        chords = new ArrayList<>();
    }


    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: edits name of comp.
    public void editName(String s) {
        this.name = s;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: edits tuning of comp.
    public void editTuning(String t) {
        this.tuning = t;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: edits capo placement of comp.
    public void editCapo(Integer i) {
        this.capo = i;
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: adds chord c to comp.
    public void addChord(Chord c) {
        this.chords.add(c);
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: removes chord c from comp.
    public void removeChord(Chord c) {
        this.chords.remove(c);
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: returns size of comp.
    public int compSize() {
        return this.chords.size();
    }

    //-----------------------------------GETTERS--------------------------
    public String getName() {
        return this.name;
    }

    public String getTuning() {
        return this.tuning;
    }

    public int getCapo() {
        return this.capo;
    }

    //gets the i-th chord from the composition
    public Chord getChord(Integer i) {
        return this.chords.get(i);
    }

    //returns all the chords making up the composition
    public List<Chord> getChords() {
        return this.chords;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tuning", tuning);
        json.put("capo", capo);
        JSONArray jsonArray = new JSONArray();
        for (Chord c : this.chords) {
            jsonArray.put(c.getName());
        }
        json.put("chords", jsonArray);
        return json;
    }

}
