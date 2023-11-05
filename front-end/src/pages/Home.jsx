import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';

export const Home = ({ toastRef }) => {
    const navigate = useNavigate();

    useEffect(() => {
        const sessionIdCookie = Cookies.get('sessionId');
        if (!sessionIdCookie) {
            navigate('/login');
            toastRef.current.addToast({ mode: 'warning', message: 'Please login or register to gain access!' });
        }
    }, []);

    return (
        <>
            <h1>Welcome Home!</h1>
            <div className="action-btn-container">
                <Link id="manageRequestsButton" className="home-nav" to='/manage-requests'>Manage Request Information</Link>
                <Link id="manageCategoriesButton" className="home-nav" to='/manage-categories'>Manage Categories</Link>
                <Link id="manageInformationButton" className="home-nav" to='/manage-information'>Manage Information</Link>
            </div>
        </>
    );
};

Home.propTypes = {
    toastRef: PropTypes.object.isRequired
};
