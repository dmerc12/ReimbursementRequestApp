'use client'

import { useState } from 'react';

export default function LoginForm () {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


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
                console.log(JSON.stringify(data))
        } catch (error) {
            console.log(JSON.stringify(error))
        }
    }

    return (
        <>
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