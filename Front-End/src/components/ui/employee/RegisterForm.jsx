'use client'

import axios from '../../../pages/api/axios'
import { useState } from 'react';

export default function Registser() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [address, setAddress] = useState('');

    const REGISTER_URL = 'http://localhost:8080/register/now'


    const onSubmit = async (event) => {
        event.preventDefault();
        try{
            const response = await axios.post(REGISTER_URL, 
                JSON.stringify({ 
                    'firstName': firstName, 
                    'lastName': lastName, 
                    'email': email, 
                    'password': password, 
                    'phoneNumber': phoneNumber, 
                    'address': address
                }), {
                    headers: {'Content-Type': 'application/json'}
                })
                console.log(JSON.stringify(response))
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
