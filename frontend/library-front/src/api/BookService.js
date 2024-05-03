import axios from "axios";

const API_URL = 'http://localhost:8080/library';

export async function saveBook(book) {
    return await axios.post(`${API_URL}/add/`, book);
}

export async function getAllBooks() {
    return await axios.get(`${API_URL}/`);
}

export async function getBookById(id) {
    return await axios.get(`${API_URL}/${id}`);
}

//todo: update 

export async function deleteBook(id) {
    return await axios.delete(`${API_URL}/delete/${id}`);
}
