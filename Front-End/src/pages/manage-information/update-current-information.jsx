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
            {/* {employee && <UpdateEmployeeForm />} */}
        </>
    )
}

// export async function getServerSideProps() {
//   const response = await fetch('api/employee/handleGet')

//   const data = await response.json()

//   return {
//     props: {
//       employee: firstName, lastName, email, phoneNumber, address
//     }
//   }
// }