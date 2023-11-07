import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { Modal } from 'components';
import { useState, useEffect } from 'react';
import { useFetch } from 'hooks';
import { useNavigate } from 'react-router-dom';
import { FiEdit } from 'react-icons/fi';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

export const UpdateRequest = ({ toastRef, request, onUpdate }) => {
    const sessionId = Cookies.get('sessionId');

    const [updateRequestForm, setUpdateRequestForm] = useState({
        sessionId: sessionId,
        requestId: request.requestId,
        categoryId: request.categoryId,
        comment: request.comment,
        amount: request.amount
    });
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetchCategories, setFailedToFetchCategories] = useState(false);
    const [failedToFetchSubmission, setFailedToFetchSubmission] = useState(false);
    const [categories, setCategories] = useState([]);

    const { fetchData } = useFetch();

    const navigate = useNavigate();

    const fetchCategories = async () => {
        setLoading(true);
        setFailedToFetchCategories(false);
        setFailedToFetchSubmission(false);
        try {
            const { responseStatus, data } = await fetchData(`/api/get/all/categories/${sessionId}`, 'GET');

            if (responseStatus === 200) {
                setCategories(data);
                setLoading(false);
            } else if (responseStatus === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end, please try again!');
            }
        } catch (error) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                toastRef.current.addToast({ mode: 'warning', message: error.message });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetchCategories(true);
                setLoading(false);
            } else {
                setLoading(false);
                toastRef.current.addToast({ mode: 'error', message: error.message });
            }
        }
    };

    const onSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);
        setFailedToFetchCategories(false);
        setFailedToFetchSubmission(false);
        try {
            const { responseStatus, data } = await fetchData('/api/update/request', 'PUT', updateRequestForm);

            if (responseStatus === 200) {
                setLoading(false);
                setVisible(false);
                onUpdate();
                toastRef.current.addToast({ mode: 'success', message: 'Request Successfully Updated!' });
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
        setFailedToFetchCategories(false);
        setFailedToFetchSubmission(false);
    };

    const onChange = (event) => {
        const { name, value } = event.target;
        setUpdateRequestForm((prevForm) => ({
            ...prevForm,
            [name]: value
        }));
    };

    useEffect(() => {
        fetchCategories();
    }, []);

    return (
        <>
            <FiEdit id="updateRequestModal" onClick={() => {setVisible(true); setFailedToFetchCategories(false); setFailedToFetchSubmission(false)}} cursor='pointer' size={15} />
            <Modal visible={visible} onClose={() => {setVisible(false), setFailedToFetchCategories(false), setFailedToFetchSubmission(false)}}>
                {loading ? (
                    <div className="loading-indicator">
                        <FaSpinner className="spinner"/>
                    </div>
                ) : failedToFetchCategories ? (
                    <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={fetchCategories}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
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
                            <label className="form-label" htmlFor="requestId">Request ID: </label>
                            <input className="form-input" disabled type="text" id="requestId" name="updateRequestId" value={updateRequestForm.requestId}/>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="categoryId">Category: </label>
                            <select className="form-input" name="categoryId" id="updateRequestCategoryDropDown" value={updateRequestForm.categoryId} onChange={onChange}>
                                {categories && categories.length > 0 && (
                                    categories.map(category => (
                                        <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                                    ))
                                )}
                            </select>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="comment">Comment: </label>
                            <input className="form-input" type="text"  id="comment" name="updateRequestComment" value={updateRequestForm.comment} onChange={onChange}/>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="amount">Amount: </label>
                            <input className="form-input" type="number" name="amount" id="updateRequestAmount" value={updateRequestForm.amount} onChange={onChange}/>
                        </div>

                        <button className="form-btn-1" type="submit" id="updateRequestButton">Update Request</button>
                    </form>
                )}
            </Modal>
        </>
    )
};

UpdateRequestModal.propTypes = {
    toastRef: PropTypes.object.isRequired,
    request: PropTypes.object.isRequired,
    onUpdate: PropTypes.func.isRequired
};
