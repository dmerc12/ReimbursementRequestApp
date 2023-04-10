'use client'

import { useState } from 'react'

export default function Register() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] =  useState('');
    const [password, setPassword] =  useState('');
    const [phoneNumber, setPhoneNumber] =  useState('');
    const [address, setAddress] =  useState('');


    const onSubmit = async (event) => {
        event.preventDefault();
        const response = await fetch('http://localhost:8080/register/now', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            'firstName': firstName,
            'lastName': lastName,
            'email': email,
            'password': password,
            'phoneNumber': phoneNumber,
            'address': address
        })})
        if (response.status === 200) {
            return console.log(response.body)
        } else if (response.status === 400) {
            throw Error(response.body)
        } else {
            alert('You really messed up to see me...')
        }
    }

    return (
        <>
            <h1>Register</h1>
            <form onSubmit={onSubmit}>
                <label htmlFor="firstName">First Name</label>
                <input type="text" id='registerFirstName' name='firstName' value={firstName} onChange={(event) => setFirstName(event.target.value)}/>
                <label htmlFor="lastName">Last Name</label>
                <input type="text" id='registerLastName' name='lastName' value={lastName} onChange={(event) => setLastName(event.target.value)}/>
                <label htmlFor="email">Email</label>
                <input type="email" id='registerEmail' name='email' value={email} onChange={(event) => setEmail(event.target.value)}/>
                <label htmlFor="password">Password</label>
                <input type="password" id='registerPassword' name='password' value={password} onChange={(event) => setPassword(event.target.value)}/>
                <label htmlFor="phoneNumber">Phone Number</label>
                <input type="text" id='registerPhoneNumber' name='phoneNumber' value={phoneNumber} onChange={(event) => setPhoneNumber(event.target.value)}/>
                <label htmlFor="address">Address</label>
                <input type="text" id='registerAddress' name='address' value={address} onChange={(event) => setAddress(event.target.value)}/>
                <button type="submit">Register</button>
            </form>
        </>
    )
}