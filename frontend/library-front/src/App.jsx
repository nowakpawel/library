import { useEffect, useState } from 'react'
import { getAllBooks } from './api/BookService'
import './App.css'


function App() {
  const [data, setData] = useState({})

  const getBooks = async () => {
    try {
        const {data} = await getAllBooks();
        setData(data);
        console.log(data);
    } catch(error) {
      console.log(error);
    }
  }

  useEffect(() => {
    getBooks();
  }, []);

  return (
    <>
      <div>
        <h1>Hello World</h1>
      </div>
    </>
  )
}

export default App
