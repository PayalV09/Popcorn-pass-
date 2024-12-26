package com.example.popcorntest1.models;

public class Seat {
    private boolean isBooked;
    private boolean isSelected;
    private int seatNumber;

    // Constructor to initialize Seat with its booking status and seat number
    public Seat(boolean isBooked, int seatNumber) {
        this.isBooked = isBooked;
        this.seatNumber = seatNumber;  // Set the seat number
        this.isSelected = false;  // Default selection state is false
    }

    // Getter for isBooked status
    public boolean isBooked() {
        return isBooked;
    }

    // Setter for isBooked status
    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    // Getter for isSelected status
    public boolean isSelected() {
        return isSelected;
    }

    // Setter for isSelected status
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Getter for seatNumber
    public int getSeatNumber() {
        return seatNumber;
    }

    // Setter for seatNumber
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
