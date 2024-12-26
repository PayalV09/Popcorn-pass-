package com.example.popcorntest1;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popcorntest1.Databasehelper.ManageShowtimeDatabaseHelper;

import java.util.Arrays;
import java.util.List;

public class ManageShowtimesActivity extends AppCompatActivity {

    private ManageShowtimeDatabaseHelper dbHelper;
    private Spinner movieSpinner, theaterSpinner;
    private TimePicker timePicker;
    private Button addShowtimeButton;
    private TextView selectedMovieTheaterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_showtimes);

        dbHelper = new ManageShowtimeDatabaseHelper(this);
        movieSpinner = findViewById(R.id.movie_spinner);
        theaterSpinner = findViewById(R.id.theater_spinner);
        timePicker = findViewById(R.id.time_picker);
        addShowtimeButton = findViewById(R.id.add_showtime_button);
        selectedMovieTheaterTextView = findViewById(R.id.selected_movie_theater);

        // Example data for movies and theaters
        List<String> movieList = Arrays.asList("Pushpa2: The Rule", "Mufasa: The Lion King", "Moana 2");
        List<String> theaterList = Arrays.asList("PVR ICON", "INOX", "Cinepolis");

        // Set up the adapters for spinners
        ArrayAdapter<String> movieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, movieList);
        movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movieSpinner.setAdapter(movieAdapter);

        ArrayAdapter<String> theaterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, theaterList);
        theaterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theaterSpinner.setAdapter(theaterAdapter);

        // Set listeners to display selected movie and theater
        movieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                displaySelectedMovieAndTheater();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        theaterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                displaySelectedMovieAndTheater();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        // Add showtime when button is clicked
        addShowtimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShowtime();
            }
        });
    }

    // Method to load movies into the spinner (using example data)
    private void loadMovies() {
        Cursor movieCursor = dbHelper.getAllMovies();
        if (movieCursor != null && movieCursor.getCount() > 0) {
            ArrayAdapter<String> movieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
            movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            int movieNameIndex = movieCursor.getColumnIndex(ManageShowtimeDatabaseHelper.COLUMN_MOVIE_NAME);
            if (movieNameIndex != -1) {
                while (movieCursor.moveToNext()) {
                    String movieName = movieCursor.getString(movieNameIndex);
                    movieAdapter.add(movieName);
                }
            } else {
                Toast.makeText(this, "Movie name column not found!", Toast.LENGTH_SHORT).show();
            }

            movieSpinner.setAdapter(movieAdapter);
            movieCursor.close();
        } else {
            Toast.makeText(this, "No movies available", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to load theaters into the spinner (using example data)
    private void loadTheaters() {
        Cursor theaterCursor = dbHelper.getAllTheaters();
        if (theaterCursor != null && theaterCursor.getCount() > 0) {
            ArrayAdapter<String> theaterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
            theaterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            int theaterNameIndex = theaterCursor.getColumnIndex(ManageShowtimeDatabaseHelper.COLUMN_THEATER_NAME);
            if (theaterNameIndex != -1) {
                while (theaterCursor.moveToNext()) {
                    String theaterName = theaterCursor.getString(theaterNameIndex);
                    theaterAdapter.add(theaterName);
                }
            } else {
                Toast.makeText(this, "Theater name column not found!", Toast.LENGTH_SHORT).show();
            }

            theaterSpinner.setAdapter(theaterAdapter);
            theaterCursor.close();
        } else {
            Toast.makeText(this, "No theaters available", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to add a new showtime
    private void addShowtime() {
        String selectedMovie = movieSpinner.getSelectedItem().toString();
        String selectedTheater = theaterSpinner.getSelectedItem().toString();
        int selectedMovieId = getMovieId(selectedMovie);
        int selectedTheaterId = getTheaterId(selectedTheater);

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        String showtime = String.format("%02d:%02d", hour, minute);

        long result = dbHelper.addShowtime(selectedMovieId, selectedTheaterId, showtime);
        if (result != -1) {
            Toast.makeText(this, "Showtime added successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add showtime", Toast.LENGTH_SHORT).show();
        }
    }

    private int getMovieId(String movieName) {
        Cursor cursor = dbHelper.getAllMovies();
        if (cursor != null && cursor.getCount() > 0) {
            int movieNameIndex = cursor.getColumnIndex(ManageShowtimeDatabaseHelper.COLUMN_MOVIE_NAME);
            int movieIdIndex = cursor.getColumnIndex(ManageShowtimeDatabaseHelper.COLUMN_MOVIE_ID);
            if (movieNameIndex != -1 && movieIdIndex != -1) {
                while (cursor.moveToNext()) {
                    if (cursor.getString(movieNameIndex).equals(movieName)) {
                        int movieId = cursor.getInt(movieIdIndex);
                        cursor.close();
                        return movieId;
                    }
                }
            }
            cursor.close();
        }
        return -1;
    }

    private int getTheaterId(String theaterName) {
        Cursor cursor = dbHelper.getAllTheaters();
        if (cursor != null && cursor.getCount() > 0) {
            int theaterNameIndex = cursor.getColumnIndex(ManageShowtimeDatabaseHelper.COLUMN_THEATER_NAME);
            int theaterIdIndex = cursor.getColumnIndex(ManageShowtimeDatabaseHelper.COLUMN_THEATER_ID);
            if (theaterNameIndex != -1 && theaterIdIndex != -1) {
                while (cursor.moveToNext()) {
                    if (cursor.getString(theaterNameIndex).equals(theaterName)) {
                        int theaterId = cursor.getInt(theaterIdIndex);
                        cursor.close();
                        return theaterId;
                    }
                }
            }
            cursor.close();
        }
        return -1;
    }

    // Method to display selected movie and theater
    private void displaySelectedMovieAndTheater() {
        String selectedMovie = movieSpinner.getSelectedItem().toString();
        String selectedTheater = theaterSpinner.getSelectedItem().toString();
        selectedMovieTheaterTextView.setText("Selected Movie: " + selectedMovie + "\nSelected Theater: " + selectedTheater);
    }
}
