import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { UpdateForm, ChangePasswordForm, DeleteForm } from 'components';

export const ManageInformation = ({ toastRef }) => {
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
            <h1>Manage Information Below!</h1>
            <div>
                <UpdateForm toastRef={toastRef} />
                <ChangePasswordForm toastRef={toastRef} />
                <DeleteForm toastRef={toastRef} />
            </div>
        </>
    );
};

ManageInformation.propTypes = {
    toastRef: PropTypes.object.isRequired
};
