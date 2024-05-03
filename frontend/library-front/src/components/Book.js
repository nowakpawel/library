import React from "react";
import {Link} from 'react-router-dom';

const Book = (book) => {
    return (
        <Link to={`/library/${book.isbn}`} className="book_item">
            <div className="book_header">
                <img src="" alt=""></img>
            </div>
            <div className="book_details">
                <p className="book_title"><i className="bi bi-person-vcard"></i>{book.title.substring(0, 25)}</p>
            </div>
            <div className="book_body">
                <p><i className="bi bi-123">{book.isbn}</i></p>
                <p><i className="bi bi-pencil-fill"></i>{book.author}</p>
                <p></p>
            </div>
        </Link>
    )
}