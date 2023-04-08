import { useSession } from 'next-auth/react'
import { useRouter } from 'next/router'

export default function ManageRequests() {
    const session = useSession();
    const router = useRouter();

    if (!session.data) {
        router.push('/login')
        return null
    }
    
    return (
        <>
            <h1>Manage Requests Page</h1>
        </>
    )
}