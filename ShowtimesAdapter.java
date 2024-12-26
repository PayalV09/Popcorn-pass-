package com.example.popcorntest1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popcorntest1.R;

import java.util.List;

public class ShowtimesAdapter extends RecyclerView.Adapter<ShowtimesAdapter.ViewHolder> {

    private final List<String> showtimes;
    private final OnShowtimeClickListener onShowtimeClickListener;

    // Interface for click handling
    public interface OnShowtimeClickListener {
        void onShowtimeClick(String showtime);
    }

    public ShowtimesAdapter(List<String> showtimes, OnShowtimeClickListener listener) {
        this.showtimes = showtimes;
        this.onShowtimeClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_showtime, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String showtime = showtimes.get(position);
        holder.showtimeTextView.setText(showtime);

        // Set click listener on the button
        holder.bookButton.setOnClickListener(v -> onShowtimeClickListener.onShowtimeClick(showtime));
    }

    @Override
    public int getItemCount() {
        return showtimes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView showtimeTextView;
        Button bookButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            showtimeTextView = itemView.findViewById(R.id.showtimeTextView);
            bookButton = itemView.findViewById(R.id.btnBook);
        }
    }
}
