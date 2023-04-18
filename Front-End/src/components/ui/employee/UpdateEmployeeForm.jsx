'use client'

import { useState } from 'react';
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export default function UpdateEmployeeForm({ employee, sessionId }) {
    const [firstName, setFirstName] = useState(employee.firstName);
    const [lastName, setLastName] = useState(employee.lastName);
    const [email, setEmail] = useState(employee.email);
    const [phoneNumber, setPhoneNumber] = useState(employee.phoneNumber);
    const [address, setAddress] = useState(employee.address);

    const router = useRouter();

    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('/api/employee/handleUpdate', 
                {
                    method: 'PUT',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({
                        'sessionId': sessionId,
                        'firstName': firstName,
                        'lastName': lastName,
                        'email': email,
                        'phoneNumber': phoneNumber,
                        'address': address
                    })
                }
            )
            const data = await response.json();

            if (data.success) {
                router.push('/manage-information');
                toast.success("Information Successfully Updated!");
            } else if (data.error.message) {
                throw new Error(`${data.error.message}`);
            } else if (data.error) {
                throw new Error(`${data.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again!")
            }
        } catch (error) {
            toast.warn(error.message);
        }
    }

    return (
        <>
            <form className='form' onSubmit={onSubmit}>
                <div className='form-field'>
                    <label className='form-label' htmlFor="firstName">First Name: </label>
                    <input className='form-input' type="text" id='updateFirstName' name='firstName' value={firstName} onChange={event => setFirstName(event.target.value)}/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor="lastName">Last Name: </label>
                    <input className='form-input' type="text" id='updateLastName' name='lastName' value={lastName} onChange={event => setLastName(event.target.value)}/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor="email">Email: </label>
                    <input className='form-input' type="text" id='updateEmail' name='email' value={email} onChange={event => setEmail(event.target.value)}/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor="phoneNumber">Phone Number: </label>
                    <input className='form-input' type="text" id='updatePhoneNumber' name='phoneNumber' value={phoneNumber} onChange={event => setPhoneNumber(event.target.value)}/>
                </div>

                <div className='form-field'>
                    <label className='form-label' htmlFor="address">Address: </label>
                    <input className='form-input' type="text" id='updateAddress' name='address' value={address} onChange={event => setAddress(event.target.value)}/>
                </div>

                <button className='update-info-form-button' type='submit'>Update Current Information</button>
            </form>
        </>
    )
}
