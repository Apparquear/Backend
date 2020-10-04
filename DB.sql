DROP DATABASE apparquear;
CREATE DATABASE apparquear;
USE apparquear;
CREATE TABLE user (
  user_ID    INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_name  TEXT NOT NULL,
  user_password TEXT NOT NULL,
  user_email TEXT NOT NULL,
  user_age INTEGER NOT NULL
);
CREATE TABLE profile (
    profile_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_ID INTEGER NOT NULL ,
    creation_Date DATETIME NOT NULL ,
    reservation_canceled INTEGER NOT NULL ,
    reservation_done INTEGER NOT NULL,
    FOREIGN KEY(user_ID) REFERENCES user(user_ID)
);

CREATE TABLE payment_info(
    paymet_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_ID INTEGER NOT NULL,
    expiration_date DATETIME NOT NULL ,
    securityCode int NOT NULL,
    FOREIGN KEY (user_ID) REFERENCES user(user_ID)
);

CREATE TABLE parking(
    parking_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    user_ID INTEGER NOT NULL,
    parking_name TEXT NOT NULL ,
    opening_time TIME NOT NULL,
    closing_time TIME NOT NULL ,
    total_spaces INTEGER NOT NULL,
    car_spaces INTEGER NOT NULL,
    bike_spaces INTEGER NOT NULL ,
    motorcycle_spaces INTEGER NOT NULL ,
    total_spaces_available INTEGER NOT NULL,
    car_spaces_available INTEGER NOT NULL,
    bike_spaces_available INTEGER NOT NULL ,
    motorcycle_spaces_available INTEGER NOT NULL ,
    bike_cost_minute REAL NOT NULL ,
    car_cost_minute REAL NOT NULL ,
    motorcycle_cost_minute REAL NOT NULL,
    score DOUBLE,
    FOREIGN KEY (user_ID) REFERENCES user(user_ID)
);

CREATE TABLE  qualification(
  qualification_ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  user_ID INTEGER NOT NULL,
  parking_ID INTEGER NOT NULL,
  score REAL NOT NULL,
  description TEXT,
  FOREIGN KEY (user_ID) REFERENCES user(user_ID),
  FOREIGN KEY (parking_ID) REFERENCES parking(parking_ID)
);

CREATE TABLE reservation(
    reservation_ID INTEGER PRIMARY KEY ,
    user_ID INTEGER NOT NULL ,
    parking_ID INTEGER NOT NULL ,
    reservation_time DATETIME NOT NULL ,
    reservation_duration real,
    vehicle_type TEXT NOT NULL,
    FOREIGN KEY (user_ID) REFERENCES user(user_ID),
    FOREIGN KEY (parking_ID) REFERENCES parking(parking_ID)
);

CREATE TABLE bill (
    bill_ID INTEGER PRIMARY KEY,
    user_ID INTEGER NOT NULL,
    parking_ID INTEGER NOT NULL,
    reservation_ID INTEGER,
    entry_time DATETIME NOT NULL ,
    exit_time DATETIME NOT NULL ,
    cost DOUBLE NOT NULL,
    FOREIGN KEY (user_ID) REFERENCES user(user_ID),
    FOREIGN KEY (parking_ID) REFERENCES parking(parking_ID)
);