package com.example.popcorntest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "popcorn.db";
    private static final int DATABASE_VERSION = 3;  // Updated version to handle schema changes

    // Movies table
    private static final String TABLE_MOVIES = "movies";
    public static final String COLUMN_MOVIE_ID = "id";
    public static final String COLUMN_MOVIE_NAME = "name";
    public static final String COLUMN_MOVIE_GENRE = "genre";
    public static final String COLUMN_MOVIE_CAST = "cast";
    public static final String COLUMN_MOVIE_CREW = "crew";
    public static final String COLUMN_MOVIE_LANGUAGE = "language";
    public static final String COLUMN_MOVIE_RATINGS = "ratings";

    // Theaters table
    private static final String TABLE_THEATERS = "theaters";
    public static final String COLUMN_THEATER_ID = "id";
    public static final String COLUMN_THEATER_NAME = "name";

    // Showtimes table
    private static final String TABLE_SHOWTIMES = "showtimes";
    public static final String COLUMN_SHOWTIME_ID = "id";
    public static final String COLUMN_SHOWTIME_MOVIE_ID = "movie_id";
    public static final String COLUMN_SHOWTIME_THEATER_ID = "theater_id";
    public static final String COLUMN_SHOWTIME_TIME = "time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Movies table without description column
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + " (" +
                COLUMN_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MOVIE_NAME + " TEXT, " +
                COLUMN_MOVIE_GENRE + " TEXT, " +
                COLUMN_MOVIE_CAST + "TEXT, " +  // Removed backticks for casting
                COLUMN_MOVIE_CREW + " TEXT, " +
                COLUMN_MOVIE_LANGUAGE + " TEXT, " +
                COLUMN_MOVIE_RATINGS + " TEXT)";
        db.execSQL(CREATE_MOVIES_TABLE);

        // Create Theaters table
        String CREATE_THEATERS_TABLE = "CREATE TABLE " + TABLE_THEATERS + " (" +
                COLUMN_THEATER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_THEATER_NAME + " TEXT NOT NULL)";
        db.execSQL(CREATE_THEATERS_TABLE);

        // Create Showtimes table
        String CREATE_SHOWTIMES_TABLE = "CREATE TABLE " + TABLE_SHOWTIMES + " (" +
                COLUMN_SHOWTIME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SHOWTIME_MOVIE_ID + " INTEGER, " +
                COLUMN_SHOWTIME_THEATER_ID + " INTEGER, " +
                COLUMN_SHOWTIME_TIME + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_SHOWTIME_MOVIE_ID + ") REFERENCES " + TABLE_MOVIES + "(" + COLUMN_MOVIE_ID + "), " +
                "FOREIGN KEY (" + COLUMN_SHOWTIME_THEATER_ID + ") REFERENCES " + TABLE_THEATERS + "(" + COLUMN_THEATER_ID + "))";
        db.execSQL(CREATE_SHOWTIMES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle schema changes for version upgrades
        if (oldVersion < 3) {
            // No need to add the description column here anymore as it's removed
        }
    }

    // Add Movie method
    public long addMovie(String name, String genre, String cast, String crew, String language, String ratings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIE_NAME, name);
        values.put(COLUMN_MOVIE_GENRE, genre);
        values.put(COLUMN_MOVIE_CAST, cast);
        values.put(COLUMN_MOVIE_CREW, crew);
        values.put(COLUMN_MOVIE_LANGUAGE, language);
        values.put(COLUMN_MOVIE_RATINGS, ratings);

        long result = db.insert(TABLE_MOVIES, null, values);
        db.close();
        return result;
    }

    // Get All Movies method
    public Cursor getAllMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_MOVIE_ID, COLUMN_MOVIE_NAME, COLUMN_MOVIE_GENRE, COLUMN_MOVIE_CAST,
                COLUMN_MOVIE_CREW, COLUMN_MOVIE_LANGUAGE, COLUMN_MOVIE_RATINGS
        };
        return db.query(TABLE_MOVIES, columns, null, null, null, null, null);
    }

    // Add Theater method
    public long addTheater(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_THEATER_NAME, name);

        long result = db.insert(TABLE_THEATERS, null, values);
        db.close();
        return result;
    }

    // Get All Theaters method
    public Cursor getAllTheaters() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_THEATER_ID, COLUMN_THEATER_NAME};
        return db.query(TABLE_THEATERS, columns, null, null, null, null, null);
    }

    // Add Showtime method
    public long addShowtime(int movieId, int theaterId, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SHOWTIME_MOVIE_ID, movieId);
        values.put(COLUMN_SHOWTIME_THEATER_ID, theaterId);
        values.put(COLUMN_SHOWTIME_TIME, time);

        long result = db.insert(TABLE_SHOWTIMES, null, values);
        db.close();
        return result;
    }

    // Get Showtimes for a movie
    public Cursor getShowtimesForMovie(int movieId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_SHOWTIME_ID, COLUMN_SHOWTIME_TIME, COLUMN_SHOWTIME_THEATER_ID};
        String selection = COLUMN_SHOWTIME_MOVIE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(movieId)};
        return db.query(TABLE_SHOWTIMES, columns, selection, selectionArgs, null, null, null);
    }

    // Helper method to get movie ID from movie name
    public int getMovieId(String movieName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_MOVIES, new String[]{COLUMN_MOVIE_ID},
                    COLUMN_MOVIE_NAME + " = ?", new String[]{movieName},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(COLUMN_MOVIE_ID);
                if (columnIndex != -1) {
                    return cursor.getInt(columnIndex);
                } else {
                    Log.e("Database Error", "Column index not found for " + COLUMN_MOVIE_ID);
                }
            }
        } catch (Exception e) {
            Log.e("Database Error", "Error retrieving movie ID", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return -1; // Return -1 if movie ID not found
    }

    // Helper method to get theater ID from theater name
    public int getTheaterId(String theaterName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_THEATERS, new String[]{COLUMN_THEATER_ID},
                    COLUMN_THEATER_NAME + " = ?", new String[]{theaterName},
                    null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(COLUMN_THEATER_ID);
                if (columnIndex != -1) {
                    return cursor.getInt(columnIndex);
                } else {
                    Log.e("Database Error", "Column index not found for " + COLUMN_THEATER_ID);
                }
            }
        } catch (Exception e) {
            Log.e("Database Error", "Error retrieving theater ID", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return -1; // Return -1 if theater ID not found
    }
}
