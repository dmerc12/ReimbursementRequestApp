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
            <nav className='nav-bar'>
                <div className='nav-left'>
                    <Link className="nav-item" href='/'>Home</Link>
                    <Link className="nav-item" href='/manage-requests'>Manage Requests</Link>
                    <Link className="nav-item" href='/manage-categories'>Manage Categories</Link>
                    <Link className="nav-item" href='manage-information'>Manage Information</Link>
                </div>
                <div className="nav-right">
                    {isLoggedIn ? (
                        <>
                            <button className="nav-item" onClick={handleLogout}>Logout</button>
                        </>
                    ) : (
                        <>
                            <Link className="nav-item" href='/login'>Login</Link>
                            <Link className="nav-item" href='/register'>Register</Link>
                        </>
                    )}
                </div>
            </nav>
        </>
    )
}

export default Navbar
