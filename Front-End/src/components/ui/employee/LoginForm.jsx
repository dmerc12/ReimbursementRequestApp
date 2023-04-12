'use client'

import { useState } from 'react';
import { useRouter } from 'next/router'

export default function LoginForm () {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

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
                if (response.ok) {
                    document.cookie = `employeeId=${data.success.employeeId}`;
                    router.push('/');
                } else if (response.status === 400) {
                    const error = data.error
                    setError(error);
                } else {
                    alert("You really messed up to see me...")
                }
        } catch (error) {
            console.log(JSON.stringify(error))
        }
    }

    return (
        <>
            {error && <div className='error'>{error}</div>}
            <form onSubmit={onSubmit}>
                <label htmlFor="email">Email:</label>
                <input type="email" id="loginEmail" name="email" value={email} onChange={(event) => setEmail(event.target.value)}/>
                <br/>

                <label htmlFor="password">Password:</label>
                <input type="password" id="loginPassword" name="password" value={password} onChange={(event) => setPassword(event.target.value)}/>
                <br/>

                <button type="submit">Login</button>
            </form>
        </>
    )
}