package com.example.popcorntest1;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.popcorntest1.Databasehelper.TheaterDatabaseHelper;

import java.util.ArrayList;

public class ManageTheaterActivity extends AppCompatActivity {

    private EditText theaterNameEditText;
    private Button addButton;
    private ListView theaterListView;
    private TheaterDatabaseHelper databaseHelper;
    private ArrayList<String> theaterList;
    private ArrayAdapter<String> theaterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_theater);

        // Initialize UI elements
        theaterNameEditText = findViewById(R.id.theater_name_edittext);
        addButton = findViewById(R.id.add_theater_button);
        theaterListView = findViewById(R.id.theater_listview);

        // Initialize the database helper
        databaseHelper = new TheaterDatabaseHelper(this);

        // Initialize the theater list
        theaterList = new ArrayList<>();

        // Set up the adapter for ListView
        theaterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theaterList);
        theaterListView.setAdapter(theaterAdapter);

        // Set listener for Add Button to add a new theater
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String theaterName = theaterNameEditText.getText().toString().trim();

                if (!theaterName.isEmpty()) {
                    // Check if the theater already exists
                    if (databaseHelper.doesTheaterExist(theaterName)) {
                        Toast.makeText(ManageTheaterActivity.this, "Theater already exists!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Add the theater to the database
                        if (databaseHelper.addLocation(theaterName, "Default Location")) { // Add default location
                            Toast.makeText(ManageTheaterActivity.this, "Theater added successfully!", Toast.LENGTH_SHORT).show();
                            theaterNameEditText.setText("");  // Clear the input field
                            loadTheaters();  // Reload the list
                        } else {
                            Toast.makeText(ManageTheaterActivity.this, "Failed to add theater!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(ManageTheaterActivity.this, "Please enter a theater name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set listener for ListView item click to delete the selected theater
        theaterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTheater = theaterList.get(position);
                // Delete the selected theater
                int theaterId = getTheaterId(selectedTheater);  // Get the theater ID
                if (theaterId != -1 && databaseHelper.deleteTheater(theaterId)) {
                    Toast.makeText(ManageTheaterActivity.this, "Theater deleted successfully!", Toast.LENGTH_SHORT).show();
                    loadTheaters();  // Reload the list
                } else {
                    Toast.makeText(ManageTheaterActivity.this, "Failed to delete theater!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Load theaters from the database
        loadTheaters();
    }

    // Method to load all theaters from the database and update the ListView
    private void loadTheaters() {
        theaterList.clear();  // Clear the existing list
        Cursor cursor = databaseHelper.getAllTheaters();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Ensure that the "name" column exists
                int columnIndex = cursor.getColumnIndex("name");
                if (columnIndex != -1) {  // Validate the column exists
                    String theaterName = cursor.getString(columnIndex);
                    theaterList.add(theaterName);  // Add the theater to the list
                } else {
                    Log.e("ManageTheatersActivity", "Column 'name' not found in cursor.");
                }
            } while (cursor.moveToNext());
            cursor.close();  // Close the cursor
        }
        theaterAdapter.notifyDataSetChanged();  // Notify the adapter that the data has changed
    }

    // Method to get the ID of a theater from the database
    private int getTheaterId(String theaterName) {
        Cursor cursor = databaseHelper.getAllTheaters();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Check if both "name" and "id" columns exist
                int nameColumnIndex = cursor.getColumnIndex("name");
                int idColumnIndex = cursor.getColumnIndex("id");

                if (nameColumnIndex != -1 && idColumnIndex != -1) {  // Validate columns
                    String name = cursor.getString(nameColumnIndex);
                    if (name.equals(theaterName)) {
                        return cursor.getInt(idColumnIndex);  // Return the ID if the name matches
                    }
                } else {
                    Log.e("ManageTheatersActivity", "Column 'name' or 'id' not found in cursor.");
                }
            } while (cursor.moveToNext());
            cursor.close();  // Close the cursor
        }
        return -1;  // Return -1 if the theater is not found
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();  // Close the database connection when the activity is destroyed
    }
}
