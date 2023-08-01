import { useState } from 'react';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';

export const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/login/now', 
            {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'email': email,
                    'password': password
                })
            });
            const data = await response.json();

            if (response.status === 200) {
                Cookies.set('sessionId', data.sessionId);
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end of the application, please try again!");
            }
        } catch (error: any) {
            toast.error(error.message);
        }
    }

    return (
        <>
            <form className='form' onSubmit={onSubmit}>
                <div>
                    <label htmlFor="loginEmail">Email: </label>
                    <input type="email" id='loginEmail' name='loginEmail' value={email} onChange={(event) => setEmail(event.target.value)} />
                </div>

                <div>
                    <label htmlFor="loginPassword">Password: </label>
                    <input type="password" id='loginPassword' name='loginPassword' value={password} onChange={(event) => setPassword(event.target.value)} />
                </div>

                <button type='submit'>Login</button>
            </form>
        </>
    );
}
