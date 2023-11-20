import Cookies from 'js-cookie';
import PropTypes from 'prop-types';

import { Modal } from '../Modal';
import { useState } from 'react';
import { useFetch } from '../../hooks';
import { useNavigate } from 'react-router-dom';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlinePlus, AiOutlineExclamationCircle } from 'react-icons/ai';

export const CreateCategory = ({ toastRef, onUpdate }) => {
    const sessionId = Cookies.get('sessionId');

    const [addCategoryForm, setAddCategoryForm] = useState({
        sessionId: Number(sessionId),
        categoryName: ''
    });
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const { fetchData } = useFetch();

    const navigate = useNavigate();

    const onSubmit = async (event) => {
        event.preventDefault();
        setLoading(true);
        setFailedToFetch(false);
        try {
            const { responseStatus, data } = await fetchData('/api/create/category', 'POST', addCategoryForm);

            if (responseStatus === 201) {
                onUpdate();
                setVisible(false);
                setLoading(false);
                setAddCategoryForm({
                    sessionId: Number(sessionId),
                    categoryName: ''
                });
                toastRef.current.addToast({ mode: 'success', message: 'Category Successfully Added!' });
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

    const onChange = (event) => {
        const { name, value } = event.target;
        setAddCategoryForm((prevForm) => ({
            ...prevForm,
            [name]: value
        }));
    };

    const goBack = () => {
        setFailedToFetch(false);
    };

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
                            <label className='form-label' htmlFor="categoryName">New Category Name: </label>
                            <input className='form-input' type="text" id='newCategoryName' name='categoryName' value={addCategoryForm.categoryName} onChange={onChange}/>
                        </div>

                        <button className='form-btn-1' type='submit' id='addCategoryButton'>Add Category</button>
                    </form> 
                )}
            </Modal>
        </>
    )
};

CreateCategory.propTypes = {
    toastRef: PropTypes.object.isRequired,
    onUpdate: PropTypes.func.isRequired
};
