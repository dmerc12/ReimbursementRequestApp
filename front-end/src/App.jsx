import './App.css'

import { useRef } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { ToastContainer, Navbar } from './components';
import { Home, Login, Register, ManageInformation, ManageCategories, ManageRequests } from './pages';

function App() {
  const toastRef = useRef();

  return (
    <>
      <Navbar toastRef={toastRef} />

      <BrowserRouter>
        <Routes>
          <Route index path='/' element={<Login toastRef={toastRef} />} />
          <Route path='/login' element={<Login toastRef={toastRef} />} />
          <Route path='/register' element={<Register toastRef={toastRef} />} />
          <Route path='/home' element={<Home toastRef={toastRef} />} />
          <Route path='/manage/information' element={<ManageInformation toastRef={toastRef} />} />
          <Route path='/manage/categories' element={<ManageCategories toastRef={toastRef} />} />
          <Route path='/manage/requests' element={<ManageRequests toastRef={toastRef} />} />
        </Routes>
      </BrowserRouter>

      <ToastContainer ref={toastRef} />
    </>
  )
}

export default App
