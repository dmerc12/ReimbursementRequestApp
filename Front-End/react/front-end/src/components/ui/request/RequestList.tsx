import { UpdateRequest } from "./UpdateRequest";
import { DeleteRequest } from "./DeleteRequest";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { toast } from 'react-toastify';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';
import Cookies from "js-cookie";

export interface Request {
    requestId: number,
    categoryId: number,
    comment: string,
    amount: number
}

export const RequestList = () => {
    const [categories, setCategories] = useState([]);
    const [requests, setRequests] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const navigate = useNavigate();

    let requestRows = [];

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

    const fetchRequests = async () => {
        try {

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
        fetchRequests();
    }, [])

    return (
        <>
        
        </>
    )
}