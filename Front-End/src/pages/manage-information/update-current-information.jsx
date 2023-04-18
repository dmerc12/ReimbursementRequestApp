'use client'

import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import UpdateEmployeeForm from '@/components/ui/employee/UpdateEmployeeForm';

export default function UpdateCurrentEmployeeInformation() {
  const [employee, setEmployee] = useState(null);
  
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
        console.log(data); 
        if (data.success) {
          setEmployee(data.success)
        } else if (data.error.message) {
          throw new Error(`${data.error.message}`)
        } else if (data.error) {
          throw new Error(`${data.error}`)
        } else {
          throw new Error("Something went extremely wrong, please try again!")
        }
      } catch (error) {
        console.error(error);
        toast.error(error.message); 
      }
    }
    fetchEmployee();
    }, [router]);

    return (
      <>
        <h1>Update Current Employee Information Page</h1>
        {employee && <UpdateEmployeeForm employee={employee}/>}
      </>
    )
}