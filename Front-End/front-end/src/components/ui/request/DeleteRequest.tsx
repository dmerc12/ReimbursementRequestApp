import { Request } from "./RequestList"
import { useState, useEffect } from 'react';
import { FiTrash2 } from "react-icons/fi";
import { FaSpinner, FaSync } from "react-icons/fa";
import { AiOutlineExclamationCircle } from "react-icons/ai";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { Modal } from "../../Modal";
import Cookies from "js-cookie";

export const DeleteRequest = (props: { request: Request, onUpdate: ()=> void}) => {
    const [visible, setVisible] = useState(false);
    const [loading, setLoading] = useState(false);
    const [failedToFetchSubmission, setFailedToFetchSubmission] = useState(false);
    const [requestId, setRequestId] = useState(0);

    const sessionId = Cookies.get('sessionId');

    const navigate = useNavigate();

    const onSubmit = async (event: any) => {
        setLoading(true);
        setFailedToFetchSubmission(false);
        try {
            const response = await fetch(`http://localhost:8080/delete/request/${requestId}/${sessionId}`, {
                method: 'DELETE',
                headers: {'Content-Type': 'application/json'}
            });

            const data = await response.json();

            if (response.status === 200) {
                setLoading(false);
                setVisible(false);
                setRequestId(0);
                window.location.reload();
                toast.success("Request Successfully Deleted!", {
                    toastId: 'customId'
                });
            } else if (response.status == 400) {
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
                setFailedToFetchSubmission(true);
                setLoading(false);
            } else {
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        } 
    }

    const goBack = () => {
        setFailedToFetchSubmission(false);
    }

    useEffect(() => {
        setRequestId(props.request.requestId);
    }, [])

    return (
        <>
            <FiTrash2 id="deleteRequestModal" onClick={() => {setVisible(true); setFailedToFetchSubmission(false)}} cursor='pointer' size={15} />
            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetchSubmission(false)}}>
                {loading ? (
                    <div className="loading-indicator">
                        <FaSpinner className="spinner"/>
                    </div>
                ) : failedToFetchSubmission ? (
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
                        <div className="form-field">
                            <label className="form-label" htmlFor="deleteRequestId">Request ID: </label>
                            <input className="form-input" disabled type="text" id="deleteRequestId" name="deleteRequestId" value={requestId}/>
                        </div>

                        <div className="form-field">
                            <label className="form-label">Are You Sure?</label>
                        </div>

                        <button className="form-btn-2" type="submit" id="deleteRequestButton">Delete Request</button>
                    </form>
                )}
            </Modal>
        </>
    )
}