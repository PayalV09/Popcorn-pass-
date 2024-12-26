package com.example.popcorntest1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popcorntest1.Databasehelper.DatabaseHelper;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView movieTitleTextView, theaterTextView, showtimeTextView, totalSeatsTextView,
            totalPriceTextView, selectedSeatsTextView, paymentIdTextView,
            bookingIdTextView, paymentStatusTextView, ticketTextView, paymentDateTextView;
    private Button confirmTicketButton;
    private RadioButton codRadioButton;
    private String paymentStatus = "pending";  // Default COD status
    private String bookingId;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        initializeViews();
        databaseHelper = new DatabaseHelper(this); // Initialize the database helper
        populateDataFromIntent();
        setupConfirmButton();
        setupPaymentMethodSelection();
    }

    private void initializeViews() {
        // Initialize all TextViews and Button
        movieTitleTextView = findViewById(R.id.tv_movie_title);
        theaterTextView = findViewById(R.id.tv_theater_name);
        showtimeTextView = findViewById(R.id.tv_showtime);
        totalSeatsTextView = findViewById(R.id.tv_total_seats);
        totalPriceTextView = findViewById(R.id.tv_total_price);
        selectedSeatsTextView = findViewById(R.id.tv_selected_seats);
        paymentIdTextView = findViewById(R.id.tv_payment_id);
        bookingIdTextView = findViewById(R.id.tv_booking_id);
        paymentStatusTextView = findViewById(R.id.tv_payment_status);
        confirmTicketButton = findViewById(R.id.confirmButton);
        codRadioButton = findViewById(R.id.codRadioButton);
        ticketTextView = findViewById(R.id.ticketTextView);
        paymentDateTextView = findViewById(R.id.tv_payment_date); // Add the new TextView for payment date
    }

    private void populateDataFromIntent() {
        // Retrieve data from Intent with fallback defaults
        String movieTitle = getIntent().getStringExtra("movieTitle");
        String theaterName = getIntent().getStringExtra("theaterName");
        String selectedShowtime = getIntent().getStringExtra("showtime");
        int totalSeats = getIntent().getIntExtra("totalSeats", 0);
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);
        String selectedSeats = getIntent().getStringExtra("selectedSeats");

        paymentStatus = getIntent().getStringExtra("paymentStatus");
        bookingId = getIntent().getStringExtra("bookingId");
        String paymentId = getIntent().getStringExtra("paymentId");
        String paymentDate = getIntent().getStringExtra("paymentDate"); // Retrieve payment date

        
        // Default to "pending" if paymentStatus is not passed
        if (paymentStatus == null) {
            paymentStatus = "pending";
        }

        // Ensure paymentId is valid and update payment status
        if (paymentId != null) {
            try {
                long paymentIdNumber = Long.parseLong(paymentId); // Use long in case the number is too large
                // Set status based on whether paymentId is even or odd
                paymentStatus = (paymentIdNumber % 2 == 0) ? "completed" : "pending";
            } catch (NumberFormatException e) {
                e.printStackTrace();
                paymentStatus = "pending"; // Default to pending if invalid format
            }
        }

        // Set the data to the TextViews
        movieTitleTextView.setText(getStringOrFallback("Movie: %s", movieTitle, "Not Available"));
        theaterTextView.setText(getStringOrFallback("Theater: %s", theaterName, "Not Available"));
        showtimeTextView.setText(getStringOrFallback("Showtime: %s", selectedShowtime, "Not Selected"));
        totalSeatsTextView.setText(String.format("Seats: %d", totalSeats));
        totalPriceTextView.setText(String.format("Total Price: ₹%d", totalPrice));
        selectedSeatsTextView.setText(getStringOrFallback("Selected Seats: %s", selectedSeats, "Not Available"));

        // Payment details
        paymentIdTextView.setText(getStringOrFallback("Payment ID: %s", paymentId, "Not Available"));
        bookingIdTextView.setText(getStringOrFallback("Booking ID: %s", bookingId, "Not Available"));
        paymentStatusTextView.setText(getStringOrFallback("Payment Status: %s", paymentStatus, "Not Available"));

        // Display payment date if available
        if (paymentDate != null) {
            paymentDateTextView.setText("Payment Date: " + paymentDate);
        }

        // Ensure payment status is correctly updated in the UI
        updatePaymentStatus();
    }

    private void setupPaymentMethodSelection() {
        // Only handling COD as payment method
        codRadioButton.setOnClickListener(v -> {
            paymentStatus = "pending";  // Set as pending for Cash on Delivery
            updatePaymentStatus();
        });
    }

    private void updatePaymentStatus() {
        if ("pending".equals(paymentStatus)) {
            paymentStatusTextView.setText("Payment Status: Pending");
            confirmTicketButton.setEnabled(true);  // Enable the Confirm button for pending payment
        } else if ("completed".equals(paymentStatus)) {
            paymentStatusTextView.setText("Payment Status: Completed");
            confirmTicketButton.setEnabled(true);  // Enable the Confirm button if payment is completed
        } else {
            paymentStatusTextView.setText("Payment Status: Unknown");
            confirmTicketButton.setEnabled(false); // Disable the button if status is unknown
        }
    }

    private void setupConfirmButton() {
        confirmTicketButton.setOnClickListener(v -> {
            if ("pending".equals(paymentStatus)) {
                showPaymentNotCompletedMessage();
            } else if ("completed".equals(paymentStatus)) {
                showTicket();
                // Save payment status to the database (completed)
                databaseHelper.saveBooking(bookingId, getIntent().getStringExtra("paymentId"), "completed");
            } else {
                showPaymentNotCompletedMessage();
            }
        });
    }

    private void showTicket() {
        // Code to display the ticket (this can be an image, a confirmation message, or PDF)
        ticketTextView.setVisibility(View.VISIBLE); // Make ticket details visible
        ticketTextView.setText("Your Ticket Details: \n\nMovie: " + movieTitleTextView.getText().toString() +
                "\nTheater: " + theaterTextView.getText().toString() +
                "\nSeats: " + selectedSeatsTextView.getText().toString() +
                "\nPrice: " + totalPriceTextView.getText().toString());
    }

    private void showPaymentNotCompletedMessage() {
        // Show a message or disable the confirm button
        paymentStatusTextView.setText("Payment has not been completed. Please complete the payment to confirm your booking.");
        confirmTicketButton.setEnabled(false); // Disable the confirm button
    }

    private String getStringOrFallback(String format, String value, String fallback) {
        return String.format(format, value != null ? value : fallback);
    }
}
