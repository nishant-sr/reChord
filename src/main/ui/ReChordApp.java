package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReChordApp {

    private Book book;
    private Composition comp;
    private Chord c1;
    private static final String JSON_STORE = "./data/book.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Scanner input;

    public ReChordApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        main();
    }

    private void main() {
        String command = null;

        initialize();
        while (true) {
            menu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                break;
            } else {
                executeCommand(command);
            }
        }

    }

    //main menu of app, displays options for users to pick from
    private void menu() {
        System.out.println("Compositions: \n");
        displayComps();
        System.out.println("\nOptions: ");
        System.out.println("\ta : Add a composition");
        System.out.println("\tr : Remove a composition");
        System.out.println("\te : Edit a composition");
        System.out.println("\ts: save book");
        System.out.println("\tl: load book");
        System.out.println("\tq : Quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes book, a composition, and a chord
    private void initialize() {
        book = new Book();
        comp = new Composition("Undecided", "EADGBE", 0);
        c1 = new Chord("A");
        book.addComposition(comp);
        comp.addChord(c1);
        input = new Scanner(System.in);
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void executeCommand(String command) {
        if (command.equals("a")) {
            addComposition();
        } else if (command.equals("r")) {
            removeComposition();
        } else if (command.equals("e")) {
            editComposition();
        } else if (command.equals("s")) {
            saveApp();
        } else if (command.equals("l")) {
            loadApp();
        } else {
            System.out.println("Choose an option that's provided");
        }
    }

    //REQUIRES:
    //MODIFIES: Book
    //EFFECTS: adds a new composition to book
    private void addComposition() {
        System.out.println("Enter tha name of the composition:");
        String name = input.next();
        System.out.println("Enter the tuning you will be using");
        String tuning = input.next();
        System.out.println("Enter the fret to place your capo");
        Integer capo = Integer.valueOf(input.next());
        Composition newComp = new Composition(name, tuning, capo);
        book.addComposition(newComp);
    }

    //REQUIRES:
    //MODIFIES: Book
    //EFFECTS:  removed a composition from book
    private void removeComposition() {
        Composition chosen = chooseComposition();
        book.removeComposition(chosen);
    }

    //REQUIRES:
    //MODIFIES: Composition
    //EFFECTS: edits the chosen composition
    private void editComposition() {
        Composition chosen = chooseComposition();
        System.out.println("You chose " + chosen.getName());
        displayComp(chosen);
        editForm();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: returns the composition the user chose
    private Composition chooseComposition() {
        Integer choice = 0;
        System.out.println("Choose the composition (# from top)");
        choice = Integer.valueOf(input.next());
        comp = book.getComposition(choice - 1);
        return comp;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: form for user to decide what edit they want to make
    private void editForm() {
        System.out.println("\nOptions: ");
        System.out.println("\tn : Edit name");
        System.out.println("\tt : Edit tuning");
        System.out.println("\tc : Edit capo");
        System.out.println("\ta : Add a chord");
        System.out.println("\tr : Remove a chord");
        System.out.println("\te : Edit an existing chord");
        String edit = input.next();
        edit.toLowerCase();
        editCommand(edit);
    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: executes appropriate operation for user command
    private void editCommand(String e) {
        if (e.equals("n")) {
            changeName();
        } else if (e.equals("t")) {
            changeTuning();
        } else if (e.equals("c")) {
            changeCapo();
        } else if (e.equals("a")) {
            addChord();
        } else if (e.equals("r")) {
            System.out.println("Which chord? (# from top)");
            Integer remove = Integer.valueOf(input.next());
            removeChord(remove);
        } else if (e.equals("e")) {
            System.out.println("Which chord? (# from top)");
            Integer edit = Integer.valueOf(input.next());
            editChord(edit);
        }
    }

    //REQUIRES:
    //MODIFIES: Composition
    //EFFECTS: adds a new chord to composition
    private void addChord() {
        System.out.println("Name of new chord?");
        String name = input.next();
        Chord newCh = new Chord(name);
        comp.addChord(newCh);
    }

    //REQUIRES:
    //MODIFIES: Composition
    //EFFECTS: removes a chord from composition
    private void removeChord(Integer i) {
        Chord ch = comp.getChord(i - 1);
        comp.removeChord(ch);
    }

    //REQUIRES:
    //MODIFIES: Composition
    //EFFECTS: edits existing chord in composition
    private void editChord(Integer i) {
        Chord chord = comp.getChord(i - 1);
        System.out.println("What do you want to change the chord to?");
        String name = input.next();
        chord.changeName(name);
    }

    //REQUIRES:
    //MODIFIES: Composition
    //EFFECTS: changes capo position
    private void changeCapo() {
        System.out.println("Enter new fret for capo");
        Integer capo = Integer.valueOf(input.next());
        comp.editCapo(capo);
    }

    //REQUIRES:
    //MODIFIES: Composition
    //EFFECTS: changes guitar tuning
    private void changeTuning() {
        System.out.println("Enter new tuning");
        String tune = input.next();
        comp.editTuning(tune);
    }

    //REQUIRES:
    //MODIFIES: Composition
    //EFFECTS: changes name of composition
    private void changeName() {
        System.out.println("Enter new name for composition");
        String name = input.next();
        comp.editName(name);
    }


    //displays all comps in the present book
    public void displayComps() {
        List<Composition> loc = book.getCompositions();
        for (Composition c : loc) {
            System.out.println((loc.indexOf(c) + 1) + " : " + c.getName());
        }
    }

    //displays all the details of the composition
    public void displayComp(Composition c) {
        System.out.println("Tuning: " + c.getTuning());
        System.out.println("Capo fret: " + c.getCapo());
        System.out.println("Chords: ");
        List<Chord> loch = c.getChords();
        for (Chord chord : loch) {
            int pos = loch.indexOf(chord) + 1;
            System.out.println(pos + ": " + chord.getName());
        }
    }

    private void saveApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(book);
            jsonWriter.close();
            System.out.println("Book saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save at this time to" + JSON_STORE);
        }
    }

    private void loadApp() {
        try {
            book = jsonReader.read();
            System.out.println("Loaded book");
        } catch (IOException e) {
            System.out.println("Unable to load at this time from" + JSON_STORE);
        }
    }

}
