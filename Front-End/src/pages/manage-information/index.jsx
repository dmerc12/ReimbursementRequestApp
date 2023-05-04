import { useEffect } from 'react';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import Link from "next/link"

export default function ManageInformation() {
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
          <h1>Manage Your Current Information</h1>
          <div className='nav-btn-container'>
            <Link className='nav-btn' href='/manage-information/update'>Update Current Information</Link>
            <Link className='nav-btn' href='/manage-information/change-password'>Change Current Password</Link>
            <Link className='nav-btn' href='/manage-information/delete'>Delete Your Information</Link>
          </div>
        </>
    )
}
