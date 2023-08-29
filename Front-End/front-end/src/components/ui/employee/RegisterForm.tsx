import { ChangeEvent, useState } from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { states } from '../../../lib/States';
import { zipCodeData } from '../../../lib/ZipCodes';
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

export const RegisterForm = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmationPassword, setConfirmationPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [streetAddress, setStreetAddress] = useState('');
    const [city, setCity] = useState('');
    const [state, setState] = useState('');
    const [zipCode, setZipCode] = useState('');
    const [zipCodes, setZipCodes] = useState([] as string[]);
    const [loading, setLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);

    const fullAddress = `${streetAddress}, ${city}, ${state} ${zipCode}`;

    const navigate = useNavigate();

    const handleStateChange = (event: ChangeEvent<HTMLSelectElement>) => {
        const selectedStateCode = event.target.value;
        const selectedZipCodes = zipCodeData[selectedStateCode] || [];
        setState(selectedStateCode);
        setZipCodes(selectedZipCodes);
    }

    const handleZipCodeChange = (event: ChangeEvent<HTMLSelectElement>) => {
        setZipCode(event.target.value);
    }

    const handlePhoneNumberChange = (event: any) => {
        const phoneNumberValue = event.target.value;
        const formattedPhoneNumber = formatPhoneNumber(phoneNumberValue);
        setPhoneNumber(formattedPhoneNumber);
    };

    const formatPhoneNumber = (value: any) => {
        const phoneNumberDigits = value.replace(/\D/g, '');
        const parts = phoneNumberDigits.match(/^(\d{3})(\d{0,3})(\d{0,4})$/);
        if (parts) {
          const formattedPhoneNumber = `${parts[1]}${parts[2] ? '-' + parts[2] : ''}${parts[3] ? '-' + parts[3] : ''}`;
          return formattedPhoneNumber;
        }
        return value;
    };

    const onSubmit = async(event: any) => {
        event.preventDefault();
        setLoading(true);
        try {
            const response = await fetch ('http://localhost:8080/register/now', 
                {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({
                        'firstName': firstName,
                        'lastName': lastName,
                        'email': email,
                        'password': password,
                        'confirmationPassword': confirmationPassword,
                        'phoneNumber': phoneNumber,
                        'address': fullAddress
                    })
                }
            );

            const data = await response.json();

            if (response.status === 201) {
                navigate('/login');
                setLoading(false);
                toast.success("Employee successfully created, please log in!", {
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
                    <label className="form-label" htmlFor="registerFirstName">First Name: </label>
                    <input className="form-input" type="text"  id="registerFirstName" name="registerFirstName" value={firstName} onChange={(event: ChangeEvent<HTMLInputElement>) => setFirstName(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerLastName">Last Name: </label>
                    <input className="form-input" type="text"  id="registerLastName" name="registerLastName" value={lastName} onChange={(event: ChangeEvent<HTMLInputElement>) => setLastName(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerEmail">Email: </label>
                    <input className="form-input" type="email"  id="registerEmail" name="registerEmail" value={email} onChange={(event: ChangeEvent<HTMLInputElement>) => setEmail(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerPassword">Password: </label>
                    <input className="form-input" type="password"  id="registerPassword" name="registerPassword" value={password} onChange={(event: ChangeEvent<HTMLInputElement>) => setPassword(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerConfirmationPassword">Confirm Password: </label>
                    <input className="form-input" type="password"  id="registerConfirmationPassword" name="registerConfirmationPassword" value={confirmationPassword} onChange={(event: ChangeEvent<HTMLInputElement>) => setConfirmationPassword(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerPhoneNumber">Phone Number: </label>
                    <input className="form-input" type="text"  id="registerPhoneNumber" name="registerPhoneNumber" value={phoneNumber} onChange={handlePhoneNumberChange}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerStreetAddress">Street Address: </label>
                    <input className="form-input" type="text"  id="registerStreetAddress" name="registerStreetAddress" value={streetAddress} onChange={(event: ChangeEvent<HTMLInputElement>) => setStreetAddress(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerCity">City: </label>
                    <input className="form-input" type="text"  id="registerCity" name="registerCity" value={city} onChange={(event: ChangeEvent<HTMLInputElement>) => setCity(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerState">State: </label>
                    <select className="form-input" name="registerState" id="registerState" value={state} onChange={handleStateChange}>
                        {states.length > 0 && (
                            states.map(state => (
                                <option key={state.code} value={state.code}>{state.name}</option>
                            ))
                        )}
                    </select>
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerZipCode">Zip Code: </label>
                    <select className="form-input" name="registerZipCode" id="registerZipCode" value={zipCode} onChange={handleZipCodeChange}>
                        {zipCodes.length > 0 && (
                            zipCodes.map((zipCode, index) => (
                                <option key={index} value={zipCode}>{zipCode}</option>
                            ))
                        )}
                    </select>
                </div>

                <button className="form-btn-1" type="submit" id="registerButton">"Register"</button>
            </form>
            )}
        </>
    );
}