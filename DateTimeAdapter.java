package com.example.popcorntest1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateTimeAdapter extends RecyclerView.Adapter<DateTimeAdapter.DateTimeViewHolder> {

    private final List<String> dateTimeList; // List of dates in "yyyy-MM-dd" format
    private final Context context;
    private OnDateTimeSelectionListener dateTimeSelectionListener;

    // Interface for date-time selection listener
    public interface OnDateTimeSelectionListener {
        void onDateTimeSelected(String selectedDateTime);
    }

    public DateTimeAdapter(List<String> dateTimeList, Context context) {
        this.dateTimeList = dateTimeList;
        this.context = context;
    }

    @NonNull
    @Override
    public DateTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false); // Using a layout with two text views
        return new DateTimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateTimeViewHolder holder, int position) {
        String dateTime = dateTimeList.get(position);

        // Parse the date to get the day of the week
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat outputDayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        String dayOfWeek = "";

        try {
            Date date = inputFormat.parse(dateTime);
            if (date != null) {
                dayOfWeek = outputDayFormat.format(date); // Get the day of the week
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Set the date and day in the text views
        holder.dateTextView.setText(dateTime); // Show the date
        holder.dayTextView.setText(dayOfWeek); // Show the day of the week

        // Set click listener on each item
        holder.itemView.setOnClickListener(v -> {
            if (dateTimeSelectionListener != null) {
                dateTimeSelectionListener.onDateTimeSelected(dateTime);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dateTimeList.size();
    }

    // Setter for the listener
    public void setOnDateTimeSelectionListener(OnDateTimeSelectionListener listener) {
        this.dateTimeSelectionListener = listener;
    }

    public static class DateTimeViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView dayTextView;

        public DateTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(android.R.id.text1); // Date TextView
            dayTextView = itemView.findViewById(android.R.id.text2); // Day TextView
        }
    }
}
