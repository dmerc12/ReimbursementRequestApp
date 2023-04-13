import '@/styles/globals.css'
import 'react-toastify/dist/ReactToastify.css';
import Navbar from '@/components/Navbar'
import { ToastContainer } from 'react-toastify';

export default function App({ Component, 
  pageProps: { ...pageProps },
 }) {
  return (
    <>
      <Navbar />
      <Component {...pageProps} />
      <ToastContainer />
    </>
  )
}