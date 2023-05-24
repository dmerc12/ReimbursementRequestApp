'use client'

import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import DeleteForm from '@/components/ui/employee/DeleteForm';
import Cookies from 'js-cookie';

export const metadata = {
  title: "Deleting",
  description: "Deleting information"
};

export default function DeleteEmployeeInformation() {
    const [sessionId, setSessionId] = useState(0);

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
            {sessionId && <DeleteForm sessionId={sessionId}/>}
        </>
    )
}