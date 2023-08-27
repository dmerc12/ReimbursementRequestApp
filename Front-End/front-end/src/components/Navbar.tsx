import Cookies from "js-cookie";
import { toast } from 'react-toastify';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

export const Navbar = () => {
    const navigate = useNavigate();

    const handleLogout = () => {
        Cookies.remove('sessionId');
        navigate('/login');
        toast.success("Goodbye!");
    }

    const isLoggedIn = Cookies.get('sessionId');

    return (
        <>
            <nav className="nav-bar">
                <div className="nav-left">
                    <Link className="nav-item" to='/home'>Home</Link>
                    <Link className="nav-item" to='/manage-requests'>Manage Requests</Link>
                    <Link className="nav-item" to='/manage-categories'>Manage Categories</Link>
                    <Link className="nav-item" to='/manage-information'>Manage Information</Link>
                </div>
                <div className="nav-right">
                    {isLoggedIn ? (
                        <>
                            <button className="nav-item" onClick={handleLogout}>Logout</button>
                        </>
                    ) : (
                        <>
                            <Link className="nav-item" to='/login'>Login</Link>
                            <Link className="nav-item" to='/register'>Register</Link>
                        </>
                    )}
                </div>
            </nav>
        </>
    );
}
