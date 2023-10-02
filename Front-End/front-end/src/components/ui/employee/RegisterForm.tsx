import { ChangeEvent, useState } from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { states } from '../../../lib/States';
import { zipCodeData } from '../../../lib/ZipCodes';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';
import { useFetch } from "../../../hooks/useFetch";

export const RegisterForm = () => {
    const [registerForm, setRegisterForm] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmationPassword: '',
        phoneNumber: '',
        address: ''
    });
    const [address, setAddress] = useState({
        streetAddress: '',
        city: '',
        state: '',
        zipCode: ''
    });
    const [zipCodes, setZipCodes] = useState([] as string[]);
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const { fetchData } = useFetch();

    const navigate = useNavigate();

    const onChange = (event: any) => {
        const { name, value } = event.target;
        
        if (name === 'phoneNumber') {
            const phoneNumberDigits = value.replace(/\D/g, '');
            const parts = phoneNumberDigits.match(/^(\d{3})(\d{0,3})(\d{0,4})$/);
            if (parts) {
                const formattedPhoneNumber = `${parts[1]}${parts[2] ? '-' + parts[2] : ''}${parts[3] ? '-' + parts[3] : ''}`;
                setRegisterForm((prevForm) => ({
                    ...prevForm,
                    phoneNumber: formattedPhoneNumber
                }));
            } else {
                setRegisterForm((prevForm) => ({
                    ...prevForm,
                    phoneNumber: value
                }));
            }
        } else if (name === 'state') {
            const selectedStateCode = value;
            const selectedZipCodes = zipCodeData[selectedStateCode] || [];
            setAddress((prevAddress) => ({
                ...prevAddress,
                state: selectedStateCode,
                zipCode: ''
            }));
            const fullAddress = `${address.streetAddress}, ${address.city}, ${selectedStateCode} ${address.zipCode}`;
            setRegisterForm((prevForm) => ({
                ...prevForm,
                address: fullAddress
            }));
            setZipCodes(selectedZipCodes);
        } else if (name === 'zipCode') {
            setAddress((prevAddress) => ({
                ...prevAddress,
                zipCode: value
            }));
            const fullAddress = `${address.streetAddress}, ${address.city}, ${address.state} ${value}`;
            setRegisterForm((prevForm) => ({
                ...prevForm,
                address: fullAddress
            }));
        } else if (name === 'streetAddress' || name === 'city') {
            setAddress((prevAddress) => ({
                ...prevAddress,
                [name]: value
            }));
            const fullAddress = `${address.streetAddress}, ${address.city}, ${address.state} ${address.zipCode}`;
            setRegisterForm((prevForm) => ({
                ...prevForm,
                address: fullAddress
            }));
        } else {
            setRegisterForm((prevForm) => ({
                ...prevForm,
                [name]: value
            }));
        }
    };

    const onSubmit = async(event: any) => {
        event.preventDefault();
        setLoading(true);
        setFailedToFetch(false);
        try {
            const { responseStatus, data } = await fetchData('/register/now', 'POST', registerForm)

            if (responseStatus === 201) {
                navigate('/login');
                setLoading(false);
                toast.success("Employee successfully created, please log in!", {
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
    };

    const goBack = () => {
        setFailedToFetch(false);
    }

    return (
        <>
            {loading ? (
                <div className="loading-indicator">
                    <FaSpinner className="spinner" />
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
                <form className="form" onSubmit={onSubmit}>
                <div className="form-field">
                    <label className="form-label" htmlFor="firstName">First Name: </label>
                    <input className="form-input" type="text"  id="registerFirstName" name="firstName" value={registerForm.firstName} onChange={onChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="lastName">Last Name: </label>
                    <input className="form-input" type="text"  id="registerLastName" name="lastName" value={registerForm.lastName} onChange={onChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="email">Email: </label>
                    <input className="form-input" type="email"  id="registerEmail" name="email" value={registerForm.email} onChange={onChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="password">Password: </label>
                    <input className="form-input" type="password"  id="registerPassword" name="password" value={registerForm.password} onChange={onChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="confirmationPassword">Confirm Password: </label>
                    <input className="form-input" type="password"  id="registerConfirmationPassword" name="confirmationPassword" value={registerForm.confirmationPassword} onChange={onChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="phoneNumber">Phone Number: </label>
                    <input className="form-input" type="text"  id="registerPhoneNumber" name="phoneNumber" value={registerForm.phoneNumber} onChange={onChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="streetAddress">Street Address: </label>
                    <input className="form-input" type="text"  id="registerStreetAddress" name="streetAddress" value={address.streetAddress} onChange={onChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="city">City: </label>
                    <input className="form-input" type="text"  id="registerCity" name="city" value={address.city} onChange={onChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="state">State: </label>
                    <select className="form-input" name="state" id="registerState" value={address.state} onChange={onChange}>
                        {states.length > 0 && (
                            states.map(state => (
                                <option key={state.code} value={state.code}>{state.name}</option>
                            ))
                        )}
                    </select>
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="zipCode">Zip Code: </label>
                    <select className="form-input" name="zipCode" id="registerZipCode" value={address.zipCode} onChange={onChange}>
                        {zipCodes.length > 0 && (
                            zipCodes.map((zipCode, index) => (
                                <option key={index} value={zipCode}>{zipCode}</option>
                            ))
                        )}
                    </select>
                </div>

                <button className="form-btn-1" type="submit" id="registerButton">Register</button>
            </form>
            )}
        </>
    );
}