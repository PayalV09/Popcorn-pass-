package com.example.popcorntest1.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int id;
    private String title;
    private String genre;
    private String language;
    private String cast;
    private String crew;
    private double rating;
    private int imageResource;  // Drawable resource ID for the image

    // Constructor
    public Movie(int id, String title, String genre, String language, String cast, String crew, double rating, int imageResource) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.cast = cast;
        this.crew = crew;
        this.rating = rating;
        this.imageResource = imageResource;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getCast() {
        return cast;
    }

    public String getCrew() {
        return crew;
    }

    public double getRating() {
        return rating;
    }

    public int getImageResource() {
        return imageResource;
    }

    // Parcelable implementation
    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        genre = in.readString();
        language = in.readString();
        cast = in.readString();
        crew = in.readString();
        rating = in.readDouble();
        imageResource = in.readInt();  // Read the drawable resource ID
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeString(language);
        dest.writeString(cast);
        dest.writeString(crew);
        dest.writeDouble(rating);
        dest.writeInt(imageResource);  // Write the drawable resource ID
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelable.Creator for creating instances from Parcel
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
