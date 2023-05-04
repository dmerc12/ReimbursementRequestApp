'use client'

import { useState } from 'react';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';

export default function ChangePasswordForm () {
    const [newPassword, setNewPassword] = useState('');

    const router = useRouter();

    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('api/employee/handlePasswordChange',
            {
                method: "",
                headers: {'Content-Type': 'application/jsson'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'password': password
                })
            })
            const data = await response.json();

            
        } catch (error) {
            toast.warn(error.message, {
                toastId: "customId"
            });
        }
    }

    return (
        <>

        </>
    )
}