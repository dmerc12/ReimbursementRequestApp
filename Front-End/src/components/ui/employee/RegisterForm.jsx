'use client'

import { useState } from 'react';
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export default function RegistserForm () {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [address, setAddress] = useState('');

    const router = useRouter();

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
                        'phoneNumber': phoneNumber, 
                        'address': address
                    })
                }
            )
            const data = await response.json();
            
            if (data.success) {
                router.push('/');
                toast.success("Employee successfully created, please log in!");
            } else if (data.error.message) {
                throw new Error(`${data.error.message}`);
            } else if (data.error) {
                throw new Error(`${data.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again later!")
            }
        } catch (error) {
            toast.error(error.message);
        }
        
    }

    return (
        <>
            <form onSubmit={onSubmit}>
                <label htmlFor="firstName">First Name</label>
                <input type="text" id='registerFirstName' name='firstName' value={firstName} onChange={(event) => setFirstName(event.target.value)}/>
                <br/>

                <label htmlFor="lastName">Last Name</label>
                <input type="text" id='registerLastName' name='lastName' value={lastName} onChange={(event) => setLastName(event.target.value)}/>
                <br/>
                
                <label htmlFor="email">Email</label>
                <input type="email" id='registerEmail' name='email' value={email} onChange={(event) => setEmail(event.target.value)}/>
                <br/>
                
                <label htmlFor="password">Password</label>
                <input type="password" id='registerPassword' name='password' value={password} onChange={(event) => setPassword(event.target.value)}/>
                <br/>
                
                <label htmlFor="phoneNumber">Phone Number</label>
                <input type="text" id='registerPhoneNumber' name='phoneNumber' value={phoneNumber} onChange={(event) => setPhoneNumber(event.target.value)}/>
                <br/>
                
                <label htmlFor="address">Address</label>
                <input type="text" id='registerAddress' name='address' value={address} onChange={(event) => setAddress(event.target.value)}/>
                <br/>
                
                <button type="submit">Register</button>
            </form>
        </>
    )
}
