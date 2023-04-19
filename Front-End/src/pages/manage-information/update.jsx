'use client'

import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import Cookies from 'js-cookie'
import UpdateEmployeeForm from '@/components/ui/employee/UpdateForm';

export default function UpdateCurrentEmployeeInformation() {
  const [employee, setEmployee] = useState(null);
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

    const fetchEmployee = async () => {
      try {
        const response = await fetch('/api/employee/handleGetCurrentInfo', {
          method: 'PATCH',
          headers: {'Content-Type': 'application/json'},
          body: JSON.stringify({'sessionId': sessionId})
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
        <h1>Update Current Employee Information Page</h1>
        {employee && <UpdateEmployeeForm employee={employee} sessionId={sessionId}/>}
      </>
    )
}