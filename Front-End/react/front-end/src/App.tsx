import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { Navbar } from './components/Navbar';
import { Home } from './pages/Home';
import { Login } from './pages/Login';
import { Register } from './pages/Register';

export default function App() {

  return (
    <>
      <BrowserRouter>
        <Navbar />

        <Routes>
          <Route index path='/' element={<Login/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/register' element={<Register/>}/>
          <Route path='/home' element={<Home/>}/>
        </Routes>
      </BrowserRouter>

      <ToastContainer position='top-center' newestOnTop autoClose={3000} hideProgressBar theme='dark' limit={1}/>
    </>
  )
}
