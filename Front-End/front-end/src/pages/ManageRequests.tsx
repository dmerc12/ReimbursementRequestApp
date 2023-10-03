import { RequestList } from "../components/ui/request/RequestList";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { toast } from 'react-toastify';
import Cookies from "js-cookie";

export const ManageRequests = () => {
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
            <h1>Manage Requests Below!</h1>
            <RequestList />
        </>
    );
}