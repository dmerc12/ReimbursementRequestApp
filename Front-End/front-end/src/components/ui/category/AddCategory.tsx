import { AiOutlinePlus, AiOutlineExclamationCircle } from 'react-icons/ai';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import { Modal } from '../../Modal';

export const AddCategory = () => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);
    const [categoryName, setCategoryName] = useState('');

    const navigate = useNavigate();

    const onSubmit = async (event: any) => {
        event.preventDefault();
        try {
            setLoading(true);

            const sessionId = Cookies.get('sessionId');

            const response = await fetch('http://localhost:8080/create/category/now', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'categoryName': categoryName
                })
            });

            const data = await response.json();

            if (response.status === 201) {
                window.location.reload();
                setVisible(false);
                setLoading(false);
                setCategoryName('');
                toast.success("Category Successfully Added!", {
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
    }

    return (
        <>
            <div className='component'>
                <button onClick={() => {setVisible(true); setFailedToFetch(false)}} className='action-btn' id='addCategoryModal'>Add New Category <AiOutlinePlus className='plus-icon' size={15} /></button>
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
                    <form className='form' onSubmit={onSubmit}>
                        <div className='form-field'>
                            <label className='form-label' htmlFor="newCategoryName">New Category Name: </label>
                            <input className='form-input' type="text" id='newCategoryName' name='newCategoryName' value={categoryName} onChange={event => setCategoryName(event.target.value)}/>
                        </div>

                        <button className='form-btn-1' type='submit' id='addCategoryButton'>Add Category</button>
                    </form> 
                )}
            </Modal>
        </>
    )
}
