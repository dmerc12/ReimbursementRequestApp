import { Modal } from "../../Modal";
import { toast } from 'react-toastify';
import { useState, useEffect, ChangeEvent } from "react";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";
import { states } from "../../../lib/States";
import { zipCodeData } from "../../../lib/ZipCodes";

interface Employee {
    firstName: string,
    lastName: string,
    email: string,
    phoneNumber: string,
    address: string
}

export const UpdateForm = (props: { employee: Employee }) => {
    const addressComponents = props.employee.address.split(', ');
    const initialStreetAddress = addressComponents[0];
    const initialCity = addressComponents[1];
    const initialState = addressComponents[2].split(' ')[0];
    const initialZipCode = addressComponents[2].split(' ')[1];

    const [isLoading, setIsLoading] = useState(false);
    const [visible, setVisible] = useState(false);
    const [sessionId, setSessionId] = useState('');
    const [firstName, setFirstName] = useState(props.employee.firstName);
    const [lastName, setLastName] = useState(props.employee.lastName);
    const [email, setEmail] = useState(props.employee.email);
    const [phoneNumber, setPhoneNumber] = useState(props.employee.phoneNumber);
    const [streetAddress, setStreetAddress] = useState(initialStreetAddress);
    const [city, setCity] = useState(initialCity);
    const [state, setState] = useState(initialState);
    const [zipCode, setZipCode] = useState(initialZipCode);
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

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setIsLoading(true);
        try {
            const fullAddress = `${streetAddress}, ${city}, ${state} ${zipCode}`;



        } catch (error: any) {
            setIsLoading(false);
            toast.error(error.message, {
                toastId: 'customId'
            });
        }
    };

    useEffect(() => {
        const sessionIdCookie = Cookies.get('sessionId');
        if (!sessionIdCookie) {
            navigate('/login')
            toast.info("Please login or register to gain access!", {
                toastId: 'customId'
            });
        } else {
            setSessionId(sessionIdCookie);
            const initialZipCodes = zipCodeData[state] || [];
            setZipCodes(initialZipCodes)
        }
    }, []);

    return (
        <>
            <div className="update-employee-component">
                <button onClick={() => setVisible(true)} className="update-employee-btn" id="updateInformationModal">Update Information</button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
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

                <button id="updateInformationButton" disabled={isLoading}className="form-btn-1" type="submit">{isLoading ? "Loading..." : "Update Information"}</button>
                </form>
            </Modal>
        </>
    )
}