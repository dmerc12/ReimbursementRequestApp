import Link from "next/link"
import { useRouter } from 'next/router'
import Cookies from 'js-cookie'
import { toast } from 'react-toastify';
 
const Navbar = () => {
    const router = useRouter();

    const handleLogout = () => {
        Cookies.remove('sessionId');
        router.push('/login');
        toast.success("Goodbye!")
    };

    const isLoggedIn = Cookies.get('sessionId');

    return (
        <>
            <nav>
                <div>
                    <Link href='/'>Home</Link>
                    <Link href='/manage-requests'>Manage Requests</Link>
                    <Link href='/manage-categories'>Manage Categories</Link>
                    <Link href='manage-information'>Manage Information</Link>
                </div>
                <div>
                    {isLoggedIn ? (
                        <>
                            <button onClick={handleLogout}>Logout</button>
                        </>
                    ) : (
                        <>
                            <Link href='/login'>Login</Link>
                            <Link href='/register'>Register</Link>
                        </>
                    )}
                </div>
            </nav>
        </>
    )
}

export default Navbar
