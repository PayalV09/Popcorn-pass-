package com.example.popcorntest1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ETicket_Activity extends AppCompatActivity {

    // Declare TextViews to display e-ticket information
    TextView tvMovieTitle, tvTheaterName, tvShowtime, tvTotalSeats, tvTotalPrice,
            tvSelectedSeats, tvPaymentId, tvBookingId, tvPaymentDate, tvPaymentMethod, tvPaymentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eticket);

        // Initialize TextViews
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvTheaterName = findViewById(R.id.tv_theater_name);
        tvShowtime = findViewById(R.id.tv_showtime);
        tvTotalSeats = findViewById(R.id.tv_total_seats);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        tvSelectedSeats = findViewById(R.id.tv_selected_seats);
        tvPaymentId = findViewById(R.id.tv_payment_id);
        tvBookingId = findViewById(R.id.tv_booking_id);
        tvPaymentDate = findViewById(R.id.tv_payment_date);
        tvPaymentMethod = findViewById(R.id.tv_payment_method);
        tvPaymentStatus = findViewById(R.id.tv_payment_status);

        // Get data passed from the ConfirmationActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movieTitle = extras.getString("movieTitle");
            String theaterName = extras.getString("theaterName");
            String showtime = extras.getString("showtime");
            String totalSeats = extras.getString("totalSeats");
            String totalPrice = extras.getString("totalPrice");
            String selectedSeats = extras.getString("selectedSeats");
            String paymentId = extras.getString("paymentId");
            String bookingId = extras.getString("bookingId");
            String paymentDate = extras.getString("paymentDate");
            String paymentMethod = extras.getString("paymentMethod");
            String paymentStatus = extras.getString("paymentStatus");

            // Set the data to the TextViews
            tvMovieTitle.setText("Movie Title: " + movieTitle);
            tvTheaterName.setText("Theater Name: " + theaterName);
            tvShowtime.setText("Showtime: " + showtime);
            tvTotalSeats.setText("Total Seats: " + totalSeats);
            tvTotalPrice.setText("Total Price: " + totalPrice);
            tvSelectedSeats.setText("Selected Seats: " + selectedSeats);
            tvPaymentId.setText("Payment ID: " + paymentId);
            tvBookingId.setText("Booking ID: " + bookingId);
            tvPaymentDate.setText("Payment Date: " + paymentDate);
            tvPaymentMethod.setText("Payment Method: " + paymentMethod);
            tvPaymentStatus.setText("Payment Status: " + paymentStatus);
        }
    }
}
