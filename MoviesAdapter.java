package com.example.popcorntest1.adpater2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popcorntest1.DatabaseHelper;
import com.example.popcorntest1.R;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private Context context;
    private Cursor cursor;

    public MoviesAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // Move cursor to the correct position
        if (cursor != null && cursor.moveToPosition(position)) {
            // Safely retrieve column indices
            int movieNameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOVIE_NAME);
            int movieGenreIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOVIE_GENRE);
            int movieCastIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOVIE_CAST);
            int movieCrewIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOVIE_CREW);
            int movieLanguageIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOVIE_LANGUAGE);
            int movieRatingsIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOVIE_RATINGS);

            // Check if the columns are valid before accessing them
            if (movieNameIndex != -1 && movieGenreIndex != -1 && movieCastIndex != -1 &&
                    movieCrewIndex != -1 && movieLanguageIndex != -1 && movieRatingsIndex != -1) {

                // Get data from the cursor
                String movieName = cursor.getString(movieNameIndex);
                String movieGenre = cursor.getString(movieGenreIndex);
                String movieCast = cursor.getString(movieCastIndex);
                String movieCrew = cursor.getString(movieCrewIndex);
                String movieLanguage = cursor.getString(movieLanguageIndex);
                String movieRatings = cursor.getString(movieRatingsIndex);

                // Bind the data to the view
                holder.textViewMovieName.setText(movieName);
                holder.textViewMovieGenre.setText(movieGenre);
                holder.textViewMovieCast.setText("Cast: " + movieCast);
                holder.textViewMovieCrew.setText("Crew: " + movieCrew);
                holder.textViewMovieLanguage.setText("Language: " + movieLanguage);
                holder.textViewMovieRatings.setText("Ratings: " + movieRatings);
            } else {
                // Handle case where one or more columns are missing
                holder.textViewMovieName.setText("Unknown Movie");
                holder.textViewMovieGenre.setText("Unknown Genre");
                holder.textViewMovieCast.setText("Unknown Cast");
                holder.textViewMovieCrew.setText("Unknown Crew");
                holder.textViewMovieLanguage.setText("Unknown Language");
                holder.textViewMovieRatings.setText("Unknown Ratings");
            }
        }
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the cursor
        return cursor != null ? cursor.getCount() : 0;
    }

    // Method to update the cursor when data changes
    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

    // ViewHolder class to hold the views
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMovieName;
        TextView textViewMovieGenre;
        TextView textViewMovieCast;
        TextView textViewMovieCrew;
        TextView textViewMovieLanguage;
        TextView textViewMovieRatings;

        public MovieViewHolder(View itemView) {
            super(itemView);
            textViewMovieName = itemView.findViewById(R.id.text_view_movie_name);
            textViewMovieGenre = itemView.findViewById(R.id.text_view_movie_genre);
            textViewMovieCast = itemView.findViewById(R.id.text_view_movie_cast);
            textViewMovieCrew = itemView.findViewById(R.id.text_view_movie_crew);
            textViewMovieLanguage = itemView.findViewById(R.id.text_view_movie_language);
            textViewMovieRatings = itemView.findViewById(R.id.text_view_movie_ratings);
        }
    }
}
