import { AiOutlinePlus } from 'react-icons/ai';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import { Modal } from '../../Modal';

export const AddCategory = () => {
    const [visible, setVisible] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);
    const [categoryName, setCategoryName] = useState('');

    const navigate = useNavigate();

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            setIsLoading(true);

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
                navigate('/manage-categories');
                setVisible(false);
                setIsLoading(false);
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
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
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

    return (
        <>
            <div className='component'>
                <button onClick={() => {setVisible(true); setFailedToFetch(false)}} className='action-btn' id='addCategoryModal'>Add New Category <AiOutlinePlus className='plus-icon' size={15} /></button>
            </div>

            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetch(false)}}>
                {failedToFetch ? (
                    <div className='failed-to-fetch'>Cannot connect to the back end server, please try again!</div>
                ) : (
                    <form className='form' onSubmit={onSubmit}>
                        <div className='form-field'>
                            <label className='form-label' htmlFor="newCategoryName">New Category Name: </label>
                            <input className='form-input' type="text" id='newCategoryName' name='newCategoryName' value={categoryName} onChange={event => setCategoryName(event.target.value)}/>
                        </div>

                        <button disabled={isLoading} className='form-btn-1' type='submit' id='addCategoryButton'>{isLoading ? "Adding Category..." : "Add Category"}</button>
                    </form> 
                )}
            </Modal>
        </>
    )
}
