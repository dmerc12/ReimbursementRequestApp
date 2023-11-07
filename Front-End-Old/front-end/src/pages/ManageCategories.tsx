import { CategoryList } from "../components/ui/category/CategoryList";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import Cookies from "js-cookie";

export const ManageCategories = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const sessionId = Cookies.get('sessionId');
        if (!sessionId) {
            navigate('/login')
            toast.info("Please login or register to gain access!", {
                toastId: 'customId'
            });
        }
    }, []);

    return (
        <>
            <h1>Manage Categories</h1>
            <CategoryList />
        </>
    );
}