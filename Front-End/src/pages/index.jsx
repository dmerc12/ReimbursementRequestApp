import Link from 'next/link'
import { useEffect } from 'react'
import { useRouter } from 'next/router'

export default function Home({ user }) {
  const router = useRouter();

  useEffect(() => {
    const employeeId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('employeeId='));
    if (!employeeId) {
      router.push('/login');
    }
  }, [router]);

  return (
    <>
      <Link href='/manage-requests'>Manage Request Information</Link>
      <Link href='/manage-information'>Manage Employee Information</Link>
    </>
  )
}
