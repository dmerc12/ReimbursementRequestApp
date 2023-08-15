import { UpdateForm } from "../components/ui/employee/UpdateForm";
import { ChangePasswordForm } from "../components/ui/employee/ChangePasswordForm";
import { DeleteForm } from "../components/ui/employee/DeleteForm";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import { toast } from 'react-toastify';

export const ManageInformation = () => {
    const [employee, setEmployee] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        const sessionId = Cookies.get('sessionId');
        if (!sessionId) {
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
                    body: JSON.stringify({'sessionId': sessionId})
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
        fetchEmployee();
    }, [])
    
    return (
        <>
            <h1>Manage Your Information Below!</h1>
            <div className="action-btn-container">
                {employee && <UpdateForm  employee={employee}/>}
                <ChangePasswordForm />
                <DeleteForm />
            </div>
        </>
    );
}