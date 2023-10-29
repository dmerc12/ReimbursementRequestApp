import { AiOutlinePlus, AiOutlineExclamationCircle } from "react-icons/ai";
import { FaSpinner, FaSync } from "react-icons/fa";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useFetch } from "../../../hooks/useFetch";
import { toast } from "react-toastify";
import { Modal } from "../../Modal";
import Cookies from "js-cookie";
import { Category } from "../category/CategoryList";

export const AddRequest = (props: { onUpdate: () => void}) => {
    const sessionId = Cookies.get('sessionId');

    const [addRequestForm, setAddRequestForm] = useState({
        sessionId: Number(sessionId),
        categoryId: 0,
        comment: '',
        amount: 0.00
    });
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetchCategories, setFailedToFetchCategories] = useState(false);
    const [failedToFetchSubmission, setFailedToFetchSubmission] = useState(false);
    const [categories, setCategories] = useState<Category[]>([])

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
                setAddRequestForm((prevForm) => ({
                    ...prevForm,
                    categoryId: data[0].categoryId}));
                setLoading(false);
            } else if (responseStatus === 400) {
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
            } else if (error.message === "No categories found, please try again!") {
                setLoading(false);
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
    };

    const onSubmit = async (event: any) => {
        event.preventDefault();
        setLoading(true);
        setFailedToFetchCategories(false);
        setFailedToFetchSubmission(false);
        try {
            const { responseStatus, data } = await fetchData('/api/create/request', 'POST', addRequestForm)
 
            if (responseStatus === 201) {
                setLoading(false);
                setVisible(false);
                setAddRequestForm({
                    sessionId: Number(sessionId),
                    categoryId: categories[0].categoryId,
                    comment: '',
                    amount: 0.00
                });
                setCategories([]);
                props.onUpdate();
                toast.success("Request Successfully Added!", {
                    toastId: 'customId'
                });
            } else if (responseStatus === 400) {
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
    };

    const onChange = (event: any) => {
        const { name, value }  = event.target;
        setAddRequestForm((prevForm) => ({
            ...prevForm,
            [name]: value
        }));
    };

    const goBack = () => {
        setFailedToFetchCategories(false);
        setFailedToFetchSubmission(false);
    };

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
                            <label className="form-label" htmlFor="categoryId">Category: </label>
                            <select className="form-input" id="addRequestCategoryDropdown" name="categoryId" value={addRequestForm.categoryId} onChange={onChange}>
                                {categories && categories.length > 0 && (
                                    categories.map(category => (
                                        <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                                    ))
                                )}
                            </select>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="comment">Comment: </label>
                            <input className="form-input" type="text"  id="addRequestComment" name="comment" value={addRequestForm.comment} onChange={onChange}/>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="amount">Amount: </label>
                            <input className="form-input" type="number" id="addRequestAmount" name="amount" value={addRequestForm.amount} onChange={onChange} />
                        </div>

                        <button className="form-btn-1" type="submit" id="addRequestButton">Add Request</button>
                    </form>
                )}
            </Modal>
        </>
    )
}