import Link from 'next/link'
import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';

export const metadata = {
  title: "Home",
  description: "Home"
};

export default function Home() {
  const [sessionId, setSessionId] = useState(null);
  
  const router = useRouter();

  useEffect(() => {
    const sessionIdCookie = Cookies.get('sessionId');
    if (!sessionIdCookie) {
        router.push('/login');
        toast.info("Please login or register to gain access!", {
            toastId: 'customId'
        })
    }
    setSessionId(sessionIdCookie)
}, [router]);


  return (
    <>
      <div className='nav-btn-container'>
      <Link className='nav-btn' href='/manage-requests'>Manage Request Information</Link>
        <Link className='nav-btn' href='/manage-categories'>Manage Categories</Link>
        <Link className='nav-btn' href='/manage-information'>Manage Employee Information</Link>
      </div>
    </>
  )
}
