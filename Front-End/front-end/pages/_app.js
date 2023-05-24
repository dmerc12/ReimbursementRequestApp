import '@/styles/globals.css'
import Navbar from '@/components/Navbar'
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import React from 'react';

export default function App({ Component, pageProps }) {
  return (
    <>
      <div>
        <ToastContainer position='top-center' newestOnTop autoClose={3000} hideProgressBar theme='dark' limit={1}/>
        <Navbar />
        <Component {...pageProps} />
      </div>
    </>
  )
}