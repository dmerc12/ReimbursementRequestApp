'use client'

import { useState, useEffect } from 'react';
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import Modal from '@/components/Model';
import Cookies from 'js-cookie';

export default function UpdateEmployeeForm({ employee }) {
    const addressComponents = employee.address.split(', ');
    const initialStreetAddress = addressComponents[0];
    const initialCity = addressComponents[1];
    const initialState = addressComponents[2].split(' ')[0];
    const initialZipCode = addressComponents[2].split(' ')[1];

    const [firstName, setFirstName] = useState(employee.firstName);
    const [lastName, setLastName] = useState(employee.lastName);
    const [email, setEmail] = useState(employee.email);
    const [phoneNumber, setPhoneNumber] = useState(employee.phoneNumber);
    const [streetAddress, setStreetAddress] = useState(initialStreetAddress);
    const [city, setCity] = useState(initialCity);
    const [state, setState] = useState(initialState);
    const [zipCode, setZipCode] = useState(initialZipCode);
    const [states, setStates] = useState([]);
    const [zipCodes, setZipCodes] = useState([]);
    const [visible, setVisible] = useState(false);

    const router = useRouter();

    const fetchStates = async () => {
        try {
            const response = await fetch('/api/states');
            const data = await response.json();
            setStates(data.states);
        } catch (error) {
            throw new Error(`${error.message}`);
        }
    };

    const fetchZipCodes = async (state) => {
        try {
            const response = await fetch(`/api/zipcodes?state=${state}`);
            const data = await response.json();
            setZipCodes(data.zipCodes);
        } catch (error) {
            throw new Error(`${error.message}`);
        }
    };

    const handleStateChange = (event) => {
        setState(event.target.value);
        fetchZipCodes(event.target.value);
    };

    const handleZipCodeChange = (event) => {
        setZipCode(event.target.value);

    }

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
        try {
            const fullAddress = `${streetAddress}, ${city}, ${state} ${zipCode}`;

            const sessionIdCookie = Cookies.get('sessionId');
            const response = await fetch('/api/employee/handleUpdate', 
                {
                    method: 'PUT',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({
                        'sessionId': sessionIdCookie,
                        'firstName': firstName,
                        'lastName': lastName,
                        'email': email,
                        'phoneNumber': phoneNumber,
                        'address': fullAddress
                    })
                }
            );
            const data = await response.json();

            if (data.success) {
                router.push('/manage-information');
                setVisible(false);
                toast.success("Information Successfully Updated!", {
                    toastId: "customId"
                  });
            } else if (data.error.message) {
                throw new Error(`${data.error.message}`);
            } else if (data.error) {
                throw new Error(`${data.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again!");
            }
        } catch (error) {
            if (error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                router.push('/login');
                setVisible(false)
                toast.warn(error.message, {
                    toastId: 'customId'
                });
            } else {
                toast.error(error.message, {
                    toastId: "customId"
                  }); 
            }
        }
    }

    return (
        <>
            <div className='update-employee-component'>
                <button onClick={() => setVisible(true)} className='update-employee-btn' id='updateInformationModal'>Update Information</button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
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
                        <input className='form-input' type="text" id='updatePhoneNumber' name='phoneNumber' value={phoneNumber} onChange={handlePhoneNumberChange}/>
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor="streetAddress">Street Address: </label>
                        <input className='form-input' type="text" id='updateStreetAddress' name='streetAddress' value={streetAddress} onChange={event => setStreetAddress(event.target.value)}/>
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor='city'>City: </label>
                        <input className='form-input' type='text' id='updateCity' name='city' value={city} onChange={(event) => setCity(event.target.value)} />
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor='state'>State: </label>
                        <select className='form-input' id='updateState' name='state' value={state} onChange={handleStateChange}>
                            {states.length > 0 && (
                                (() => {
                                    const options = [];
                                    for (let i = 0; i < states.length; i++) {
                                        const state = states[i];
                                        options.push(<option key={state.code} value={state.code}>{state.name}</option>);
                                    }
                                    return options;
                                })
                            )}
                        </select>
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor='zipCode'>Zip Code: </label>
                        <select className='form-input' id='updateZipCode' name='zipCode' value={zipCode} onChange={handleZipCodeChange}>
                            {zipCodes.length > 0 && (
                                (() => {
                                    const options = [];
                                    for (let i = 0; i < zipCodes.length; i++) {
                                        const zipCode = zipCodes[i];
                                        options.push(<option key={i} value={zipCode}>{zipCode}</option>);
                                    }
                                    return options;
                                })
                            )}
                        </select>
                    </div>

                    <button className='form-btn-2' type='submit' id='updateCurrentInformationButton'>Update Current Information</button>
                </form>
            </Modal>
        </>
    )
}
