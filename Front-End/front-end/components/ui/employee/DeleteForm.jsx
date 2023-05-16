'use client'

import { useRouter} from 'next/router';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie'

export default function DeleteForm({ sessionId }) {

    const router = useRouter();


    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('/api/employee/handleDelete',
            {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({'sessionId': sessionId,})
            })
            console.log(response)
            const result = await response.json();

            if (result.success) {
                Cookies.remove('sessionId');
                router.push('/login');
                toast.success("Account successfully deleted, goodbye!", {
                    toastId: 'customId'
                });
            } else if (result.error.message) {
                throw new Error(`${result.error.message}`);
            } else if (result.error) {
                throw new Error(`${result.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again!")
            }
        } catch (error) {
            if (error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                router.push('/login');
                toast.warn(error.message, {
                    toastId: 'customId'
                });
            } else {
                console.log(error)
                toast.error(error.message, {
                    toastId: 'customId'
                });
            }
        }
    }

    return (
        <>
            <h1>Confirm Deletion Below</h1>
            <p> Any requests or subsequent categories you are associated with will also be deleted. Are you sure you would like to delete your information?</p>
            
            <form className="form" onSubmit={onSubmit}>
                <button className="form-btn-2">Yes, Please Delete My Infomation</button>
            </form>
        </>
    )
}