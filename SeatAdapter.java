package com.example.popcorntest1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popcorntest1.R;
import com.example.popcorntest1.models.Seat;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {

    private final List<Seat> seatList;
    private final Context context;
    private OnSeatSelectionListener seatSelectionListener;

    public SeatAdapter(List<Seat> seatList, Context context) {
        this.seatList = seatList;
        this.context = context;
    }

    // Interface for seat selection listener
    public interface OnSeatSelectionListener {
        void onSeatSelectionChanged();
    }

    public void setOnSeatSelectionListener(OnSeatSelectionListener listener) {
        this.seatSelectionListener = listener;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the seat item layout (item_seat.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);

        // Update the seat appearance based on its status
        if (seat.isBooked()) {
            holder.seatImageView.setImageResource(R.drawable.seat_booked); // Booked seat
            holder.seatImageView.setEnabled(false); // Disable interaction for booked seats
        } else if (seat.isSelected()) {
            holder.seatImageView.setImageResource(R.drawable.seat_selected); // Selected seat
        } else {
            holder.seatImageView.setImageResource(R.drawable.chair_image); // Available seat
        }

        // Adjust the ImageView size programmatically
        holder.seatImageView.getLayoutParams().width = 80; // Adjust width
        holder.seatImageView.getLayoutParams().height = 80; // Adjust height

        // Set the seat number
        holder.seatNumberTextView.setText(String.valueOf(seat.getSeatNumber()));

        // Handle seat selection on click
        holder.seatImageView.setOnClickListener(v -> {
            if (!seat.isBooked()) { // Only allow selection for available seats
                seat.setSelected(!seat.isSelected()); // Toggle selection status
                notifyItemChanged(position); // Notify the adapter to update the UI

                // Notify listener about the seat selection change
                if (seatSelectionListener != null) {
                    seatSelectionListener.onSeatSelectionChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    // ViewHolder class for binding seat items
    public static class SeatViewHolder extends RecyclerView.ViewHolder {
        ImageView seatImageView;
        TextView seatNumberTextView;

        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            seatImageView = itemView.findViewById(R.id.imageViewSeat); // Reference to the seat image view
            seatNumberTextView = itemView.findViewById(R.id.textViewSeatNumber); // Reference to the seat number TextView
        }
    }
}
