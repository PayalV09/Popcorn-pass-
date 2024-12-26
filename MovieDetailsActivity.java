package com.example.popcorntest1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.popcorntest1.models.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Get the Movie object from the Intent
        movie = getIntent().getParcelableExtra("movie");

        if (movie != null) {
            // Update UI elements with movie details
            ((TextView) findViewById(R.id.textViewTitle)).setText(movie.getTitle());
            ((TextView) findViewById(R.id.textViewGenre)).setText("Genre: " + movie.getGenre());
            ((TextView) findViewById(R.id.textViewLanguage)).setText("Language: " + movie.getLanguage());
            ((TextView) findViewById(R.id.textViewCast)).setText("Cast: " + movie.getCast());
            ((TextView) findViewById(R.id.textViewCrew)).setText("Crew: " + movie.getCrew());
            ((TextView) findViewById(R.id.textViewRating)).setText("Rating: " + movie.getRating());

            ImageView imageViewPoster = findViewById(R.id.imageViewPoster);

            // Set the image using the drawable resource ID passed in the Movie object
            if (movie.getImageResource() != 0) {
                imageViewPoster.setImageResource(movie.getImageResource());  // Use drawable resource ID
            } else {
                // Optionally handle the case where the drawable resource ID is invalid
                imageViewPoster.setImageResource(R.drawable.placeholder_image); // Set a placeholder image
            }
        }

        // Button to navigate to TheaterListingActivity
        Button buttonToTheater = findViewById(R.id.buttonToTheater);
        buttonToTheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to go to TheaterListingActivity
                Intent intent = new Intent(MovieDetailsActivity.this, TheaterListingActivity.class);
                intent.putExtra("movie", movie);
                // Pass the movie title using movie.getTitle()
                intent.putExtra("movieTitle", movie.getTitle()); // Pass the correct movie title

                // Start the activity
                startActivity(intent);
            }
        });
    }
}
