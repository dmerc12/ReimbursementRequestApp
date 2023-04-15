import Link from 'next/link'
import { useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export default function Home() {
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
      <Link href='/manage-requests'>Manage Request Information</Link>
      <Link href='/manage-information'>Manage Employee Information</Link>
    </>
  )
}
