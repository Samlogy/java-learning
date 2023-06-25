CREATE TABLE  meeting (
  id UUID PRIMARY KEY,
  creneau_horaire VARCHAR(255),
  meeting_type VARCHAR(50),
  room_id UUID,
  created_at DATE
);

CREATE TABLE room (
  id UUID PRIMARY KEY,
  name VARCHAR(255),
  capacity INTEGER,
  resources VARCHAR(50),
  created_at DATE
);

ALTER TABLE meeting
ADD CONSTRAINT fk_room_id
FOREIGN KEY (room_id) REFERENCES room (id);