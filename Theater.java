package com.example.popcorntest1.models;

public class Theater {
    private String name;
    private String location;
    private String details;

    // Constructor
    public Theater(String name, String location, String details) {
        this.name = name;
        this.location = location;
        this.details = details;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDetails() {
        return details;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // Optional: Override toString for better object representation
    @Override
    public String toString() {
        return "Theater{name='" + name + "', location='" + location + "', details='" + details + "'}";
    }
}
