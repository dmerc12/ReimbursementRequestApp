import { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import Link from "next/link";
import Cookies from 'js-cookie';

export const metadata = {
  title: "Managing Information",
  description: "Managing information"
};

export default function ManageInformation() {
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
          <h1>Manage Your Current Information</h1>
          <div className='nav-btn-container'>
            <Link className='nav-btn' href='/manage-information/update'>Update Current Information</Link>
            <Link className='nav-btn' href='/manage-information/change-password'>Change Current Password</Link>
            <Link className='nav-btn' href='/manage-information/delete'>Delete Your Information</Link>
          </div>
        </>
    )
}
