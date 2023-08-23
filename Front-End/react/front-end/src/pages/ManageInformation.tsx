import { UpdateForm } from "../components/ui/employee/UpdateForm";
import { ChangePasswordForm } from "../components/ui/employee/ChangePasswordForm";
import { DeleteForm } from "../components/ui/employee/DeleteForm";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import { toast } from 'react-toastify';

export const ManageInformation = () => {
    const navigate = useNavigate();

    useEffect(() => {
        const sessionId = Cookies.get('sessionId');
        if (!sessionId) {
            navigate('/login');
            toast.info("Please login or register to gain access!", {
                toastId: 'customId'
            });
        }
    }, [])
    
    return (
        <>
            <h1>Manage Information Below!</h1>
            <div className="action-btn-container">
                <UpdateForm />
                <ChangePasswordForm />
                <DeleteForm />
            </div>
        </>
    );
}