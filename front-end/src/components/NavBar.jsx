import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { Link } from 'react-router-dom';
import { useNavigate } from '../hooks';

export const Navbar = ({ toastRef }) => {
    const navigate = useNavigate();

    const handleLogout = () => {
        Cookies.remove('sessionId');
        navigate('/login');
        toastRef.current.addToast({ mode: 'success', message: 'Goodbye!' });
    };

    const isLoggedIn = Cookies.get('sessionId');

    return (
        <>
            <nav className="nav-bar">
                <div className="nav-left">
                    <Link className="nav-item" to='/home'>Home</Link>
                    <Link className="nav-item" to='/manage-requests' id="manageRequestsTab">Manage Requests</Link>
                    <Link className="nav-item" to='/manage-categories' id="manageCategoriesTab">Manage Categories</Link>
                    <Link className="nav-item" to='/manage-information' id="manageInformationTab">Manage Information</Link>
                </div>
                <div className="nav-right">
                    {isLoggedIn ? (
                        <div>
                            <button id="logoutButton" className="nav-item" onClick={handleLogout}>Logout</button>
                        </div>
                    ) : (
                        <div>
                            <Link className="nav-item" to='/login' id="loginTab">Login</Link>
                            <Link className="nav-item" to='/register' id="registerTab">Register</Link>
                        </div>
                    )}
                </div>
            </nav>
        </>
    );
};

Navbar.propTypes = {
    toastRef: PropTypes.object.isRequired
};
