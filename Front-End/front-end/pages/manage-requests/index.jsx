import { useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export const metadata = {
  title: "Managing Requests",
  description: "Managing requests"
};

export default function ManageRequest() {
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
            <h1>Manage Request Information Page</h1>
        </>
    )
}
