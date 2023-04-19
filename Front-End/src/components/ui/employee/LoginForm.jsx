'use client'

import { useState } from 'react';
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export default function LoginForm () {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const router = useRouter();


    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('/api/employee/handleLogin',
                {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body:  JSON.stringify({
                        'email': email,
                        'password': password
                    })
                })
                const data = await response.json();
    
                if (data.success) {
                    document.cookie = `sessionId=${data.success.sessionId}`;
                    router.push('/');
                    toast.success("Welcome!");
                    } else if (data.error.message) {
                    throw new Error(`${data.error.message}`);
                } else if (data.error) {
                    throw new Error(`${data.error}`);
                } else {
                    throw new Error("Something went extremely wrong, please try again later!")
                }
        } catch (error) {
            toast.error(error.message);
        }
    }

    return (
        <>
            <form className='form' onSubmit={onSubmit}>
                <div className='form-field'>
                    <label className='form-label' htmlFor="email">Email:</label>
                    <input className='form-input' type="email" id="loginEmail" name="email" value={email} onChange={(event) => setEmail(event.target.value)}/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor="password">Password:</label>
                    <input className='form-input' type="password" id="loginPassword" name="password" value={password} onChange={(event) => setPassword(event.target.value)}/>
                </div>

                <button className='form-btn-1' type="submit">Login</button>
            </form>
        </>
    )
}