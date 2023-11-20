import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { CategoryList } from 'components';

export const ManageCategories = ({ toastRef }) => {
    document.title = "Manage Categories";
    
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
            <h1>Manage Categories</h1>
            <CategoryList toastRef={toastRef} />
        </>
    );
};

ManageCategories.propTypes = {
    toastRef: PropTypes.object.isRequired
};
