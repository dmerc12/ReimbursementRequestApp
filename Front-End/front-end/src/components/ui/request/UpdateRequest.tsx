import { Request } from "./RequestList";
import { Category } from "../category/CategoryList";
import { useState, useEffect } from "react";
import { FiEdit } from "react-icons/fi";
import { useNavigate } from "react-router-dom";
import { Modal } from "../../Modal";
import { FaSpinner, FaSync } from "react-icons/fa";
import { AiOutlineExclamationCircle } from "react-icons/ai";
import { toast } from "react-toastify";
import Cookies from "js-cookie";

export const UpdateRequest = (props: { request: Request, onUpdate: () => void}) => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetchCategories, setFailedToFetchCategories] = useState(false);
    const [failedToFetchSubmission, setFailedToFetchSubmission] = useState(false);
    const [categories, setCategories] = useState<Category[]>([]);
    const [requestId, setRequestId] = useState(0);
    const [categoryId, setCategoryId] = useState(0);
    const [comment, setComment] = useState('');
    const [amount, setAmount] = useState(0.00);

    const navigate = useNavigate();

    const fetchCategories = async () => {
        try {
            setLoading(true);
            setFailedToFetchCategories(false);
            setFailedToFetchSubmission(false);

            const sessionId = Cookies.get('sessionId');
                
            const response = await fetch(`http://localhost:8080/get/all/categories/${sessionId}`, {
                method: 'GET',
                headers: {'Content-Type': 'application/json'}
            });

            const data = await response.json();

            if (response.status === 200) {
                setCategories(data);
                setLoading(false);
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end, please try again!');
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetchCategories(true);
                setLoading(false);
            } else {
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        }
    }

    const onSubmit = async (event: any) => {
        setLoading(true);
        setFailedToFetchCategories(false);
        setFailedToFetchSubmission(false);
        try {
            const sessionId = Cookies.get('sessionId');

            const response = await fetch('http://localhost:8080/update/request/now', {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'requestId': requestId,
                    'categoryId': categoryId,
                    'comment': comment,
                    'amount': amount
                })
            });

            const data = await response.json();

            if (response.status === 200) {
                setLoading(false);
                setVisible(false);
                setRequestId(0);
                setCategoryId(0);
                setComment('');
                setAmount(0.00);
                props.onUpdate();
                toast.success("Request Successfully Updated!", {
                    toastId: 'customId'
                });
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end, please try again!");
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetchSubmission(true);
                setLoading(false);
            } else {
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        } 
    }

    const goBack = () => {
        setFailedToFetchCategories(false);
        setFailedToFetchSubmission(false);
    }

    useEffect(() => {
        fetchCategories();
        setRequestId(props.request.requestId);
        setCategoryId(props.request.categoryId);
        setComment(props.request.comment);
        setAmount(props.request.amount);
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
                            <label className="form-label" htmlFor="updateRequestId">Request ID: </label>
                            <input className="form-input" disabled type="text" id="updateRequestId" name="updateRequestId" value={requestId}/>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateRequestCategoryDropDown">Category: </label>
                            <select className="form-input" name="updateRequestCategoryDropDown" id="updateRequestCategoryDropDown" value={categoryId} onChange={event => setCategoryId(parseInt(event.target.value, 10))}>
                                {categories && categories.length > 0 && (
                                    categories.map(category => (
                                        <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                                    ))
                                )}
                            </select>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateRequestComment">Comment: </label>
                            <input className="form-input" type="text"  id="updateRequestComment" name="updateRequestComment" value={comment} onChange={event => setComment(event.target.value)}/>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateRequestAmount">Amount: </label>
                            <input className="form-input" type="number" name="updateRequestAmount" id="updateRequestAmount" value={amount} onChange={event => setAmount(parseFloat(event.target.value))}/>
                        </div>

                        <button className="form-btn-1" type="submit" id="updateRequestButton">Update Request</button>
                    </form>
                )}
            </Modal>
        </>
    )
}