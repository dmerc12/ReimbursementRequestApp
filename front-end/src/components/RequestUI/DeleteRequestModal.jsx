import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { Modal } from '../Modal';
import { useState, useEffect } from 'react';
import { useFetch } from '../../hooks';
import { useNavigate } from 'react-router-dom';
import { FiTrash2 } from 'react-icons/fi';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

export const DeleteRequest = ({ toastRef, request, onUpdate}) => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetchSubmission, setFailedToFetchSubmission] = useState(false);

    const sessionId = Cookies.get('sessionId');

    const { fetchData } = useFetch();

    const navigate = useNavigate();

    const onSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);
        setFailedToFetchSubmission(false);
        try {
            const { responseStatus, data } = await fetchData(`/api/delete/request/${request.requestId}/${sessionId}`, 'DELETE');

            if (responseStatus === 200) {
                setLoading(false);
                setVisible(false);
                onUpdate();
                toastRef.current.addToast({ mode: 'success', message: 'Request Successfully Deleted!' });
            } else if (responseStatus == 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end, please try again!");
            }
        } catch (error) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                toastRef.current.addToast({ mode: 'warning', message: error.message });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetchSubmission(true);
                setLoading(false);
            } else {
                setLoading(false);
                toastRef.current.addToast({ mode: 'error', message: error.message });
            }
        } 
    };

    const goBack = () => {
        setFailedToFetchSubmission(false);
    };

    useEffect(() => {
        setRequestId(props.request.requestId);
    }, []);

    return (
        <>
            <FiTrash2 id="deleteRequestModal" onClick={() => {setVisible(true); setFailedToFetchSubmission(false)}} cursor='pointer' size={15} />
            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetchSubmission(false)}}>
                {loading ? (
                    <div className="loading-indicator">
                        <FaSpinner className="spinner"/>
                    </div>
                ) : failedToFetchSubmission ? (
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
                        <div className="form-field">
                            <label className="form-label" htmlFor="deleteRequestId">Request ID: </label>
                            <input className="form-input" disabled type="text" id="deleteRequestId" name="deleteRequestId" value={request.requestId}/>
                        </div>

                        <div className="form-field">
                            <label className="form-label">Are You Sure?</label>
                        </div>

                        <button className="form-btn-2" type="submit" id="deleteRequestButton">Delete Request</button>
                    </form>
                )}
            </Modal>
        </>
    )
};

DeleteRequest.propTypes = {
    toastRef: PropTypes.object.isRequired,
    request: PropTypes.object.isRequired,
    onUpdate: PropTypes.func.isRequired
};
