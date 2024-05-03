import React from "react";
import Book from "./Book"

const BookList = (data) => {
    <main>
        {data?.length === 0 && <div>No Books</div>}

        <ul className="book__list">
            {data?.length > 0 && data.map(book => <Book book={book} key={book.isbn} />)}
        </ul>


    </main>
}