import { useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export default function ManageInformation() {
    const router = useRouter();

    useEffect(() => {
        const sessionId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('sessionId='));
        if (!sessionId) {
          router.push('/login');
          toast.info("Please login or register to gain access!")
        }
      }, [router]);

    return (
        <>
            <h1>Manage Category Information Page</h1>
        </>
    )
}