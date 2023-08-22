import { Modal } from "../../Modal";
import { toast } from 'react-toastify';
import { useState, useEffect, ChangeEvent } from "react";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";
import { states } from "../../../lib/States";
import { zipCodeData } from "../../../lib/ZipCodes";
import { FaSpinner, FaSync } from 'react-icons/fa';
import { AiOutlineExclamationCircle } from 'react-icons/ai';

interface Employee {
    firstName: string,
    lastName: string,
    email: string,
    phoneNumber: string,
    address: string
}

export const UpdateForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [dataIsLoading, setDataIsLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);
    const [visible, setVisible] = useState(false);
    const [employee, setEmployee] = useState(false);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [streetAddress, setStreetAddress] = useState('');
    const [city, setCity] = useState('');
    const [state, setState] = useState('');
    const [zipCode, setZipCode] = useState('');
    const [zipCodes, setZipCodes] = useState([] as string[])

    const navigate = useNavigate();

    const handleStateChange = (event: ChangeEvent<HTMLSelectElement>) => {
        const selectedStateCode = event.target.value;
        const selectedZipCodes = zipCodeData[selectedStateCode] || [];
        setState(selectedStateCode);
        setZipCodes(selectedZipCodes);
    };

    const handleZipCodeChange = (event: ChangeEvent<HTMLSelectElement>) => {
        setZipCode(event.target.value);
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

    const handlePhoneNumberChange = (event: any) => {
        const phoneNumberValue = event.target.value;
        const formattedPhoneNumber = formatPhoneNumber(phoneNumberValue);
        setPhoneNumber(formattedPhoneNumber);
    };

    const onSubmit = async (event: any) => {
        event.preventDefault();
        setIsLoading(true);
        try {
            const fullAddress = `${streetAddress}, ${city}, ${state} ${zipCode}`;
            const sessionId = Cookies.get('sessionId');

            const response = await fetch('http://localhost:8080/update/employee/now', {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'firstName': firstName,
                    'lastName': lastName,
                    'email': email,
                    'phoneNumber': phoneNumber,
                    'address': fullAddress
                })
            });
            const data = await response.json();

            if (response.status === 200) {
                navigate('/manage-information');
                setVisible(false);
                setIsLoading(false);
                toast.success("Information successfully updated!", {
                    toastId: 'customId'
                });
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end, please try again!");
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetch(true);
                setIsLoading(false);
            } else {
                setIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        }
    };

    const goBack = () => {
        if (employee) {
            setFailedToFetch(false);
        } else {
            setFailedToFetch(false);
            setVisible(false);
        }
    }

    const fetchEmployee = async() => {
        try {
            setDataIsLoading(true);
            setFailedToFetch(false);

            const sessionId = Cookies.get('sessionId');

            const response = await fetch('http://localhost:8080/get/employee', {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({'sessionId': sessionId})
            });
            const data = await response.json();
            if (response.status === 200) {
                const employee: Employee = data
                setFirstName(employee.firstName);
                setLastName(employee.lastName);
                setEmail(employee.email);
                setPhoneNumber(employee.phoneNumber);
                const addressComponents = employee.address.split(', ');
                setStreetAddress(addressComponents[0]);
                setCity(addressComponents[1]);
                const initialState = addressComponents[2].split(' ')[0];
                setState(initialState);
                const initialZipCodes = zipCodeData[initialState];
                setZipCodes(initialZipCodes);
                const initialZipCode = addressComponents[2].split(' ')[1];
                setZipCode(initialZipCode);
                setEmployee(true);
                setDataIsLoading(false);
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end of the application, please try again!");
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setDataIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetch(true);
                setDataIsLoading(false);
            } else {
                setDataIsLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            }
        }
    }

    useEffect(() => {
        fetchEmployee();
    }, []);

    return (
        <>
            <div className="component">
                <button onClick={() => {setVisible(true); setFailedToFetch(false)}} className="action-btn" id="updateInformationModal">Update Information</button>
            </div>

            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetch(false)}}>
                {dataIsLoading ? ( 
                    <div className='loading-indicator'>
                        <FaSpinner className='spinner' />
                    </div>
                ) : isLoading ? (
                    <div className='loading-indicator'>
                        <FaSpinner className='spinner' />
                    </div>
                ) : failedToFetch ? (
                    <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={fetchEmployee}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
                ) : employee ? (
                    <form className="form" onSubmit={onSubmit}>
                        <div className="form-field">
                            <label className="form-label" htmlFor="updateFirstName">First Name: </label>
                            <input className="form-input" type="text"  id="updateFirstName" name="updateFirstName" value={firstName} onChange={(event: ChangeEvent<HTMLInputElement>) => setFirstName(event.target.value)}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateLasttName">Last Name: </label>
                            <input className="form-input" type="text"  id="updateLasttName" name="updateLasttName" value={lastName} onChange={(event: ChangeEvent<HTMLInputElement>) => setLastName(event.target.value)}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateEmail">Email: </label>
                            <input className="form-input" type="email"  id="updateEmail" name="updateEmail" value={email} onChange={(event: ChangeEvent<HTMLInputElement>) => setEmail(event.target.value)}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updatePhoneNumber">Phone Number: </label>
                            <input className="form-input" type="text"  id="updatePhoneNumber" name="updatePhoneNumber" value={phoneNumber} onChange={handlePhoneNumberChange}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateStreetAddress">Street Address: </label>
                            <input className="form-input" type="text"  id="updateStreetAddress" name="updateStreetAddress" value={streetAddress} onChange={(event: ChangeEvent<HTMLInputElement>) => setStreetAddress(event.target.value)}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateCity">City: </label>
                            <input className="form-input" type="text"  id="updateCity" name="updateCity" value={city} onChange={(event: ChangeEvent<HTMLInputElement>) => setCity(event.target.value)}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateState">State: </label>
                            <select className="form-input" name="updateState" id="updateState" value={state} onChange={handleStateChange}>
                                {states.length > 0 && (
                                    states.map(state => (
                                        <option key={state.code} value={state.code}>{state.name}</option>
                                    ))
                                )}
                            </select>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="updateZipCode">Zip Code: </label>
                            <select className="form-input" name="updateZipCode" id="updateZipCode" value={zipCode} onChange={handleZipCodeChange}>
                                {zipCodes.length > 0 && (
                                    zipCodes.map((zipCode, index) => (
                                        <option key={index} value={zipCode}>{zipCode}</option>
                                    ))
                                )}
                            </select>
                        </div>

                        <button id="updateInformationButton" disabled={isLoading} className="form-btn-1" type="submit">{isLoading ? "Updating Information..." : "Update Information"}</button>
                    </form>
                ) : (
                    <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={fetchEmployee}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
                )}    
            </Modal>
        </>
    )
}