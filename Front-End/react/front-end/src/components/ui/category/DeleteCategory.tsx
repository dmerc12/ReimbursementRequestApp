import { Category } from "./CategoryList";
import { useState } from 'react';
import { FiTrash2 } from  'react-icons/fi';
import { FaSpinner } from 'react-icons/fa';
import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';
import { Modal } from "../../Modal";
import Cookies from "js-cookie";

export const DeleteCategory = (props: { category: Category, onUpdate: () => void}) => {
    const [visible, setVisible] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const navigate = useNavigate();
    
    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            const sessionId = Cookies.get('sessionId');

            const response = await fetch(`http://localhost:8080/delete/category/${props.category.categoryId}/${sessionId}`, {
                method: 'DELETE',
                headers: {'Content-Type': 'application/json'}
            });

            const data = await response.json();

            if (response.status === 200) {
                props.onUpdate();
                setVisible(false);
                setIsLoading(false);
                toast.success("Category Successfully Deleted!", {
                    toastId: "customId"
                });
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end server, please try again!');
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
            <FiTrash2 onClick={() => {setVisible(true); setFailedToFetch(false)}} cursor='pointer' size={15} />
            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetch(false)}}>
                {isLoading ? (
                    <div className='loading-indicator'>
                        <FaSpinner className='spinner' />
                    </div>
                ) : failedToFetch ? (
                    <div className="failed-to-fetch">Cannot connect to the back end server, please try again!</div>
                ) : (
                    <form className="form" onSubmit={onSubmit}>
                        <div className="form-field">
                            <label className="form-label">Are you sure?</label>
                        </div>

                        <button disabled={isLoading} className="form-btn-1" type="submit" id="deleteCategoryButton">{isLoading ? "Deleting Category..." : "Delete Category"}</button>
                    </form>
                )}
            </Modal>
        </>
    )
}
