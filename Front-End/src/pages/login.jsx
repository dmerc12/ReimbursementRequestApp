'use client'

import { useState } from 'react'
import { signIn } from 'next-auth/react'

export default function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    
    const onSubmit = async (event) => {
        event.preventDefault();
        const response = await signIn('credentials', { email, password, callbackUrl: '/' });
    };

    return (
        <>
            <h1>Login</h1>
            <form onSubmit={onSubmit}>
                <label htmlFor="email">Email</label>
                <input
                type="email"
                id="loginEmail"
                name="email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                />
                <label htmlFor="password">Password</label>
                <input
                type="password"
                id="loginPassword"
                name="password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                />
                <button type="submit">Sign in</button>
            </form>
        </>
    )
}