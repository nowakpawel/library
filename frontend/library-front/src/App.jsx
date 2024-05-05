import { useEffect, useRef, useState } from 'react'
import { getAllBooks } from './api/BookService'
import './App.css'


function App() {
  const [data, setData] = useState({})

  const getData = async () => {
    try {
      const data = await getAllBooks();
      setData(data.data)
      console.log(data.data);
    } catch(error) {
      console.log(error);
    }
  }

  const hasLoadedBefore = useRef(true);

  useEffect(() => {
    if(hasLoadedBefore.current) {
      getData();
      hasLoadedBefore.current = false;
    }
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
