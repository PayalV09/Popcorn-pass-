# Popcorn-pass-
Movie Ticket Booking App
PopcornPass 🎬🍿
PopcornPass is an Android application that simplifies the process of booking movie tickets. With features like browsing movies, selecting theaters, choosing seats, and secure payments, it provides a seamless and enjoyable user experience. 

Features-
---------------User Authentication: Login and Sign-Up functionality.
---------------Movie Listing: Browse and filter movies by genre, language, and ratings.Choose from a list of movies.
---------------Theater Selection: Choose from a list of theater.
---------------Seat Booking: Interactive seat selection feature.
---------------Payment Options: Payment methods (e.g.cash on delivery).
---------------Admin Dashboard: Manage Movies theaters and Showtimes.
Tech Stack
---------------Frontend: Java, XML (Android UI).
---------------Backend: SQLite for local theater and movie data.
---------------Tools: Android Studio.
Project Structure -
---------------AndroidManifest.xml
---------------adapters - 1. AdminPagerAdapter 2. DateTimeAdapter  3.MovieAdapter 4.SeatAdapter 5.ShowtimesAdapter 6.TheaterAdapter
---------------adapter2 (Manage admin activities) - 1.ManageTheaterAdapter 2.MoviesAdapter 
---------------DatabaseHelper- 1.ManageShowtimeDatabaseHelper 2. MovieDatabaseHelper 3.TheaterDatabaseHelper
---------------Model Class- 1.ManageTheater 2.Movie 3.Seat  4. Showtime 5.Theater 
---------------Activities(java) - 1. AdminDashboardActivity 2. AdminLoginActivity 3. ConfirmationActivity  4.LoginActivity 5.MovieDetailsActivity 6.MovieListingActivity 7. PaymentSummaryActivity 
                                  8. SeatSelectionActivity  9. ShowtimesActivity  10. SignUpActivity 11.SplashActivity  12. TheaterListingActivity
                                  AdminActivity-( Manage_Movies_Activity,ManageShowtimesActivity ,ManageTheaterActivity)
---------------Drawable- 1. box_border.xml, 2.circle_gradient.xml,  3. rounded_corner.xml , 4.rounded_seat_background.xml 5. seat_available.xml, 6. seat_booked.xml, 7.seat_item_background.xml, 
                          8.seat_selected.xml,9. seat_selected.xml, 10.shape.xml , 11.spinner_bg.xml , 12. text_background.xml
---------------layout- activity_admin_dashboard.xml,actvity_admin_login.xml, activity_confirmation.xml, activty_login.xml,activty_sign_up.xml,activity_movie_details.xml,activty_movie_lisiting.xml,
                       activity_payment_summary.xml,activity_showtimes.xml,activty_splash.xml,activity_theater_listing.xml,dialog_add_movie,item_managetheater.xml,item_movie.xml,item_movie2.xml,item_seat.xml,
                       item_showtime.xml,item_theater.xml,movie_list_item.xml,seat_selection_activty.xml,tv_total_seats.xml
                       (admin xml)- activity_manage_movies.xml, activity_manage_showtimes.xml, activity_manage_theater.xml
--------------Values- strings.xml, styles.xml, colors.xml, ids.xml
--------------Themes- themes.xml , themes.xml(night)
example of structure in adnroid studio- 
PopcornPass/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/popcornpass/
│   │   │   │   ├── activities/
│   │   │   │   │   ├── SplashActivity.java
│   │   │   │   │   ├── LoginActivity.java
│   │   │   │   │   ├── SignUpActivity.java
│   │   │   │   │   ├── DashboardActivity.java
│   │   │   │   │   ├── MovieDetailsActivity.java
│   │   │   │   │   ├── TheaterSelectionActivity.java
│   │   │   │   │   ├── SeatSelectionActivity.java
│   │   │   │   │   ├── PaymentActivity.java
│   │   │   │   └── ManageTheaterActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   ├── values/
│   │   │   ├── AndroidManifest.xml
├── build/
├── gradle/
├── README.md
└── ...


Installation Guide
1.Clone the repository:
git clone https://github.com/yourusername/popcornpass.git
2.Open the project in Android Studio.
3.Sync the project with Gradle files.
4.Connect to Firebase for database integration.
5.Build and run the app on an emulator or a physical Android device.



How to Use
-Launch the app and sign up or log in.
-Browse through the movie listings.
-Select a movie to view details and available theaters.
-Choose a theater and select your preferred seats.
-Complete the booking by making a secure payment.


Screenshots -  UI/UX of App  









Future Enhancements
-Add more payment gateways.
-Enable push notifications for booking confirmations and reminders.
-Integrate user reviews and ratings for movies.
-Expand language support.
