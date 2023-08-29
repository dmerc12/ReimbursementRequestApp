import { UpdateRequest } from "./UpdateRequest";
import { DeleteRequest } from "./DeleteRequest";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { toast } from 'react-toastify';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';
import Cookies from "js-cookie";
import { Category } from "../category/CategoryList";

export interface Request {
    requestId: number,
    categoryId: number,
    comment: string,
    amount: number
}

export const RequestList = () => {
    const [categories, setCategories] = useState<Category[]>([]);
    const [requests, setRequests] = useState<Request[]>([]);
    const [isLoading, setIsLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const navigate = useNavigate();

    let requestRows = [];

    const fetchCategories = async () => {
        try {
            setIsLoading(true);
            setFailedToFetch(false);

            const sessionId = Cookies.get('sessionId');
                
            const response = await fetch(`http://localhost:8080/get/all/categories/${sessionId}`, {
                method: 'GET',
                headers: {'Content-Type': 'application/json'}
            });

            const data = await response.json();

            if (response.status === 200) {
                setCategories(data);
                setIsLoading(false);
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end, please try again!');
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "No categories found, please try again!") {
                setIsLoading(false);
            } else if (error.message === "Failed to fetch") {
                setFailedToFetch(true);
                setIsLoading(false);
            } else {
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        }
    }

    const fetchRequests = async () => {
        try {
            setFailedToFetch(false);
            setIsLoading(true);

            const sessionId = Cookies.get('sessionId');

            const response = await fetch(`http://localhost:8080/get/all/requests/${sessionId}`, {
                method: "GET",
                headers: {'Content-Type': 'application/json'}
            });

            const data = await response.json();

            if (response.status === 200) {
                setRequests(data);
                setIsLoading(false);
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end, please try again!');
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "No requests found, please try again!") {
                setIsLoading(false);
            } else if (error.message === "Failed to fetch") {
                setFailedToFetch(true);
                setIsLoading(false);
            } else {
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        }
    }

    const goBack = () => {
        navigate('/home');
        setFailedToFetch(false);
    }

    const getCategoryName = (categoryId: number) => {
        const foundCategory = categories.find(category => category.categoryId === categoryId);
        return foundCategory ? foundCategory.categoryName : ''
    }

    const fetchData = () => {
        fetchCategories();
        fetchRequests();
    }

    useEffect(() => {
        fetchData();
    }, [])

    if (requests) {
        for (let i=0; i<requests.length; i++) {
            const request: Request = requests[i];
            const categoryName = getCategoryName(request.categoryId);
            requestRows.unshift(
                <tr key={request.requestId}>
                    <td className="table-data">{request.requestId}</td>
                    <td className="table-data">{categoryName}</td>
                    <td className="table-data">{request.comment}</td>
                    <td className="table-data">{request.amount}</td>
                    <td className="table-data">
                        <UpdateRequest request={request} onUpdate={fetchRequests}/>
                        <DeleteRequest request={request} onUpdate={fetchRequests}/>
                    </td>
                </tr>
            )
        }
    }

    return (
        <>
            {isLoading ? (
                <div className="loading-indicator">
                    <FaSpinner className="spinner" />
                </div>
            ) : failedToFetch ? (
                <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={fetchData}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
            ) : requests.length === 0 ? (
                <div>No requests yet</div>
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
}