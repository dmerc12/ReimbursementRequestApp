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
        const sessionIdCookie = Cookies.get('sessionId');
        if (!sessionIdCookie) {
            navigate('/login')
            toast.info("Please login or register to gain access!", {
                toastId: 'customId'
            });
        }
    }, []);

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {

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