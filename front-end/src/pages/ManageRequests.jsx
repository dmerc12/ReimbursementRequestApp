import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { RequestList } from '../components';

export const ManageRequests = ({ toastRef }) => {
    document.title = "Manage Requests";
    
    const navigate = useNavigate();

    useEffect(() => {
        const sessionId = Cookies.get('sessionId');
        if (!sessionId) {
            navigate('/login');
            toastRef.current.addToast({ mode: 'warning', message: 'Please login or register to gain access!' });
        }
    }, []);

    return (
        <>
            <h1>Manage Requests Below!</h1>
            <RequestList toastRef={toastRef} />
        </>
    );
};

ManageRequests.propTypes = {
    toastRef: PropTypes.object.isRequired
};
