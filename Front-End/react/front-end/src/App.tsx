import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Home } from './pages/Home';
import { Login } from './pages/Login';
import { Register } from './pages/Register';

export default function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index path='/' element={<Login/>}/>
          <Route index path='/login' element={<Login/>}/>
          <Route path='/register' element={<Home/>}/>
          <Route path='/home' element={<Home/>}/>
        </Routes>
      </BrowserRouter>
    </>
  )
}
