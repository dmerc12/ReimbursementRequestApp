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
    const [address, setAddress] = useState('');

    const router = useRouter();

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
    }, [phoneNumber]);

    const onSubmit = async (event) => {
        event.preventDefault();
        try{
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
                        'address': address
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
                    <label className='form-label' htmlFor="address">Address: </label>
                    <input className='form-input' type="text" id='registerAddress' name='address' value={address} onChange={(event) => setAddress(event.target.value)}/>
                    <br/>
                </div>
                
                <button className='form-btn-1' type="submit">Register</button>
            </form>
        </>
    )
}
