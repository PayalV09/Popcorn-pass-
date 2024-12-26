package com.example.popcorntest1;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popcorntest1.adapters.MovieAdapter;
import com.example.popcorntest1.models.Movie;
import java.util.ArrayList;
import java.util.List;
public class MovieListingActivity extends AppCompatActivity implements MovieAdapter.OnMovieClickListener {

    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_listing);

        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns in the grid

        // Initialize and populate the movie list
        movieList = new ArrayList<>();
        populateMovieList();

        // Set up adapter with movie list and this as click listener
        movieAdapter = new MovieAdapter(this, movieList, this);
        recyclerViewMovies.setAdapter(movieAdapter);
    }

    private void populateMovieList() {
        // Add movies for display
        movieList.add(new Movie(1, "Pushpa 2: The Rule", "Action, Drama", "Telugu",
                "Allu Arjun, Rashmika Mandanna, Fahadh Faasil", "Sukumar", 8.3, R.drawable.pushpa2));

        movieList.add(new Movie(2, "Mufasa: The Lion King", "Animation, Adventure, Drama", "English",
                "Aaron Pierre, Kelvin Harrison Jr., Billy Eichner", "Barry Jenkins", 7.1, R.drawable.mufasa1));

        movieList.add(new Movie(3, "Moana 2", "Animation, Adventure, Family", "English",
                "Auli Cravalho, Dwayne Johnson, Nicole Scherzinger", "David Hall", 7.2, R.drawable.moana1));
        movieList.add(new Movie(3, "The Sabarmati Report", "Drama, Historical", "Hindi", "Vikrant Massey, Raashi Khanna, Ridhi Dogra", "Dheeraj Sarna", 8.5, R.drawable.sabarmati));


        movieList.add(new Movie(1, "Venom: The Last Dance", "Action", "English", "Tom Hardy, Chiwetel Ejiofor, Michelle Williams", "Tom Hardy, Kelly Marcel", 7.6, R.drawable.venom));
        movieList.add(new Movie(2, "Singham Again", "Action", "Hindi", "Ajay Devgn, Kareena Kapoor", "Rohit Shetty", 8.0, R.drawable.singham));
        movieList.add(new Movie(3, "Bhool Bhulaiyaa 3", "Horror", "Hindi", "Kartik Aaryan,Madhuri Dixit,Rajpal Yadav,Vidya Balan,Tripti Dimri", "Anees Bazmee", 8.3, R.drawable.bhool3));
        movieList.add(new Movie(4, "The Wild Robot", " Children/Animation", "English", "Lupita Nyong'o,Pedro Pascal,Kit Connor", " Chris Sanders", 8.5, R.drawable.wildrobot));
        movieList.add(new Movie(5, "Paani", "Drama", "Marathi", "Subodh Bhave,Kishor Kadam,Adinath Kothare"," Adinath Kothare,Priyanka Chopra", 7.8, R.drawable.panni));
        movieList.add(new Movie(6, "Phullwanti", "Romance", "Marathi", " Prajakta Mali,Gashmeer Mahajani", " Snehal Pravin Tarde", 8.1, R.drawable.phulwanti));
        movieList.add(new Movie(7, "Vettaiyan", "Action", "Tamil", "Rajinikanth,Amitabh Bachchan", " T. J. Gnanavel", 7.9, R.drawable.vetti));
        movieList.add(new Movie(8, "The Greatest Of All Time", "Action", "Tamil", "Vijay,Prashanth,Prabhu Deva", "Venkat Prabhu", 6.8, R.drawable.goat));
        movieList.add(new Movie(9, "Do Patti", "Mystery", "Hindi", "Kajol,Kriti Sanon,Shaheer Sheikh", " Shashanka Chaturvedi", 8.2, R.drawable.dopatti));
        movieList.add(new Movie(10, "Amaran", "Action", "Tamil", "Sivakarthikeyan,Sai Pallavi", " Rajkumar Periasamy", 7.3, R.drawable.amran));

    }

    @Override
    public void onMovieClick(Movie movie) {
        // Pass the selected movie details to MovieDetailsActivity
        Intent intent = new Intent(MovieListingActivity.this,MovieDetailsActivity.class);
        intent.putExtra("movie", movie); // Pass the entire Movie object
        startActivity(intent);
    }

    }

