package com.example.popcorntest1;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button manageMoviesButton, manageTheatersButton, manageShowtimesButton, goToMovieListButton;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize views
        initializeViews();

        // Set up listeners for buttons
        setButtonListeners();

        // Check admin privileges and manage "Go to Movie List" visibility
        handleAdminPrivileges();

        // Request storage permission if not granted
        requestStoragePermission();
    }

    // Initialize all views in the activity
    private void initializeViews() {
        manageMoviesButton = findViewById(R.id.btn_manage_movies);
        manageTheatersButton = findViewById(R.id.btn_manage_theaters);
        manageShowtimesButton = findViewById(R.id.btn_manage_showtimes);
        goToMovieListButton = findViewById(R.id.goToMovieListButton);

        TextView adminTitleTextView = findViewById(R.id.tv_admin_dashboard_title);
        adminTitleTextView.setText("Admin Dashboard"); // Set the title dynamically
    }

    // Set up click listeners for the buttons
    private void setButtonListeners() {
        manageMoviesButton.setOnClickListener(v -> {
            Log.d("AdminDashboard", "Manage Movies Button Clicked"); // Debugging log
            Intent intent = new Intent(AdminDashboardActivity.this, Manage_Movies_Activity.class);
            startActivity(intent);

        });

        manageTheatersButton.setOnClickListener(v -> {
            Log.d("AdminDashboard", "Manage Theaters Button Clicked"); // Debugging log
            Intent intent = new Intent(AdminDashboardActivity.this, ManageTheaterActivity.class);
            startActivity(intent);
        });

        manageShowtimesButton.setOnClickListener(v -> {
            Log.d("AdminDashboard", "Manage Showtimes Button Clicked"); // Debugging log
            Intent intent = new Intent(AdminDashboardActivity.this, ManageShowtimesActivity.class);
            startActivity(intent);
        });
    }

    // Check if the user is an admin and handle the visibility of "Go to Movie List" button
    private void handleAdminPrivileges() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", "payalvidhate09@gmail.com");
        String userRole = sharedPreferences.getString("user_role", "admin_user");

        Log.d("AdminDashboard", "User Email: " + userEmail); // Debugging log
        Log.d("AdminDashboard", "User Role: " + userRole); // Debugging log

        // Verify if the user is an admin
        if ("payalvidhate09@gmail.com".equals(userEmail) && "admin_user".equals(userRole)) {
            // Show the "Go to Movie List" button and set its listener
            goToMovieListButton.setVisibility(View.VISIBLE);
            goToMovieListButton.setOnClickListener(v -> {
                Log.d("AdminDashboard", "Go to Movie List Button Clicked"); // Debugging log
                Intent intent = new Intent(AdminDashboardActivity.this, MovieListingActivity.class);
                intent.putExtra("isAdmin", true); // Indicate this is an admin navigation
                startActivity(intent);
            });
        } else {
            // Hide the button for non-admin users
            goToMovieListButton.setVisibility(View.GONE);
        }
    }

    // Request storage permission if not already granted
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
        }
    }

    // Handle the result of the permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with storage-related operations if necessary
                Log.d("AdminDashboard", "Storage permission granted");
            } else {
                // Permission denied, show a Toast or inform the user
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
