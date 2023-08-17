import { UpdateCategory } from "./UpdateCategory";
import { DeleteCategory } from "./DeleteCategory";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from 'react';
import { toast } from "react-toastify";
import Cookies from "js-cookie";

export interface Category {
    categoryId: number,
    categoryName: String
}

export const CategoryList = () => {
    const [categories, setCategories] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const navigate = useNavigate();

    let categoryRows = []

    useEffect(() => {
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
        fetchCategories();
    }, [navigate]);

    if (categories.length > 0) {
        for (let i = 0; i < categories.length; i++) {
            const category: Category = categories[i];
            categoryRows.unshift(
                <tr key={category.categoryId}>
                    <td className="table-data">{category.categoryName}</td>
                    <td className="table-data crud-icons">
                        <UpdateCategory category={category} />
                        <DeleteCategory category={category} />
                    </td>
                </tr>
            )
        }
    }

    if (failedToFetch) {
        return <div className="failed-to-fetch">Cannot connect to the back end server, please try again!</div>
    }

    if (isLoading) {
        return <div className="loading-indicator">Loading...</div>
    }

    if (categories.length === 0) {
        return <div>No categories found.</div>
    }

    return (
        <>
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
        </>
    )
}
