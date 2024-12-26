package com.example.popcorntest1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popcorntest1.R;
import com.example.popcorntest1.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movieList;
    private OnMovieClickListener movieClickListener;

    // Constructor for the adapter
    public MovieAdapter(Context context, List<Movie> movieList, OnMovieClickListener movieClickListener) {
        this.context = context;
        this.movieList = movieList;
        this.movieClickListener = movieClickListener;
    }

    // ViewHolder class
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movieImage);
            movieTitle = itemView.findViewById(R.id.movieTitle);

            // Set click listener for each item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the position of the clicked item
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && movieClickListener != null) {
                        // Notify listener with the movie clicked
                        movieClickListener.onMovieClick(movieList.get(position));
                    }
                }
            });
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the movie item layout (item_movie.xml)
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // Get the movie at the current position
        Movie movie = movieList.get(position);

        // Bind the movie title
        holder.movieTitle.setText(movie.getTitle());

        // Set the image using the drawable resource ID
        holder.movieImage.setImageResource(movie.getImageResource());  // Use drawable resource ID
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // Interface to handle item click and pass the movie object
    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }
}
