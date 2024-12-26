package com.example.popcorntest1.models;

public class ManageTheater {
    // Properties of the theater
    private int id;
    private String name;

    // Constructor
    public ManageTheater(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Optionally, you can override toString() to display theater info easily
    @Override
    public String toString() {
        return "Theater ID: " + id + ", Name: " + name;
    }
}
