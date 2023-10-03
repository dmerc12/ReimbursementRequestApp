import { Category } from "./CategoryList";
import { FiTrash2 } from  'react-icons/fi';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';
import { useNavigate } from "react-router-dom";
import { useState } from 'react';
import { useFetch } from "../../../hooks/useFetch";
import { toast } from 'react-toastify';
import { Modal } from "../../Modal";
import Cookies from "js-cookie";

export const DeleteCategory = (props: { category: Category, onUpdate: () => void}) => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const { fetchData } = useFetch();

    const sessionId = Cookies.get('sessionId');

    const navigate = useNavigate();
    
    const onSubmit = async (event: any) => {
        event.preventDefault();
        setLoading(true);
        setFailedToFetch(false);
        try {
            const { responseStatus, data } = await fetchData(`/delete/category/${props.category.categoryId}/${sessionId}`, 'DELETE', {});

            if (responseStatus === 200) {
                props.onUpdate();
                setVisible(false);
                setLoading(false);
                toast.success("Category Successfully Deleted!", {
                    toastId: "customId"
                });
            } else if (responseStatus === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end server, please try again!');
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
                setFailedToFetch(true);
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
        setFailedToFetch(false);
        setVisible(false);
    }

    return (
        <>
            <FiTrash2 onClick={() => {setVisible(true); setFailedToFetch(false)}} cursor='pointer' size={15} id={`deleteCategoryModal${props.category.categoryId}`}/>
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
                        <div className="form-field">
                            <label className="form-label">Are you sure?</label>
                        </div>

                        <button className="form-btn-1" type="submit" id="deleteCategoryButton">Delete Category</button>
                    </form>
                )}
            </Modal>
        </>
    )
}
