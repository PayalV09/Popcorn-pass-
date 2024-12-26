package com.example.popcorntest1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class PaymentSummaryActivity extends AppCompatActivity {

    private TextView movieTitleTextView, theaterTextView, showtimeTextView, totalSeatsTextView, totalPriceTextView, selectedSeatsTextView, paymentDateTextView;
    private Button proceedPaymentButton, selectPaymentDateButton;

    private String selectedPaymentDateTime = "Not Selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);

        // Initialize views
        movieTitleTextView = findViewById(R.id.movieTitleTextView);
        theaterTextView = findViewById(R.id.theaterTextView);
        showtimeTextView = findViewById(R.id.showtimeTextView);
        totalSeatsTextView = findViewById(R.id.totalSeatsTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        selectedSeatsTextView = findViewById(R.id.selectedSeatsTextView);
        paymentDateTextView = findViewById(R.id.paymentDateTextView);
        selectPaymentDateButton = findViewById(R.id.selectPaymentDateButton);
        proceedPaymentButton = findViewById(R.id.proceedPaymentButton);

        // Retrieve intent data
        Intent intent = getIntent();
        String movieTitle = intent.getStringExtra("movieTitle");
        String theaterName = intent.getStringExtra("theaterName");
        String selectedShowtime = intent.getStringExtra("showtime");
        int totalSeats = intent.getIntExtra("totalSeats", 0);
        int totalPrice = intent.getIntExtra("totalPrice", 0);
        ArrayList<Integer> selectedSeats = intent.getIntegerArrayListExtra("selectedSeats");

        // Set data to views
        movieTitleTextView.setText("Movie: " + (movieTitle != null ? movieTitle : "Not Available"));
        theaterTextView.setText("Theater: " + (theaterName != null ? theaterName : "Not Available"));
        showtimeTextView.setText("Showtime: " + (selectedShowtime != null ? selectedShowtime : "Not Selected"));
        totalSeatsTextView.setText("Seats: " + totalSeats);
        totalPriceTextView.setText("Total Price: ₹" + totalPrice);

        // Display selected seat numbers
        if (selectedSeats != null && !selectedSeats.isEmpty()) {
            StringBuilder seatsStringBuilder = new StringBuilder();
            for (int seat : selectedSeats) {
                seatsStringBuilder.append(seat).append(", ");
            }
            seatsStringBuilder.setLength(seatsStringBuilder.length() - 2); // Remove trailing comma and space
            selectedSeatsTextView.setText("Selected Seats: " + seatsStringBuilder.toString());
        } else {
            selectedSeatsTextView.setText("No seats selected");
        }

        // Initialize payment method spinner
        Spinner paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);
        String[] paymentMethods = {"Cash On Delivery"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(adapter);

        paymentMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMethod = paymentMethods[position];
                Toast.makeText(PaymentSummaryActivity.this, "Selected Payment: " + selectedMethod, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Handle no selection
            }
        });

        // Handle select payment date button
        selectPaymentDateButton.setOnClickListener(v -> openDateTimePicker());

        // Handle proceed payment button click
        proceedPaymentButton.setOnClickListener(v -> {
            // Mock payment and booking details

            // Get the selected payment method
            String paymentMethod = paymentMethods[paymentMethodSpinner.getSelectedItemPosition()];

            // Generate mock payment ID and booking ID
            String paymentId = "PAY" + System.currentTimeMillis(); // Example mock payment ID
            String bookingId = "BOOK" + System.currentTimeMillis(); // Example mock booking ID

            // Create Intent to move to ConfirmationActivity
            Intent confirmationIntent = new Intent(PaymentSummaryActivity.this, ConfirmationActivity.class);

            // Add all required data to the Intent
            confirmationIntent.putExtra("movieTitle", movieTitle != null ? movieTitle : "Not Available");
            confirmationIntent.putExtra("theaterName", theaterName != null ? theaterName : "Not Available");
            confirmationIntent.putExtra("showtime", selectedShowtime != null ? selectedShowtime : "Not Selected");
            confirmationIntent.putExtra("totalSeats", totalSeats);
            confirmationIntent.putExtra("totalPrice", totalPrice);
            confirmationIntent.putExtra("selectedSeats", selectedSeats != null ? selectedSeats.toString() : "No seats selected");
            confirmationIntent.putExtra("paymentDate", selectedPaymentDateTime);  // Pass the selected date
            confirmationIntent.putExtra("paymentMethod", paymentMethod);

            // Add the mock payment ID and booking ID
            confirmationIntent.putExtra("paymentId", paymentId);
            confirmationIntent.putExtra("bookingId", bookingId);

            // Log the data being transferred for debugging
            Log.d("PaymentSummaryActivity", "Data Transferred: " + confirmationIntent.getExtras());

            // Start the ConfirmationActivity
            startActivity(confirmationIntent);
        });
    }

    private void openDateTimePicker() {
        // Get current date and time
        Calendar calendar = Calendar.getInstance();

        // Open DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // Open TimePickerDialog after date is selected
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timeView, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                // Format selected date and time
                selectedPaymentDateTime = android.text.format.DateFormat.format("yyyy-MM-dd HH:mm", calendar).toString();
                paymentDateTextView.setText("Payment Date: " + selectedPaymentDateTime);

            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

            timePickerDialog.show();
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
