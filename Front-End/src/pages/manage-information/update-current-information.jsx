import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import UpdateEmployeeForm from '@/components/ui/employee/UpdateEmployeeForm';

export default function UpdateCurrentEmployeeInformation({ employee }) {
  
    const router = useRouter();

    useEffect(() => {
        const sessionId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('sessionId='));
        if (!sessionId) {
          router.push('/login');
          toast.info("Please login or register to gain access!", {
            toastId: "customId"
          })
        }
      }, [router]);

    return (
        <>
            <h1>Update Current Employee Information Page</h1>
            {employee && <UpdateEmployeeForm employee={employee}/>}
        </>
    )
}

export async function getServerSideProps(context) {
  const sessionId = context.req.cookies.sessionId;
  try {
    const response = await fetch(`http://localhost:8080/get/employee/${sessionId}`)
    const data = await response.json()
    if (response.status === 200) {
      return {props: { employee: data}};
    } else if (response.status === 400) {
      return {props: { error: data}};
    } else {
      toast.error("Cannot connect to the back end, please try again!")
      return {props: { error: "Cannot connect to the back end, please try again!"}};
    }
  } catch (error) {
    toast.error(error.message)
    return { props: {'error': error.message}}
  }
}