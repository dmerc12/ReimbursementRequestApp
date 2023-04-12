import { useEffect } from 'react'
import { useRouter } from 'next/router'

export default function ManageInformation({ user }) {
    const router = useRouter();

    useEffect(() => {
        const employeeId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('employeeId='));
        if (!employeeId) {
          router.push('/login');
        }
      }, [router]);

    return (
        <>
            <h1>Manage Employee Information Page</h1>
        </>
    )
}
