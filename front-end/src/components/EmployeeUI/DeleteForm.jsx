import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { Modal } from '../Modal';
import { useState } from 'react';
import { useFetch } from '../../hooks';
import { useNavigate } from 'react-router-dom';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

export const DeleteForm = ({ toastRef }) => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const { fetchData } = useFetch();

    const sessionId = Cookies.get('sessionId');

    const navigate = useNavigate();

    const onSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);
        setFailedToFetch(false);
        try {
            const { responseStatus, data } = await fetchData('/api/delete/employee', 'DELETE', {sessionId: Number(sessionId)});

            if (responseStatus === 200) {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                setVisible(false);
                toastRef.current.addToast({ mode: 'success', message: 'Profile successfully deleted, goodbye!' });
            } else if (responseStatus === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end, please try again!");
            }
        } catch (error) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                toastRef.current.addToast({ mode: 'warn', message: error.message });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetch(true);
                setLoading(false);
            } else {
                setLoading(false);
                toastRef.current.addToast({ mode: 'error', message: error.message });
            }
        }
    };

    const goBack = () => {
        setFailedToFetch(false);
    };

    return (
        <>
            <div className="component">
                <button onClick={() => {setVisible(true); setFailedToFetch(false)}} className="action-btn" id="deleteInformationModal">Delete Profile</button>
            </div>

            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetch(false)}}>
                {loading ? (
                    <div className='loading-indicator'>
                        <FaSpinner className='spinner' />
                    </div>
                ) : failedToFetch ? (
                    <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={onSubmit}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
                ) : (
                    <form className="form" onSubmit={onSubmit}>
                        <h1>Confirm Deletion Below</h1>
                        <p>Any requests or subsequent categories you are associated with will also be deleted. Are you sure you?</p>

                        <button id="deleteInformationButton" className="form-btn-1" type="submit">Delete Profile</button>
                </form>
                )}
            </Modal>
        </>
    )
};

DeleteForm.propTypes = {
    toastRef: PropTypes.object.isRequired
};
