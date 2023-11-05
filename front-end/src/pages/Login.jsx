import PropTypes from 'prop-types';

import { LoginForm } from 'components';
 
export const Login = ({ toastRef }) => {
    return (
        <>
            <LoginForm toastRef={toastRef} />
        </>
    );
};

Login.propTypes = {
    toastRef: PropTypes.object
};
