import React, { ChangeEvent, useState } from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { states } from '../../../lib/States';
import { zipCodeData } from '../../../lib/ZipCodes';

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
    const [isLoading, setIsLoading] = useState(false);

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

    const onSubmit = async(event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault;
        setIsLoading(true);
        try {
            const fullAddress = `${streetAddress}, ${city}, ${state} ${zipCode}`;

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
                setIsLoading(false);
                toast.success("Employee successfully created, please log in!", {
                    toastId: 'customId'
                });
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end of the application, please try again!");
            }
        } catch (error: any) {
            setIsLoading(false);
            toast.error(error.message);
        }
    }

    return (
        <>
            <form className="form" onSubmit={onSubmit}>
                <div className="form-field">
                    <label className="form-label" htmlFor="registerFirstName">First Name: </label>
                    <input className="form-input" type="text"  id="registerFirstName" name="registerFirstName" value={firstName} onChange={(event: ChangeEvent<HTMLInputElement>) => setFirstName(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerLasttName">Last Name: </label>
                    <input className="form-input" type="text"  id="registerLasttName" name="registerLasttName" value={lastName} onChange={(event: ChangeEvent<HTMLInputElement>) => setLastName(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerEmail">Email: </label>
                    <input className="form-input" type="text"  id="registerEmail" name="registerEmail" value={email} onChange={(event: ChangeEvent<HTMLInputElement>) => setEmail(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerPassword">Password: </label>
                    <input className="form-input" type="text"  id="registerPassword" name="registerPassword" value={password} onChange={(event: ChangeEvent<HTMLInputElement>) => setPassword(event.target.value)}/>
                    <br />
                </div>

                <div className="form-field">
                    <label className="form-label" htmlFor="registerConfirmationPassword">Confirm Password: </label>
                    <input className="form-input" type="text"  id="registerConfirmationPassword" name="registerConfirmationPassword" value={confirmationPassword} onChange={(event: ChangeEvent<HTMLInputElement>) => setConfirmationPassword(event.target.value)}/>
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
                        
                    </select>
                </div>
            </form>
        </>
    );
}