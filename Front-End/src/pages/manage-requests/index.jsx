import { useEffect } from 'react'
import { useRouter } from 'next/router'

export default function ManageRequest({ user }) {
    const router = useRouter();

    useEffect(() => {
        const employeeId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('employeeId='));
        if (!employeeId) {
          router.push('/login');
        }
      }, [router]);

    return (
        <>
            <h1>Manage Request Information Page</h1>
        </>
    )
}
