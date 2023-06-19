CREATE TABLE annonce (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION,
    type VARCHAR(20) NOT NULL,
    created_at DATE
);
