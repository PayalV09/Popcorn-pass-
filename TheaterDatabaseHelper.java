package com.example.popcorntest1.Databasehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TheaterDatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "popcorn_db";
    private static final int DATABASE_VERSION = 1;

    // Table and columns
    private static final String TABLE_THEATERS = "theaters";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LOCATION = "location";


    // Constructor
    public TheaterDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_THEATERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL UNIQUE, " +
                "location TEXT NOT NULL)";
        db.execSQL(createTableQuery);  // Execute the query
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it exists and recreate it
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEATERS);
        onCreate(db);  // Recreate the table
    }

    /**
     * Add a new theater to the database.
     *
     * @param name Name of the theater
     * @return True if the operation was successful, false otherwise
     */
    public boolean addLocation(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);


        long result = db.insert(TABLE_THEATERS, null, values);
        db.close();  // Close the database connection after insertion

        return result != -1;  // If result == -1, insertion failed
    }

    public boolean addLocation(String name, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put("location", location);

        long result = db.insert(TABLE_THEATERS, null, values);
        db.close();  // Close the database connection after insertion

        return result != -1;  // If result == -1, insertion failed
    }




    /**
         * Get all theaters from the database.
         *
         * @return Cursor object containing all theaters
         */
    public Cursor getAllTheaters() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Query to retrieve all theaters, ordered by name
        return db.query(TABLE_THEATERS, null, null, null, null, null, COLUMN_NAME + " ASC");
    }
    public Cursor getAllLocation() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Query to retrieve all theaters, ordered by name
        return db.query(TABLE_THEATERS, null, null, null, null, null, COLUMN_NAME + " ASC");
    }


    /**
     * Get the theater ID based on its name.
     *
     * @param name Name of the theater
     * @return The ID of the theater, or -1 if not found
     */
    public int getTheaterId(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_THEATERS + " WHERE " + COLUMN_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name});

        int theaterId = -1;  // Default value if not found

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_ID);  // Get column index for 'id'

            if (columnIndex != -1) {
                // Column exists, get the theater ID
                theaterId = cursor.getInt(columnIndex);
            }
            cursor.close();  // Close cursor after use
        }
        return theaterId;
    }



    public int getLocationId(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_THEATERS + " WHERE " + COLUMN_NAME + " = ?";
        Cursor cursor = null;
        int theaterId = -1; // Default value if not found


        try {
            cursor = db.rawQuery(query, new String[]{name});

            if (cursor != null && cursor.moveToFirst()) {
                // Get column index and retrieve the theater ID
                theaterId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log exception for debugging
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor
            }
            db.close(); // Close the database
        }

        return theaterId;
    }

    /**
     * Check if a theater already exists by its name.
     *
     * @param theaterName Name of the theater to check
     * @return True if the theater exists, false otherwise
     */
    public boolean doesTheaterExist(String theaterName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Query to check if the theater exists by its name
            cursor = db.query(
                    TABLE_THEATERS,           // The table name
                    new String[]{COLUMN_ID},  // Columns to retrieve (we only need the id)
                    COLUMN_NAME + " = ?",     // The selection (where clause)
                    new String[]{theaterName},// The selection args (theater name)
                    null,                     // Group by
                    null,                     // Having
                    null                      // Order by
            );

            // Return true if the cursor contains at least one row (theater exists)
            return cursor != null && cursor.getCount() > 0;

        } finally {
            if (cursor != null) {
                cursor.close();  // Close the cursor to avoid memory leaks
            }
        }
    }
    ///// query add location
    public boolean doesLocationExist(String theaterName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Query to check if the theater exists by its name
            cursor = db.query(
                    TABLE_THEATERS,           // The table name
                    new String[]{COLUMN_ID},  // Columns to retrieve (we only need the id)
                    COLUMN_NAME + " = ?",     // The selection (where clause)
                    new String[]{theaterName},// The selection args (theater name)
                    null,                     // Group by
                    null,                     // Having
                    null                      // Order by
            );

            // Return true if the cursor contains at least one row (theater exists)
            return cursor != null && cursor.getCount() > 0;

        } finally {
            if (cursor != null) {
                cursor.close();  // Close the cursor to avoid memory leaks
            }
        }
    }



























    /**
     * Delete a theater from the database.
     *
     * @param id ID of the theater to be deleted
     * @return True if the deletion was successful, false otherwise
     */
    public boolean deleteTheater(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_THEATERS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();  // Close the database connection after deletion

        return rowsDeleted > 0;  // If at least one row was deleted, the operation was successful
    }

    /**
     * Update an existing theater's name in the database.
     *
     * @param id   ID of the theater to update
     * @param name New name for the theater
     * @return True if the update was successful, false otherwise
     */
    public boolean updateTheater(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);

        int rowsUpdated = db.update(TABLE_THEATERS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();  // Close the database connection after updating

        return rowsUpdated > 0;  // If at least one row was updated, the operation was successful
    }


    //
    public boolean updateLocation(int id, String name, @Nullable String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        if (location != null) {
            values.put(COLUMN_LOCATION, location);
        }
        int rowsUpdated = db.update(TABLE_THEATERS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return rowsUpdated > 0;
    }


    /**
     * Close the database connection safely.
     */
    @Override
    public void close() {
        super.close();  // Ensures the DB connection is properly closed
    }
}
