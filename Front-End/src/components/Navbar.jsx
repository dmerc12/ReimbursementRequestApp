import Link from "next/link"
import { signIn, signOut, useSession } from 'next-auth/react'
import { useRouter } from 'next/router'

 
const Navbar = () => {
    const session = useSession()
    const router = useRouter();

    const handleLogout = async () => {
        signOut({ callbackUrl: '/login' });
        router.push('/login');
    };

    return (
        <>
            <nav>
                <div>
                    <Link href='/'>Home</Link>
                    <Link href='/manage-requests'>Manage Requests</Link>
                    <Link href='manage-information'>Manage Information</Link>
                </div>
                <div>
                    {session.data ? (
                        <>
                            <button onClick={handleLogout}>Logout</button>
                        </>
                    ) : (
                        <>
                            <button onClick={() => signIn()}>Login</button>
                            <Link href='/register'>Register</Link>
                        </>
                    )}
                </div>
            </nav>
        </>
    )
}

export default Navbar
