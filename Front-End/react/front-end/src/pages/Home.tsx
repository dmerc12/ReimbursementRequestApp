import Cookies from "js-cookie";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';
import { Link } from "react-router-dom";

export const Home = () => {
    const [sessionId, setSessionId] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        const sessionIdCookie = Cookies.get('sessionId');
        if (!sessionIdCookie) {
            navigate('/login')
            toast.info("Please login or register to gain access!", {
                toastId: 'customId'
            })
        } else {
            setSessionId(sessionIdCookie);
        }
    }, [])

    return (
        <>
            <div className="nav-btn-container">
                <Link className="nav-btn" to='/manage-requests'>Manage Request Information</Link>
                <Link className="nav-btn" to='/manage-categories'>Manage Categories</Link>
                <Link className="nav-btn" to='/manage-information'>Manage Information</Link>
            </div>
        </>
    );
}
