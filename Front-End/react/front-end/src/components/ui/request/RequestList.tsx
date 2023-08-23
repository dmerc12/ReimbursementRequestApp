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

    let requestRows = []

    return (
        <>
        
        </>
    )
}