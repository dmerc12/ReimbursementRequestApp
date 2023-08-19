import { Category } from "./CategoryList";
import { useState } from 'react';
import { FiEdit } from 'react-icons/fi';
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import Cookies from "js-cookie";
import { Modal } from "../../Modal";
import { FaSpinner } from 'react-icons/fa';

export const UpdateCategory = (props: { category: Category, onUpdate: () => void}) => {
    const [visible, setVisible] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);
    const [categoryName, setCategoryName] = useState(props.category.categoryName);

    const navigate = useNavigate();

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setIsLoading(true);
        try {
            const sessionId = Cookies.get('sessionId');
            
            const response = await fetch('http://localhost:8080/update/category/now', {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'categoryId': props.category.categoryId,
                    'categoryName': categoryName
                })
            });

            const data = await response.json();

            if (response.status === 200) {
                navigate('/manage-categories');
                props.onUpdate();
                setVisible(false);
                setIsLoading(false);
                toast.success("Category Successfully Updated!", {
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
            <FiEdit onClick={() => {setVisible(true); setFailedToFetch(false)}} cursor='pointer' size={15}/>
            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetch(false)}}>
                {isLoading ? (
                    <div className='loading-indicator'>
                        <FaSpinner className='spinner' />
                    </div> 
                ) : failedToFetch ? (
                    <div className='failed-to-fetch'>Cannot connect to the back end server, please try again!</div>
                ) : (
                    <form className="form" onSubmit={onSubmit}>
                        <div className="form-field">
                            <label className="form-label" htmlFor="updateCategoryName">Category Name: </label>
                            <input className="form-input" type="text" id="updateCategoryName" name="updateCategoryName" value={categoryName} onChange={event => setCategoryName(event.target.value)}/>
                        </div>

                        <button disabled={isLoading} className="form-btn-1" type="submit" id="updateCategoryButton">{isLoading ? "Updating Category..." : "Update Category"}</button>
                    </form>
                )}
            </Modal>
        </>
    )
}
