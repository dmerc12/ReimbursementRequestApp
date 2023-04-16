import '@/styles/globals.css'
import 'react-toastify/dist/ReactToastify.css';
import Navbar from '@/components/Navbar'
import { ToastContainer } from 'react-toastify';

export default function App({ Component, pageProps }) {
  return (
    <>
      <ToastContainer position='top-center' newestOnTop autoClose={3000} hideProgressBar theme='dark'/>
      <Navbar />
      <Component {...pageProps} />
    </>
  )
}