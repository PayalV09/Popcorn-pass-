# PopcornPass - Online Movie Ticket Booking System

## Project Structure

```
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
```

### Key Components:

#### Adapters
- **Main Adapters:**
  - `AdminPagerAdapter`
  - `DateTimeAdapter`
  - `MovieAdapter`
  - `SeatAdapter`
  - `ShowtimesAdapter`
  - `TheaterAdapter`
- **Admin Management Adapters:**
  - `ManageTheaterAdapter`
  - `MoviesAdapter`

#### Database Helpers
- `ManageShowtimeDatabaseHelper`
- `MovieDatabaseHelper`
- `TheaterDatabaseHelper`

#### Model Classes
- `ManageTheater`
- `Movie`
- `Seat`
- `Showtime`
- `Theater`

#### Activities (Java)
- `AdminDashboardActivity`
- `AdminLoginActivity`
- `ConfirmationActivity`
- `LoginActivity`
- `MovieDetailsActivity`
- `MovieListingActivity`
- `PaymentSummaryActivity`
- `SeatSelectionActivity`
- `ShowtimesActivity`
- `SignUpActivity`
- `SplashActivity`
- `TheaterListingActivity`
- **Admin Management Activities:**
  - `Manage_Movies_Activity`
  - `ManageShowtimesActivity`
  - `ManageTheaterActivity`

#### UI Components
- **Drawable Files:** Backgrounds, seat status indicators, gradients, borders, and shape definitions.
- **Layouts:** XML files for each activity and UI component.
- **Values:** Strings, styles, colors, and themes.

## Installation Guide

1. Clone the repository:
   ```bash
   git clone https://github.com/PayalV09/Popcorn-pass-.git
   ```
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Connect to **Firebase** for database integration.
5. Build and run the app on an emulator or a physical Android device.

## How to Use

- **Launch** the app and sign up or log in.
- **Browse** through the movie listings.
- **Select** a movie to view details and available theaters.
- **Choose** a theater and select your preferred seats.
- **Complete** the booking by making a secure payment.



## Future Enhancements

- Add more payment gateways.
- Enable push notifications for booking confirmations and reminders.
- Integrate user reviews and ratings for movies.
- Expand language support.

---

For any queries or contributions, feel free to raise an issue or submit a pull request!

