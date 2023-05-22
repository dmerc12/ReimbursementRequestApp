import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';

export const metadata = {
  title: "Managing Requests",
  description: "Managing requests"
};

export default function ManageRequest() {
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
            <h1>Manage Request Information Page</h1>
        </>
    )
}
