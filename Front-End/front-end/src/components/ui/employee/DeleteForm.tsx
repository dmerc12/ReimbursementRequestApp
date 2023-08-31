import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { Modal } from "../../Modal";
import Cookies from "js-cookie";
import { toast } from "react-toastify";
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

export const DeleteForm = () => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const navigate = useNavigate();

    const onSubmit = async (event: any) => {
        event.preventDefault();
        setLoading(true);
        try {
            const sessionId = Cookies.get('sessionId');

            const response = await fetch('http://localhost:8080/delete/employee/now', {
                method: 'DELETE',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId
                })
            })

            const data = await response.json();

            if (response.status === 200) {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                setVisible(false);
                toast.success("Profile successfully deleted, goodbye!", {
                    toastId: 'customId'
                });
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end, please try again!");
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetch(true);
                setLoading(false);
            } else {
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        }
    };

    const goBack = () => {
        setFailedToFetch(false);
    }

    return (
        <>
            <div className="component">
                <button onClick={() => {setVisible(true); setFailedToFetch(false)}} className="action-btn" id="deleteInformationModal">Delete Profile</button>
            </div>

            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetch(false)}}>
                {loading ? (
                    <div className='loading-indicator'>
                        <FaSpinner className='spinner' />
                    </div>
                ) : failedToFetch ? (
                    <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={onSubmit}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
                ) : (
                    <form className="form" onSubmit={onSubmit}>
                        <h1>Confirm Deletion Below</h1>
                        <p>Any requests or subsequent categories you are associated with will also be deleted. Are you sure you?</p>

                        <button id="deleteInformationButton" className="form-btn-1" type="submit">Delete Profile</button>
                </form>
                )}
            </Modal>
        </>
    )
}