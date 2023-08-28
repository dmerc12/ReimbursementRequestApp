import { AiOutlinePlus, AiOutlineExclamationCircle } from "react-icons/ai";
import { FaSpinner, FaSync } from "react-icons/fa";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Modal } from "../../Modal";
import Cookies from "js-cookie";
import { Category } from "../category/CategoryList";

export const AddRequest = () => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetchCategories, setFailedToFetchCategories] = useState(false);
    const [failedToFetchSubmission, setFailedToFetchSubmission] = useState(false)
    const [categoryId, setCategoryId] = useState(0);
    const [comment, setComment] = useState('');
    const [amount, setAmount] = useState(0.00);
    const [categories, setCategories] = useState<Category[]>([])

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
                setCategoryId(data[0].categoryId);
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
        try {
            setLoading(true);
            setFailedToFetchCategories(false);
            setFailedToFetchSubmission(false);

            const sessionId = Cookies.get('sessionId');

            const response = await fetch('http://localhost:8080/create/request/now', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'categoryId': categoryId,
                    'comment': comment,
                    'amount': amount
                })
            });

            const data = await response.json();

            if (response.status === 201) {
                setLoading(false);
                setVisible(false);
                setCategoryId(categories[0].categoryId);
                setComment('');
                setAmount(0.00);
                setCategories([]);
                window.location.reload();
                toast.success("Request Successfully Added!", {
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
    }, [])


    return ( 
        <>
            <div className="component">
                <button onClick={() => {setVisible(true); setFailedToFetchCategories(false); setFailedToFetchSubmission(false)}} className="action-btn" id="addRequestModal">Add New Request <AiOutlinePlus className="plus-icon" size={15}/></button>
            </div>

            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetchCategories(false); setFailedToFetchSubmission(false)}}>
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
                            <label className="form-label" htmlFor="addRequestCategoryDropdown">Category: </label>
                            <select className="form-input" id="addRequestCategoryDropdown" name="addRequestCategoryDropdown" value={categoryId} onChange={event => setCategoryId(parseInt(event.target.value, 10))}>
                                {categories && categories.length > 0 && (
                                    categories.map(category => (
                                        <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                                    ))
                                )}
                            </select>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="addRequestComment">Comment: </label>
                            <input className="form-input" type="text"  id="addRequestComment" name="addReuestComment" value={comment} onChange={event => setComment(event.target.value)}/>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="addRequestAmount">Amount: </label>
                            <input className="form-input" type="number" name="addRequestAmount" id="addRequestAmount" value={amount} onChange={event => setAmount(parseFloat(event.target.value))}/>
                        </div>

                        <button className="form-btn-1" type="submit" id="addRequestButton">Add Request</button>
                    </form>
                )}
            </Modal>
        </>
    )
}