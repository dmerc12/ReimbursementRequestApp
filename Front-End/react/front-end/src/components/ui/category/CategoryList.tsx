import { UpdateCategory } from "./UpdateCategory";
import { DeleteCategory } from "./DeleteCategory";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from 'react';
import { toast } from "react-toastify";
import Cookies from "js-cookie";

export const CategoryList = () => {
    const [categories, setCategories] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

    const navigate = useNavigate();

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                setIsLoading(true);
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
                    toast.warn(error.message, {
                        toastId: "customId"
                    });
                } else {
                    toast.warn(error.message, {
                        toastId: "customId"
                    });
                }
            }
        }
    }, [navigate])
    return (
        <>
        
        </>
    )
}
