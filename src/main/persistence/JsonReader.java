package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    public Book read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses book from JSON object and returns it
    private Book parseBook(JSONObject jsonObject) {
        Book bk = new Book();
        addCompositions(bk, jsonObject);
        return bk;
    }

    // MODIFIES: bk
    // EFFECTS: parses compositions from JSON object and adds them to book
    private void addCompositions(Book bk, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("compositions");
        for (Object json : jsonArray) {
            JSONObject nextTComposition = (JSONObject) json;
            addComposition(bk, nextTComposition);
        }
    }

    // MODIFIES: bk
    // EFFECTS: parses composition from JSON object and adds it to book
    private void addComposition(Book bk, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String tuning = jsonObject.getString("tuning");
        Integer capo = jsonObject.getInt("capo");
        ArrayList<Chord> chords = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("chords");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                String chord = jsonArray.getString(i);
                chords.add(new Chord(chord));
            }
        }
        Composition c = new Composition(name, tuning, capo);
        for (Chord chord : chords) {
            c.addChord(chord);
        }
        bk.addComposition(c);
    }


}
