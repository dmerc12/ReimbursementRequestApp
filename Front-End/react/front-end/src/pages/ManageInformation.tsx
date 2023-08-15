import { UpdateForm } from "../components/ui/employee/UpdateForm";
import { useEffect, useState } from "react";
import { parsePath, useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import { toast } from 'react-toastify';

export const ManageInformation = () => {
    const [employee, setEmployee] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        const sessionIdCookie = Cookies.get('sessionId');
        if (!sessionIdCookie) {
            navigate('/login');
            toast.info("Please login or register to gain access!", {
                toastId: 'customId'
            });
        }

        const fetchEmployee = async() => {
            try {
                const response = await fetch('http://localhost:8080/get/employee', {
                    method: 'PATCH',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({'sessionId': sessionIdCookie})
                });
                const data = await response.json();
                if (response.status === 200) {
                    setEmployee(data);
                } else if (response.status === 400) {
                    throw new Error(`${data.message}`);
                } else {
                    throw new Error("Cannot connect to the back end of the application, please try again!");
                }
            } catch (error: any) {
                if (error.message === "Session has expired, please login" || error.message === "No session found, please try again!") {
                    Cookies.remove('sessionId');
                    navigate('/loigin');
                    toast.warn(error.message, {
                        toastId: 'customId'
                    });
                } else {
                    toast.error(error.message, {
                        toastId: 'customId'
                    });
                }
            }
        }
        fetchEmployee();
    }, [])
    
    return (
        <>
            <h1>Manage Your Information Below!</h1>
            {employee && <UpdateForm  employee={employee}/>}
        </>
    );
}