import Link from "next/link"
import { signIn, signOut } from 'next-auth/react'

 
const Navbar = () => {
    return (
        <>
            <nav>
                <div>
                    <Link href='/'>Home</Link>
                    <Link href='/manage-requests'>Manage Requests</Link>
                    <Link href='manage-information'>Manage Information</Link>
                </div>
                <div>
                    <button onClick={() => signIn()}>Login</button>
                    <button onClick={() => signOut()}>Logout</button>
                    <Link href='/register'>Register</Link>
                </div>
            </nav>
        </>
    )
}

export default Navbar
