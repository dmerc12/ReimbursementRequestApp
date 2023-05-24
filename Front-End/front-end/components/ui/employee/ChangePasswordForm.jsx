'use client'

import { useState } from 'react';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import Modal from '@/components/Model';

export default function ChangePasswordForm ({ sessionId }) {
    const [password, setPassword] = useState('');
    const [visible, setVisible] = useState(false);

    const router = useRouter();

    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('/api/employee/handlePasswordChange', {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'password':  password
                })
            })

            const data = await response.json();
            
            if (data.success) {
                router.push('/manage-information');
                setVisible(false);
                toast.success("Password Successfully Changed!", {
                    toastId: "customId"
                });
            } else if (data.error.message) {
                throw new Error(`${data.error.message}`);
            }  else if (data.error) {
                throw new Error(`${data.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again!")
            }
        } catch (error) {
            if (error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                router.push('/login');
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

    return (
        <>
            <div className='change-password-component'>
                <button onClick={() => setVisible(true)} className='change-password-btn' id='changePasswordModal'>Change Password</button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
                <form className='form' onSubmit={onSubmit}>
                    <div className='form-field'>
                        <label className='form-label' htmlFor="password">New Password: </label>
                        <input className='form-input' type="password" id='updatePassword' name='password' value={password} onChange={event => setPassword(event.target.value)}/>
                    </div>

                    <button className='form-btn-2' type='submit' id='changePasswordButton'>Change Password</button>
                </form>
            </Modal>
        </>
    )
}