import { AiOutlinePlus, AiOutlineExclamationCircle } from "react-icons/ai";
import { FaSpinner, FaSync } from "react-icons/fa";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Modal } from "../../Modal";
import Cookies from "js-cookie";
import { Category } from "../category/CategoryList";

export const AddRequest = () => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);
    const [categoryId, setCategoryId] = useState(0);
    const [comment, setComment] = useState('');
    const [amount, setAmount] = useState(0.00);
    const [categories, setCategories] = useState<Category[]>([])

    const fetchCategories = async () => {

    }

    const onSubmit = async (event: any) => {

    }

    const goBack = () => {

    }

    return ( 
        <>
            <div className="component">
                <button onClick={() => {setVisible(true); setFailedToFetch(false)}} className="action-btn" id="addRequestModal">Add New Request <AiOutlinePlus className="plus-icon" size={15}/></button>
            </div>

            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetch(false)}}>
                {loading ? (
                    <div className="loading-indicator">
                        <FaSpinner className="spinner"/>
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