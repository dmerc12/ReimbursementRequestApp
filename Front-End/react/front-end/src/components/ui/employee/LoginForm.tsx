import { useState } from 'react';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';

export const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isloading, setIsLoading] = useState(false);

    const navigate = useNavigate();

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setIsLoading(true);
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
                navigate('/home');
                setIsLoading(false);
                toast.success("Welcome!", {
                    toastId: 'customId'
                });
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end of the application, please try again!");
            }
        } catch (error: any) {
            toast.error(error.message, {
                toastId: 'customId'
            });
        }
    }

    return (
        <>
            <form className='form' onSubmit={onSubmit}>
                <div className='form-field'>
                    <label className='form-label' htmlFor="loginEmail">Email: </label>
                    <input className='form-input' type="email" id='loginEmail' name='loginEmail' value={email} onChange={(event) => setEmail(event.target.value)} />
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor="loginPassword">Password: </label>
                    <input className='form-input' type="password" id='loginPassword' name='loginPassword' value={password} onChange={(event) => setPassword(event.target.value)} />
                </div>

                <button disabled={isloading} className='form-btn-1' type='submit'>{isloading ? "Loading.." : "Login"}</button>
            </form>
        </>
    );
}
