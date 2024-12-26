package com.example.popcorntest1.Databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "MovieDatabaseHelper";

    private static final String DATABASE_NAME = "popcornpass.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_MOVIES = "movies";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_LANGUAGE = "language";
    private static final String COLUMN_CREW = "crew";
    private static final String COLUMN_ACTOR = "actor";
    private static final String COLUMN_RATINGS = "ratings";

    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_GENRE + " TEXT NOT NULL, " +
                COLUMN_LANGUAGE + " TEXT NOT NULL, " +
                COLUMN_CREW + " TEXT, " +
                COLUMN_ACTOR + " TEXT, " +
                COLUMN_RATINGS + " REAL);";
        db.execSQL(CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
            onCreate(db);
        }
    }

    // Add a Movie
    public long addMovie(String name, String genre, String language, String actor, String crew, double ratings) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = -1;

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_GENRE, genre);
            values.put(COLUMN_LANGUAGE, language);
            values.put(COLUMN_ACTOR, actor);
            values.put(COLUMN_CREW, crew);
            values.put(COLUMN_RATINGS, ratings);

            // Insert the movie into the table
            id = db.insert(TABLE_MOVIES, null, values);

            if (id == -1) {
                Log.e(TAG, "Error adding movie: Insert failed.");
            } else {
                Log.d(TAG, "Movie added with ID: " + id);
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error adding movie: " + e.getMessage(), e);
        } finally {
            db.close();
        }

        return id;
    }

    // Update a Movie
    public int updateMovie(int id, String name, String genre, String language, String actor, String crew, double ratings) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsUpdated = 0;

        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_GENRE, genre);
            values.put(COLUMN_LANGUAGE, language);
            values.put(COLUMN_ACTOR, actor);
            values.put(COLUMN_CREW, crew);
            values.put(COLUMN_RATINGS, ratings);

            // Update the movie in the table
            rowsUpdated = db.update(TABLE_MOVIES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});

            Log.d(TAG, "Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            Log.e(TAG, "Error updating movie: " + e.getMessage(), e);
        } finally {
            db.close();
        }

        return rowsUpdated;
    }

    // Delete a Movie
    public int deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = 0;

        try {
            // Delete the movie with the specified ID
            rowsDeleted = db.delete(TABLE_MOVIES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
            Log.d(TAG, "Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            Log.e(TAG, "Error deleting movie: " + e.getMessage(), e);
        } finally {
            db.close();
        }

        return rowsDeleted;
    }
}
