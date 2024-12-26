package com.example.popcorntest1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popcorntest1.R;
import com.example.popcorntest1.models.Theater;

import java.util.List;

public class TheaterAdapter extends RecyclerView.Adapter<TheaterAdapter.TheaterViewHolder> {

    private List<Theater> theaterList;
    private OnTheaterClickListener listener;

    public TheaterAdapter(List<Theater> theaterList, OnTheaterClickListener listener) {
        this.theaterList = theaterList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TheaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theater, parent, false);
        return new TheaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheaterViewHolder holder, int position) {
        Theater theater = theaterList.get(position);
        holder.theaterName.setText(theater.getName());
        holder.theaterLocation.setText(theater.getLocation());
        holder.theaterDetails.setText(theater.getDetails());

        // Set click listener
        holder.itemView.setOnClickListener(v -> listener.onTheaterClick(theater));
    }

    @Override
    public int getItemCount() {
        return theaterList.size();
    }

    public static class TheaterViewHolder extends RecyclerView.ViewHolder {
        TextView theaterName, theaterLocation, theaterDetails;

        public TheaterViewHolder(@NonNull View itemView) {
            super(itemView);
            theaterName = itemView.findViewById(R.id.theaterName);
            theaterLocation = itemView.findViewById(R.id.theaterLocation);
            theaterDetails = itemView.findViewById(R.id.theaterDetails);
        }
    }

    public interface OnTheaterClickListener {
        void onTheaterClick(Theater theater);
    }
}
