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
    
    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        
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
