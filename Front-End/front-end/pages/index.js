import Link from 'next/link'
import { useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export const metadata = {
  title: "Home",
  description: "Home"
};

export default function Home() {
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
      <div className='nav-btn-container'>
        <Link className='nav-btn' href='/manage-categories'>Manage Categories</Link>
        <Link className='nav-btn' href='/manage-requests'>Manage Request Information</Link>
        <Link className='nav-btn' href='/manage-information'>Manage Employee Information</Link>
      </div>
    </>
  )
}
