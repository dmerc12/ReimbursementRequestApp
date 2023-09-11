import { useState } from 'react';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';
import { useFetch } from '../../../hooks/useFetch';

export const LoginForm = () => {
    const [loginForm, setLoginForm] = useState({
        email: '',
        password: ''
    });
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const { fetchData } = useFetch();

    const navigate = useNavigate();

    const onSubmit = async (event: any) => {
        event.preventDefault();
        setFailedToFetch(false);
        setLoading(true);
        try {
            const { responseStatus, data } = await fetchData('/login/now', 'POST', loginForm)

            if (responseStatus === 200) {
                Cookies.set('sessionId', data.sessionId);
                navigate('/home');
                setLoading(false);
                toast.success("Welcome!", {
                    toastId: 'customId'
                });
            } else if (responseStatus === 400) {
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

    const onChange = (event: any) => {
        const { name, value } = event.target;
        setLoginForm((prevLoginForm) => ({
            ...prevLoginForm,
            [name]: value
        }));
    };

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
                        <label className='form-label' htmlFor="email">Email: </label>
                        <input className='form-input' type="email" id='loginEmail' name='email' value={loginForm.email} onChange={onChange} />
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor="password">Password: </label>
                        <input className='form-input' type="password" id='loginPassword' name='password' value={loginForm.password} onChange={onChange} />
                    </div>

                    <button className='form-btn-1' type='submit' id='loginButton'>Login</button>
                </form>
            )}
        </>
    );
}
