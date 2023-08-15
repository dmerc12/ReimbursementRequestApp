import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';
import Cookies from "js-cookie";
import { Modal } from "../../Modal";

export const ChangePasswordForm = () => {
    const [visible, setVisible] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [password, setPassword] = useState('');
    const [confirmationPassword, setConfirmationPassword] = useState('');

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

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setIsLoading(true);
        try {
            const sessionId = Cookies.get('sessionId');
            const response = await fetch('http://localhost:8080/change/password/now', {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'password': password,
                    'confirmationPassword': confirmationPassword
                })
            });

            const data = await response.json();

            if (response.status === 200) {
                navigate('/manage-information');
                setVisible(false);
                setIsLoading(false);
                toast.success("Password successfully changed!", {
                    toastId: 'customId'
                });
            } else if (response.status === 400) {
                throw new Error(`${data.messsage}`);
            } else {
                throw new Error("Cannot connect to the back end, please try again!");
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setVisible(false);
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else {
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        }
    };

    return (
        <>
            <div className="change-password-component">
                <button onClick={() => setVisible(true)} className="change-password-btn" id="changePasswordModal">Change Password</button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
                <form className="form" onSubmit={onSubmit}>
                    <div className="form-field">
                        <label className="form-label" htmlFor="newPassword">New Password: </label>
                        <input className="form-input" type="password" id="newPassword" name="newPassword" value={password} onChange={event => setPassword(event.target.value)}/>
                    </div>

                    <div className="form-field">
                        <label className="form-label" htmlFor="newConfirmationPassword">Confirm Password: </label>
                        <input className="form-input" type="password" id="newConfirmationPassword" name="newConfirmationPassword" value={confirmationPassword} onChange={event => setConfirmationPassword(event.target.value)}/>
                    </div>

                    <button disabled={isLoading} className="form-btn-1" type="submit">{isLoading ? "Changing Password..." : "Change Password"}</button>
                </form>
            </Modal>
        </>
    )
}