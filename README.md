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


## Screenshots
![splashscreenpage](https://github.com/user-attachments/assets/c1265e80-d0fd-46de-a8dc-16533c3e8daf)
![loginpage](https://github.com/user-attachments/assets/883d055d-322b-4fd4-9c80-2f91e67e9dcf)
![signuppage](https://github.com/user-attachments/assets/618da5b2-3227-43ea-9888-fe5a5b05bb95)
![movielist3](https://github.com/user-attachments/assets/4406eaa2-5558-4385-8e6b-b30451f0b093)
![movielist2](https://github.com/user-attachments/assets/b14be346-4f25-4803-8a40-8ef116e464d9)
![Movielist1](https://github.com/user-attachments/assets/b05bd606-eef8-4edc-b78a-578b3fd705b8)
![showtimes](https://github.com/user-attachments/assets/88a98579-edea-439f-a9e5-b2d658d38300)
![theaterlisting1](https://github.com/user-attachments/assets/6fcf4b92-16be-4c1a-b9e2-b215410619e5)
![seatSelection](https://github.com/user-attachments/assets/ec9aab14-656d-4112-a57c-c60c3e0fa2c8)
![payment](https://github.com/user-attachments/assets/46ba3e5a-8019-47d0-b16e-d3f5c7cbe440)
![proceedpayment](https://github.com/user-attachments/assets/17639eb0-14f0-43e1-8685-e1003b2bdca3)
![confirmticket](https://github.com/user-attachments/assets/d04791ea-dcf8-474f-8efa-c06009fc9fab)


## Future Enhancements

- Add more payment gateways.
- Enable push notifications for booking confirmations and reminders.
- Integrate user reviews and ratings for movies.
- Expand language support.

---

For any queries or contributions, feel free to raise an issue or submit a pull request!

