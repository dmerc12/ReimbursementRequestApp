import { useEffect } from 'react'
import { useRouter } from 'next/router'
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
          <Link href='/manage-information/update-current-information'>Update Current Information</Link>
        </>
    )
}
