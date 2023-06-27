CREATE TABLE room (
  roomId UUID PRIMARY KEY,
  name VARCHAR(255),
  capacity INTEGER,
  resources VARCHAR(255),
  createdAt DATE
);

CREATE TABLE booking (
  bookingId UUID PRIMARY KEY,
  creneauHoraire VARCHAR(255),
  meetingType VARCHAR(50),
  roomId UUID,
  createdAt DATE,
  FOREIGN KEY (roomId) REFERENCES room (roomId)
 );