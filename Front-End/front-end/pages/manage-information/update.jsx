'use client'

import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import UpdateEmployeeForm from '@/components/ui/employee/UpdateForm';

export const metadata = {
  title: "Updating",
  description: "Updating information"
};

export default function UpdateCurrentEmployeeInformation() {
  const [employee, setEmployee] = useState(null);
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

    const fetchEmployee = async () => {
      try {
        const response = await fetch('/api/employee/handleGet', {
          method: 'PATCH',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({'sessionId': sessionIdCookie})
        });

        const data = await response.json();
        if (data.success) {
          setEmployee(data.success)
          setSessionId(sessionId)
        } else if (data.error.message) {
          throw new Error(`${data.error.message}`)
        } else if (data.error) {
          throw new Error(`${data.error}`)
        } else {
          throw new Error("Something went extremely wrong, please try again!")
        }
      } catch (error) {
        if (error.message === "Session has expired, please log in!") {
          Cookies.remove('sessionId');
          router.push('/login');
          toast.warn(error.message, {
            toastId: "customId"
          });
        } else {
          toast.error(error.message, {
            toastId: "customId"
          }); 
        }
      }
    }
    fetchEmployee();
    }, [router]);

    return (
      <>
        <h1>Update Your Current Information Below</h1>
        {employee && <UpdateEmployeeForm employee={employee} sessionId={sessionId}/>}
      </>
    )
}