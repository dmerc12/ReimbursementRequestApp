import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { useEffect, useState } from 'react';
import { useFetch } from 'hooks';
import { useNavigate } from 'react-router-dom';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';
import { AddRequest, UpdateRequest, DeleteRequest } from 'components';

export const RequestList = ({ toastRef }) => {
    const [categories, setCategories] = useState([]);
    const [requests, setRequests] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [failedToFetchCategories, setFailedToFetchCategories] = useState(false);
    const [failedToFetchRequests, setFailedToFetchRequests] = useState(false);

    const { fetchData } = useFetch();

    const sessionId = Cookies.get('sessionId');

    const navigate = useNavigate();

    let requestRows = [];

    const fetchCategories = async () => {
        setIsLoading(true);
        setFailedToFetchCategories(false);
        try {
            const { responseStatus, data } = await fetchData(`/api/get/all/categories/${sessionId}`, 'GET');

            if (responseStatus === 200) {
                setCategories(data);
                setIsLoading(false);
            } else if (responseStatus === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end, please try again!');
            }
        } catch (error) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setIsLoading(false);
                toastRef.current.addToast({ mode: 'warning', message: error.message});
            } else if (error.message === "No categories found, please try again!") {
                setIsLoading(false);
                navigate('/home');
                toastRef.current.addToast({ mode: 'info', message: "Oops! It looks like you haven't created any categories yet. Categories are used to organize your requests. Please create a category first before adding a new request. You can do this by clicking on 'Manage Categories' in the navigation menu" });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetchCategories(true);
                setIsLoading(false);
            } else {
                setIsLoading(false);
                toastRef.current.addToast({ mode: 'error', message: error.message});
            }
        }
    }

    const fetchRequests = async () => {
        setFailedToFetchRequests(false);
        setIsLoading(true);
        try {
            const { responseStatus, data } = await fetchData(`/api/get/all/requests/${sessionId}`, 'GET');

            if (responseStatus === 200) {
                setRequests(data);
                setIsLoading(false);
            } else if (responseStatus === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end, please try again!');
            }
        } catch (error) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setIsLoading(false);
                toastRef.current.addToast({ mode: 'warning', message: error.message});
            } else if (error.message === "No requests found, please try again!") {
                setIsLoading(false);
            } else if (error.message === "Failed to fetch") {
                setFailedToFetchRequests(true);
                setIsLoading(false);
            } else {
                setIsLoading(false);
                toastRef.current.addToast({ mode: 'error', message: error.message});
            }
        }
    };

    const goBack = () => {
        navigate('/home');
        setFailedToFetchCategories(false);
        setFailedToFetchRequests(false);
    };

    const getCategoryName = (categoryId) => {
        const foundCategory = categories.find(category => category.categoryId === categoryId);
        return foundCategory ? foundCategory.categoryName : '';
    };

    const fetchCurrent = () => {
        fetchCategories();
        fetchRequests();
    }

    useEffect(() => {
        fetchCurrent();
    }, []);

    if (requests) {
        for (let i=0; i<requests.length; i++) {
            const request = requests[i];
            const categoryName = getCategoryName(request.categoryId);
            requestRows.unshift(
                <tr key={request.requestId}>
                    <td className="table-data">{request.requestId}</td>
                    <td className="table-data">{categoryName}</td>
                    <td className="table-data">{request.comment}</td>
                    <td className="table-data">{request.amount}</td>
                    <td className="table-data">
                        <UpdateRequest toastRef={toastRef} request={request} onUpdate={fetchRequests}/>
                        <DeleteRequest toastRef={toastRef} request={request} onUpdate={fetchRequests}/>
                    </td>
                </tr>
            )
        }
    }

    return (
        <>
            <AddRequest toastRef={toastRef} onUpdate={fetchRequests}/>
            {isLoading ? (
                <div className="loading-indicator">
                    <FaSpinner className="spinner" />
                </div>
            ) : failedToFetchCategories ? (
                <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={fetchCurrent}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
            ) : failedToFetchRequests ? (
                <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={fetchCurrent}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
            ) : requests.length === 0 ? (
                <div className="empty-list">No requests have been created yet. Click the Add Request button to create a new reimbursement request.</div>
            ) : (
                <div className="list">
                    <table className="table">
                        <thead>
                            <tr>
                                <th className="table-head">Request ID</th>
                                <th className="table-head">Category</th>
                                <th className="table-head">Comment</th>
                                <th className="table-head">Amount</th>
                                <th className="table-head">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {requestRows}
                        </tbody>
                    </table>
                </div>
            )}
        </>
    )
};

RequestList.propTypes = {
    toastRef: PropTypes.object.isRequired
};
