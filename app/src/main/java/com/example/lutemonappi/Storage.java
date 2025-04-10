package com.example.lutemonappi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private String name;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private static Storage storage = null;
    private Storage() {

    }

    public static Storage getInstance() {
        if(storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public ArrayList<Lutemon> getLutemons() {
        return lutemons;
    }

    public void addLutemon(Lutemon lutemon) {
        lutemons.add(lutemon);
    }

    public Lutemon getLutemonById(int id) {
        return lutemons.get(id);
    }

    public Lutemon getLutemonByIdWithoutRemove(int id) {
        return lutemons.get(id);
    }

    public void removeLutemon(int id) {
        int i = 0;
        for (Lutemon l : lutemons) {
            if (l.getId() == id) {
                break;
            }
            i++;
        }
        lutemons.remove(i);
    }

    public void listLutemons() {
        int i = 0;
        for (Lutemon lutemon : lutemons) {
            System.out.println(i++ + ": " + lutemon.getName());
        }
    }

    public void listLutemonsInformation() {
        for (Lutemon lutemon : lutemons) {
            lutemon.printSpecs();
        }
    }

    public void saveLutemons(Context context) {
        try {
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("lutemons.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemons);
            lutemonWriter.close();
        } catch (IOException e) {
            System.out.println("Lutemonien tallentaminen ei onnistunut!");
        }

    }

    public void loadLutemons(Context context) {
        try {
            ObjectInputStream lutemonReader = new ObjectInputStream(context.openFileInput("lutemons.data"));
            lutemons = (ArrayList<Lutemon>) lutemonReader.readObject();
            lutemonReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Lutemonien lukeminen ei onnistunut!");
            e.printStackTrace();
        }
    }
}
