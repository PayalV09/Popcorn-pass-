package com.example.popcorntest1.Databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ManageShowtimeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "popcornDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_MOVIES = "movies";
    public static final String COLUMN_MOVIE_ID = "id";
    public static final String COLUMN_MOVIE_NAME = "name";

    public static final String TABLE_THEATERS = "theaters";
    public static final String COLUMN_THEATER_ID = "id";
    public static final String COLUMN_THEATER_NAME = "name";

    public static final String TABLE_SHOWTIMES = "showtimes";
    public static final String COLUMN_SHOWTIME_ID = "id";
    public static final String COLUMN_MOVIE_ID_FK = "movie_id";
    public static final String COLUMN_THEATER_ID_FK = "theater_id";
    public static final String COLUMN_SHOWTIME = "showtime";

    public ManageShowtimeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create movies table
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
                + COLUMN_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MOVIE_NAME + " TEXT)";
        db.execSQL(CREATE_MOVIES_TABLE);

        // Create theaters table
        String CREATE_THEATERS_TABLE = "CREATE TABLE " + TABLE_THEATERS + "("
                + COLUMN_THEATER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_THEATER_NAME + " TEXT)";
        db.execSQL(CREATE_THEATERS_TABLE);

        // Create showtimes table
        String CREATE_SHOWTIMES_TABLE = "CREATE TABLE " + TABLE_SHOWTIMES + "("
                + COLUMN_SHOWTIME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MOVIE_ID_FK + " INTEGER,"
                + COLUMN_THEATER_ID_FK + " INTEGER,"
                + COLUMN_SHOWTIME + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_MOVIE_ID_FK + ") REFERENCES " + TABLE_MOVIES + "(" + COLUMN_MOVIE_ID + "),"
                + "FOREIGN KEY(" + COLUMN_THEATER_ID_FK + ") REFERENCES " + TABLE_THEATERS + "(" + COLUMN_THEATER_ID + "))";
        db.execSQL(CREATE_SHOWTIMES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOWTIMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEATERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    // Add showtime
    public long addShowtime(int movieId, int theaterId, String showtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MOVIE_ID_FK, movieId);
        values.put(COLUMN_THEATER_ID_FK, theaterId);
        values.put(COLUMN_SHOWTIME, showtime);

        long result = db.insert(TABLE_SHOWTIMES, null, values);
        db.close();
        return result;
    }

    // Get all movies
    public Cursor getAllMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MOVIES, null, null, null, null, null, null);
    }

    // Get all theaters
    public Cursor getAllTheaters() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_THEATERS, null, null, null, null, null, null);
    }
}
