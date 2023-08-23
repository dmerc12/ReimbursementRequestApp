import { UpdateCategory } from "./UpdateCategory";
import { DeleteCategory } from "./DeleteCategory";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from 'react';
import { toast } from "react-toastify";
import Cookies from "js-cookie";
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

export interface Category {
    categoryId: number,
    categoryName: string
}

export const CategoryList = () => {
    const [categories, setCategories] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const navigate = useNavigate();

    let categoryRows = []

    const fetchCategories = async () => {
        try {
            setIsLoading(true);
            setFailedToFetch(false);

            const sessionId = Cookies.get('sessionId');
                
            const response = await fetch(`http://localhost:8080/get/all/categories/${sessionId}`, {
                method: 'GET',
                headers: {'Content-Type': 'application/json'}
            });

            const data = await response.json();

            if (response.status === 200) {
                setCategories(data);
                setIsLoading(false);
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error('Cannot connect to the back end, please try again!');
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

    const goBack = () => {
        navigate('/home');
        setFailedToFetch(false);
    }

    useEffect(() => {
        fetchCategories();
    }, [])

    if (categories.length > 0) {
        for (let i = 0; i < categories.length; i++) {
            const category: Category = categories[i];
            categoryRows.unshift(
                <tr key={category.categoryId}>
                    <td className="table-data">{category.categoryName}</td>
                    <td className="table-data crud-icons">
                        <UpdateCategory category={category} onUpdate={fetchCategories}/>
                        <DeleteCategory category={category} onUpdate={fetchCategories}/>
                    </td>
                </tr>
            )
        }
    }

    return (
        <>
            {isLoading ? (
                <div className='loading-indicator'>
                    <FaSpinner className='spinner' />
                </div>
            ) : failedToFetch ? (
                <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={fetchCategories}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
            ) : categories.length === 0 ? (
                <div>No categories found</div>
            ) : (
                <table className="table">
                    <thead>
                        <tr>
                            <th className="table-head">Category Name</th>
                            <th className="table-head">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {categoryRows}
                    </tbody>
                </table>
            )}
        </>
    )
}
