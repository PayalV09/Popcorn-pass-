package com.example.popcorntest1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popcorntest1.Databasehelper.MovieDatabaseHelper;

public class Manage_Movies_Activity extends AppCompatActivity {

    private MovieDatabaseHelper dbHelper;

    private EditText movieId, movieName, movieGenre, movieLanguage, movieCast, movieCrew, movieRatings;
    private Button addMovieBtn, updateMovieBtn, deleteMovieBtn;
    private ListView movieListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_movies);

        dbHelper = new MovieDatabaseHelper(this);

        initializeUI();

        // Add default movies when the activity starts
        addDefaultMovies();

        // Add Movie
        addMovieBtn.setOnClickListener(v -> addMovie());

        // Update Movie
        updateMovieBtn.setOnClickListener(v -> updateMovie());

        // Delete Movie
        deleteMovieBtn.setOnClickListener(v -> deleteMovie());
    }

    private void initializeUI() {
        movieId = findViewById(R.id.movieId);
        movieName = findViewById(R.id.movieName);
        movieGenre = findViewById(R.id.movieGenre);
        movieLanguage = findViewById(R.id.movieLanguage);
        movieCast = findViewById(R.id.movieActor);
        movieCrew = findViewById(R.id.movieCrew);
        movieRatings = findViewById(R.id.movieRatings);

        addMovieBtn = findViewById(R.id.addMovieBtn);
        updateMovieBtn = findViewById(R.id.updateMovieBtn);
        deleteMovieBtn = findViewById(R.id.deleteMovieBtn);
        movieListView = findViewById(R.id.movieListView);
    }

    // Method to add default movies
    private void addDefaultMovies() {
        String[] movieNames = {
                "Pushpa 2: The Rule",
                "Mufasa: The Lion King",
                "Moana 2",
                "Dharmarakshak Mahaveer Chhatrapati Sambhaji Maharaj",
                "Zero Se Restart"
        };
        String[] genres = {
                "Action",
                "Animation",
                "Animation",
                "Drama",
                "Drama"
        };
        String[] languages = {
                "Telugu",
                "English",
                "English",
                "Marathi",
                "Hindi"
        };
        String[] casts = {
                "Allu Arjun, Rashmika Mandanna",
                "Aaron Blaise, Rob LaDuca",
                "Auli'i Cravalho, Dwayne Johnson",
                "Chhatrapati Sambhaji Maharaj, Amol Kolhe",
                "Rajeev Khandelwal, Zoa Morani"
        };
        String[] crews = {
                "Sukumar (Director)",
                "Aaron Blaise, Rob LaDuca (Directors)",
                "Ron Clements, John Musker (Directors)",
                "Ravi Jadhav (Director)",
                "Anil Kumar (Director)"
        };
        double[] ratings = {8.0, 7.0, 7.5, 8.0, 6.5};

        // Insert default movie data into the database
        for (int i = 0; i < movieNames.length; i++) {
            dbHelper.addMovie(movieNames[i], genres[i], languages[i], casts[i], crews[i], ratings[i]);
        }
    }

    private void addMovie() {
        if (areFieldsValid()) {
            String name = movieName.getText().toString();
            String genre = movieGenre.getText().toString();
            String language = movieLanguage.getText().toString();
            String cast = movieCast.getText().toString();
            String crew = movieCrew.getText().toString();
            double ratings = parseRatings();

            if (ratings == -1) return;

            long result = dbHelper.addMovie(name, genre, language, cast, crew, ratings);
            if (result != -1) {
                showToast("Movie Added Successfully!");
                clearFields();
                updateMovieList();
            } else {
                showToast("Failed to Add Movie");
            }
        }
    }

    private void updateMovie() {
        if (areFieldsValidForUpdate()) {
            int id = Integer.parseInt(movieId.getText().toString());
            String name = movieName.getText().toString();
            String genre = movieGenre.getText().toString();
            String language = movieLanguage.getText().toString();
            String cast = movieCast.getText().toString();
            String crew = movieCrew.getText().toString();
            double ratings = parseRatings();

            if (ratings == -1) return;

            int rowsUpdated = dbHelper.updateMovie(id, name, genre, language, cast, crew, ratings);
            if (rowsUpdated > 0) {
                showToast("Movie Updated Successfully!");
                clearFields();
                updateMovieList();
            } else {
                showToast("Failed to Update Movie");
            }
        }
    }

    private void deleteMovie() {
        String idText = movieId.getText().toString();
        if (idText.isEmpty()) {
            showToast("Please enter a valid movie ID");
            return;
        }
        int id = Integer.parseInt(idText);
        int rowsDeleted = dbHelper.deleteMovie(id);
        if (rowsDeleted > 0) {
            showToast("Movie Deleted Successfully!");
            clearFields();
            updateMovieList();
        } else {
            showToast("Failed to Delete Movie");
        }
    }

    private boolean areFieldsValid() {
        if (movieName.getText().toString().isEmpty() ||
                movieGenre.getText().toString().isEmpty() ||
                movieLanguage.getText().toString().isEmpty() ||
                movieCast.getText().toString().isEmpty() ||
                movieCrew.getText().toString().isEmpty() ||
                movieRatings.getText().toString().isEmpty()) {
            showToast("All fields must be filled!");
            return false;
        }
        return true;
    }

    private boolean areFieldsValidForUpdate() {
        if (movieId.getText().toString().isEmpty()) {
            showToast("Please enter a valid movie ID");
            return false;
        }
        return areFieldsValid();
    }

    private double parseRatings() {
        double ratings = 0;
        try {
            ratings = Double.parseDouble(movieRatings.getText().toString());
        } catch (NumberFormatException e) {
            showToast("Invalid rating value!");
            return -1;
        }
        return ratings;
    }

    private void clearFields() {
        movieId.setText("");
        movieName.setText("");
        movieGenre.setText("");
        movieLanguage.setText("");
        movieCast.setText("");
        movieCrew.setText("");
        movieRatings.setText("");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateMovieList() {
        // Update the ListView with the current movies (to be implemented with an adapter)
        // This will require setting up an ArrayAdapter or a custom Adapter to populate the ListView
    }
}
