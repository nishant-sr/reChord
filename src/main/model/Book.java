package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import persistence.Writeable;

public class Book implements Writeable {

    private List<Composition> compositions;

    //Constructs a new book of compositions
    public Book() {
        compositions = new ArrayList<>();
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: adds a composition c to the book
    public void addComposition(Composition c) {
        this.compositions.add(c);
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: removes a composition c from the book
    public void removeComposition(Composition c) {
        this.compositions.remove(c);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: gets composition the i-th composition from the book
    public Composition getComposition(Integer i) {
        return this.compositions.get(i);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns size of book
    protected int bookSize() {
        return this.compositions.size();
    }

    //returns all the compositions making up the book
    public List<Composition> getCompositions() {
        return this.compositions;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("compositions", compositionsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray compositionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Composition c : this.compositions) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }

}
