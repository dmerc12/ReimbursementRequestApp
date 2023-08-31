import { useState } from 'react';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

export const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const navigate = useNavigate();

    const onSubmit = async (event: any) => {
        event.preventDefault();
        setFailedToFetch(false);
        setLoading(true);
        try {
            const response = await fetch('http://localhost:8080/login/now', {
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
                setLoading(false);
                toast.success("Welcome!", {
                    toastId: 'customId'
                });
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end of the application, please try again!");
            }
        } catch (error: any) {
            if (error.message === "Failed to fetch") {
                setFailedToFetch(true);
                setLoading(false);
            } else {
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        }
    }

    const goBack = () => {
        setFailedToFetch(false);
    }

    return (
        <>
            {loading ? (
                <div className='loading-indictor'>
                    <FaSpinner className='spinner' />
                </div>
            ) : failedToFetch ? (
                <div className='failed-to-fetch'>
                    <AiOutlineExclamationCircle className='warning-icon'/>
                    <p>Cannot connect to the back end server.</p>
                    <p>Please check your internet connection and try again.</p>
                    <button className='retry-button' onClick={onSubmit}>
                        <FaSync className='retry-icon'/> Retry
                    </button>
                    <button className='back-button' onClick={goBack}>Go Back</button>
                </div>
            ) : (
                <form className='form' onSubmit={onSubmit}>
                    <div className='form-field'>
                        <label className='form-label' htmlFor="loginEmail">Email: </label>
                        <input className='form-input' type="email" id='loginEmail' name='loginEmail' value={email} onChange={(event) => setEmail(event.target.value)} />
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor="loginPassword">Password: </label>
                        <input className='form-input' type="password" id='loginPassword' name='loginPassword' value={password} onChange={(event) => setPassword(event.target.value)} />
                    </div>

                    <button className='form-btn-1' type='submit' id='loginButton'>Login</button>
                </form>
            )}
        </>
    );
}
