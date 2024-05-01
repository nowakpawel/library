CREATE TABLE IF NOT EXISTS Book (
    isbn text NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    CONSTRAINT "books_pkey" PRIMARY KEY(isbn)
);