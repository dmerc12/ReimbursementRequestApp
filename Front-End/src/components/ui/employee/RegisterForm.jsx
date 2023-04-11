'use client'

import { useState } from 'react';

export default function Registser() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [address, setAddress] = useState('');

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
            console.log(JSON.stringify(data))
        } catch (error) {
            console.log(JSON.stringify(error))
        }
        
    }

    return (
        <>
            <h1>Register below:</h1>
            <form onSubmit={onSubmit}>
            <label htmlFor="firstName">First Name</label>
                <input type="text" id='firstName' name='firstName' value={firstName} onChange={(event) => setFirstName(event.target.value)}/><br/>
                <label htmlFor="lastName">Last Name</label>
                <input type="text" id='lastName' name='lastName' value={lastName} onChange={(event) => setLastName(event.target.value)}/><br/>
                <label htmlFor="email">Email</label>
                <input type="email" id='email' name='email' value={email} onChange={(event) => setEmail(event.target.value)}/><br/>
                <label htmlFor="password">Password</label>
                <input type="password" id='password' name='password' value={password} onChange={(event) => setPassword(event.target.value)}/><br/>
                <label htmlFor="phoneNumber">Phone Number</label>
                <input type="text" id='phoneNumber' name='phoneNumber' value={phoneNumber} onChange={(event) => setPhoneNumber(event.target.value)}/><br/>
                <label htmlFor="address">Address</label>
                <input type="text" id='address' name='address' value={address} onChange={(event) => setAddress(event.target.value)}/><br/>
                <button type="submit">Register</button>
            </form>
        </>
    )
}
