DROP DATABASE apparquear;
CREATE DATABASE apparquear;
USE apparquear;

CREATE TABLE user (
	userID INTEGER PRIMARY KEY AUTO_INCREMENT,
	name TEXT NOT NULL,
	password TEXT NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	age INTEGER NOT NULL
);

CREATE TABLE profile (
	profileID INTEGER PRIMARY KEY AUTO_INCREMENT,
	userID INTEGER NOT NULL ,
	creation_Date DATETIME NOT NULL ,
	reservation_canceled INTEGER NOT NULL ,
	reservation_done INTEGER NOT NULL,
	FOREIGN KEY(userID) REFERENCES user(userID)
);

CREATE TABLE payment_info(
	paymentID INTEGER PRIMARY KEY AUTO_INCREMENT,
	userID INTEGER NOT NULL,
	expiration_date DATETIME NOT NULL ,
	securityCode int NOT NULL,
	FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE parking(
	parkingID INTEGER PRIMARY KEY AUTO_INCREMENT,
	userID INTEGER NOT NULL,
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
	FOREIGN KEY (userID) REFERENCES user(userID)
);

CREATE TABLE location(
	locationID INTEGER PRIMARY KEY AUTO_INCREMENT,
    parkingID INTEGER NOT NULL,
	latitude DOUBLE NOT NULL,
	longitude DOUBLE NOT NULL,
	FOREIGN KEY (parkingID) REFERENCES parking(parkingID)
);

CREATE TABLE  qualification(
	qualificationID INTEGER PRIMARY KEY AUTO_INCREMENT,
	userID INTEGER NOT NULL,
	parkingID INTEGER NOT NULL,
	score REAL NOT NULL,
	description TEXT,
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (parkingID) REFERENCES parking(parkingID)
);

CREATE TABLE reservation(
	reservationID INTEGER PRIMARY KEY ,
	userID INTEGER NOT NULL ,
	parkingID INTEGER NOT NULL ,
	reservation_time DATETIME NOT NULL ,
	reservation_duration real,
	vehicle_type TEXT NOT NULL,
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (parkingID) REFERENCES parking(parkingID)
);

CREATE TABLE bill (
	billID INTEGER PRIMARY KEY,
	userID INTEGER NOT NULL,
	parkingID INTEGER NOT NULL,
	reservationID INTEGER,
	entry_time DATETIME NOT NULL ,
	exit_time DATETIME NOT NULL ,
	cost DOUBLE NOT NULL,
	FOREIGN KEY (userID) REFERENCES user(userID),
	FOREIGN KEY (parkingID) REFERENCES parking(parkingID)
);

CREATE TABLE token(
	tokenID INTEGER PRIMARY KEY AUTO_INCREMENT,
	userID INTEGER NOT NULL,
	token TEXT NOT NULL,
	valid BOOLEAN NOT NULL,
	FOREIGN KEY (userID) REFERENCES user(userID)
);

