package com.example.popcorntest1.adpater2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popcorntest1.R;

public class ManageTheaterAdapter extends RecyclerView.Adapter<ManageTheaterAdapter.TheaterViewHolder> {

    private Context context;
    private Cursor cursor;
    private OnDeleteClickListener deleteClickListener;

    // Constructor for the adapter
    public ManageTheaterAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    // Interface for delete click listener
    public interface OnDeleteClickListener {
        void onDeleteClick(int id); // Pass the theater ID when delete is clicked
    }

    // Setter for delete click listener
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    @NonNull
    @Override
    public TheaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_theater, parent, false);
        return new TheaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheaterViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            // Get data from the cursor
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));

            // Set the data to the TextView
            holder.theaterNameTextView.setText(name);

            // Set up the delete button click listener
            holder.deleteButton.setOnClickListener(v -> {
                if (deleteClickListener != null) {
                    // Pass the ID of the item to the listener
                    deleteClickListener.onDeleteClick(id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount(); // Return the number of items in the cursor
    }

    // Method to swap the cursor when data changes
    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close(); // Close the previous cursor to avoid memory leaks
        }
        cursor = newCursor; // Update the cursor to the new one
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    // Optional: Getter for cursor (if needed outside the adapter)
    public Cursor getCursor() {
        return cursor;
    }

    static class TheaterViewHolder extends RecyclerView.ViewHolder {
        TextView theaterNameTextView;
        Button deleteButton;

        // ViewHolder for each item in the RecyclerView
        TheaterViewHolder(View itemView) {
            super(itemView);
            theaterNameTextView = itemView.findViewById(R.id.theater_name_text); // ID for TextView
            deleteButton = itemView.findViewById(R.id.delete_button); // ID for Delete Button
        }
    }
}
