'use client'

import { useState, useEffect } from 'react';
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export default function RegistserForm () {
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
    const [states, setStates] = useState([]);
    const [zipCodes, setZipCodes] = useState([]);

    const router = useRouter();

    const fetchStates = async () => {
        try {
            const response = await fetch('/api/states');
            const data = await response.json();
            setStates(data.states);
        } catch (error) {
            throw new Error(`${error.message}`)
        }
    };

    const fetchZipCodes = async (state) => {
        try {
            const response = await fetch(`/api/zipcodes?state=${state}`);
            const data = await response.json();
            setZipCodes(data.zipCodes);
        } catch (error) {
            throw new Error(`${error.message}`)
        }
    };

    const handleStateChange = (event) => {
        setState(event.target.value);
        //fetchZipCodes(event.target.value);
    };

    const handleZipCodeChange = (event) => {
        setZipCode(event.target.value)
    };

    const handlePhoneNumberChange = (event) => {
        const phoneNumberValue = event.target.value;
        const formattedPhoneNumber = formatPhoneNumber(phoneNumberValue);
        setPhoneNumber(formattedPhoneNumber);
    };
    
    const formatPhoneNumber = (value) => {
        const phoneNumberDigits = value.replace(/\D/g, '');
        const parts = phoneNumberDigits.match(/^(\d{3})(\d{0,3})(\d{0,4})$/);
        if (parts) {
          const formattedPhoneNumber = `${parts[1]}${parts[2] ? '-' + parts[2] : ''}${parts[3] ? '-' + parts[3] : ''}`;
          return formattedPhoneNumber;
        }
        return value;
    };

    useEffect(() => {
        const formattedPhoneNumber = formatPhoneNumber(phoneNumber);
        setPhoneNumber(formattedPhoneNumber);
        fetchStates();
    }, [phoneNumber]);

    const onSubmit = async (event) => {
        event.preventDefault();
        try{
            const fullAddress = `${streetAddress}, ${city}, ${state} ${zipCode}`

            const response = await fetch('/api/employee/handleRegister',
                {
                    method: "POST",
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
            )
            const data = await response.json();
            
            if (data.success) {
                router.push('/');
                toast.success("Employee successfully created, please log in!", {
                    toastId: "customId"
                  });
            } else if (data.error.message) {
                throw new Error(`${data.error.message}`);
            } else if (data.error) {
                throw new Error(`${data.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again later!")
            }
        } catch (error) {
            toast.warn(error.message, {
                toastId: "customId"
              });
        }
        
    }

    return (
        <>
            <form className='form' onSubmit={onSubmit}>
                <div className='form-field'>
                    <label className='form-label' htmlFor="firstName">First Name: </label>
                    <input className='form-input' type="text" id='registerFirstName' name='firstName' value={firstName} onChange={(event) => setFirstName(event.target.value)}/>
                    <br/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor="lastName">Last Name: </label>
                    <input className='form-input' type="text" id='registerLastName' name='lastName' value={lastName} onChange={(event) => setLastName(event.target.value)}/>
                    <br/>
                </div>
                
                <div className='form-field'>
                    <label className='form-label' htmlFor="email">Email: </label>
                    <input className='form-input' type="email" id='registerEmail' name='email' value={email} onChange={(event) => setEmail(event.target.value)}/>
                    <br/>
                </div>
                
                <div className='form-field'>
                    <label className='form-label' htmlFor="password">Password: </label>
                    <input className='form-input' type="password" id='registerPassword' name='password' value={password} onChange={(event) => setPassword(event.target.value)}/>
                    <br/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor='confirmPassword'>Confirm Password: </label>
                    <input className='form-input' type='password' id='registerConfirmPassword' name='confirmPassword' value={confirmationPassword} onChange={(event) => setConfirmationPassword(event.target.value)}/>
                </div>
                
                <div className='form-field'>
                    <label className='form-label' htmlFor="phoneNumber">Phone Number: </label>
                    <input className='form-input' type="text" id='registerPhoneNumber' name='phoneNumber' value={phoneNumber} onChange={handlePhoneNumberChange}/>
                    <br/>
                </div>
                
                <div className='form-field'>
                    <label className='form-label' htmlFor="streetAddress">Street Address: </label>
                    <input className='form-input' type="text" id='registerStreetAddress' name='streetAddress' value={streetAddress} onChange={(event) => setStreetAddress(event.target.value)}/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor='city'>City: </label>
                    <input className='form-input' type="text" id='registerCity' name='city' value={city} onChange={(event) => setCity(event.target.value)}/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor='state'>State: </label>
                    <select className='form-input' id='registerState' name='state' value={state} onChange={handleStateChange}>
                        {states.length > 0 && (
                        (() => {
                            const options = [];
                            for (let i = 0; i < states.length; i++) {
                            const state = states[i];
                            options.push(<option key={state.code} value={state.code}>{state.name}</option>);
                            }
                            return options;
                        })()
                        )}
                    </select>
</div>


                <div className='form-field'>
                    <label className='form-label' htmlFor='zipCode'>Zip Code: </label>
                    <select className='form-input' id='registerZipCode' name='zipCode' value={zipCode} onChange={handleZipCodeChange} />
                </div>
                
                <button className='form-btn-1' type="submit">Register</button>
            </form>
        </>
    )
}
