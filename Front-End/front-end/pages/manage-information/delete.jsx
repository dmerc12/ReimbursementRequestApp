'use client'

import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import DeleteForm from '@/components/ui/employee/DeleteForm';

export const metadata = {
  title: "Deleting",
  description: "Deleting information"
};

export default function DeleteEmployeeInformation() {
    const [sessionId, setSessionId] = useState(0);

    const router = useRouter();

    useEffect(() => {
        const sessionId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('sessionId=')).split('=')[1];
        if (!sessionId) {
          router.push('/login');
          toast.info("Please login or register to gain access!", {
            toastId: "customId"
          })
        }
        setSessionId(sessionId)
      }, [router]);

    return (
        <>
            {sessionId && <DeleteForm sessionId={sessionId}/>}
        </>
    )
}