import { Modal } from "../../Modal";
import { toast } from 'react-toastify';
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useFetch } from "../../../hooks/useFetch";
import Cookies from "js-cookie";
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
    const sessionId = Cookies.get('sessionId');

    const [updateForm, setUpdateForm] = useState({
        sessionId: Number(sessionId),
        firstName: '',
        lastName: '',
        email: '',
        phoneNumber: '',
        address: ''
    });
    const [address, setAddress] = useState({
        streetAddress: '',
        city: '',
        state: '',
        zipCode: ''
    });
    const [loading, setLoading] = useState(false);
    const [dataLoading, setDataLoading] = useState(false);
    const [failedToFetch, setFailedToFetch] = useState(false);
    const [failedToFetchData, setFailedToFetchData] = useState(false);
    const [visible, setVisible] = useState(false);
    const [employee, setEmployee] = useState(false);
    const [zipCodes, setZipCodes] = useState([] as string[])

    const { fetchData } = useFetch();

    const navigate = useNavigate();

    const onChange = (event: any) => {
        const { name, value } = event.target;
        
        if (name === 'phoneNumber') {
            const phoneNumberDigits = value.replace(/\D/g, '');
            const parts = phoneNumberDigits.match(/^(\d{3})(\d{0,3})(\d{0,4})$/);
            if (parts) {
                const formattedPhoneNumber = `${parts[1]}${parts[2] ? '-' + parts[2] : ''}${parts[3] ? '-' + parts[3] : ''}`;
                setUpdateForm((prevForm) => ({
                    ...prevForm,
                    phoneNumber: formattedPhoneNumber
                }));
            } else {
                setUpdateForm((prevForm) => ({
                    ...prevForm,
                    phoneNumber: value
                }));
            }
        } else if (name === 'state') {
            const selectedStateCode = value;
            const selectedZipCodes = zipCodeData[selectedStateCode] || [];
            setAddress((prevAddress) => ({
                ...prevAddress,
                state: selectedStateCode,
                zipCode: ''
            }));
            const fullAddress = `${address.streetAddress}, ${address.city}, ${selectedStateCode} ${address.zipCode}`;
            setUpdateForm((prevForm) => ({
                ...prevForm,
                address: fullAddress
            }));
            setZipCodes(selectedZipCodes);
        } else if (name === 'zipCode') {
            setAddress((prevAddress) => ({
                ...prevAddress,
                zipCode: value
            }));
            const fullAddress = `${address.streetAddress}, ${address.city}, ${address.state} ${value}`;
            setUpdateForm((prevForm) => ({
                ...prevForm,
                address: fullAddress
            }));
        } else if (name === 'streetAddress' || name === 'city') {
            setAddress((prevAddress) => ({
                ...prevAddress,
                [name]: value
            }));
            const fullAddress = `${address.streetAddress}, ${address.city}, ${address.state} ${address.zipCode}`;
            setUpdateForm((prevForm) => ({
                ...prevForm,
                address: fullAddress
            }));
        } else {
            setUpdateForm((prevForm) => ({
                ...prevForm,
                [name]: value
            }));
        }
    };

    const onSubmit = async (event: any) => {
        event.preventDefault();
        setLoading(true);
        setFailedToFetch(false);
        try {
            const { responseStatus, data } = await fetchData('/api/update/employee', 'PUT', updateForm)

            if (responseStatus === 200) {
                navigate('/manage-information');
                setVisible(false);
                setLoading(false);
                toast.success("Information successfully updated!", {
                    toastId: 'customId'
                });
            } else if (responseStatus === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end, please try again!");
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetch(true);
                setLoading(false);
            } else {
                setLoading(false);
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
        setDataLoading(true);
        setFailedToFetchData(false);
        try {
            const { responseStatus, data } = await fetchData("/api/get/employee", "PATCH", {sessionId: Number(sessionId)});
            
            if (responseStatus === 200) {
                const employee: Employee = data
                const addressComponents = employee.address.split(', ');
                const initialState = addressComponents[2].split(' ')[0];
                const initialZipCodes = zipCodeData[initialState];
                const initialZipCode = addressComponents[2].split(' ')[1];
                setUpdateForm({
                    sessionId: Number(sessionId),
                    firstName: employee.firstName,
                    lastName: employee.lastName,
                    email: employee.email,
                    phoneNumber: employee.phoneNumber,
                    address: employee.address
                });
                setAddress({
                    streetAddress: addressComponents[0],
                    city: addressComponents[1],
                    state: initialState,
                    zipCode: initialZipCode
                });
                setZipCodes(initialZipCodes);
                setEmployee(true);
                setDataLoading(false);
            } else if (responseStatus === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end of the application, please try again!");
            }
        } catch (error: any) {
            if (error.message === "No session found, please try again!" || error.message === "Session has expired, please log in!") {
                Cookies.remove('sessionId');
                navigate('/login');
                setDataLoading(false);
                toast.warn(error.message, {
                    toastId: "customId"
                });
            } else if (error.message === "Failed to fetch") {
                setFailedToFetchData(true);
                setDataLoading(false);
            } else {
                setDataLoading(false);
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
                <button onClick={() => {setVisible(true); setFailedToFetch(false); setFailedToFetchData(false)}} className="action-btn" id="updateInformationModal">Update Information</button>
            </div>

            <Modal visible={visible} onClose={() => {setVisible(false); setFailedToFetch(false); setFailedToFetchData(false)}}>
                {dataLoading ? ( 
                    <div className='loading-indicator'>
                        <FaSpinner className='spinner' />
                    </div>
                ) : loading ? (
                    <div className='loading-indicator'>
                        <FaSpinner className='spinner' />
                    </div>
                ) : failedToFetch ? (
                    <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={onSubmit}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
                ) : failedToFetchData ? (
                    <div className='failed-to-fetch'>
                        <AiOutlineExclamationCircle className='warning-icon'/>
                        <p>Cannot connect to the back end server.</p>
                        <p>Please check your internet connection and try again.</p>
                        <button className='retry-button' onClick={fetchEmployee}>
                            <FaSync className='retry-icon'/> Retry
                        </button>
                        <button className='back-button' onClick={goBack}>Go Back</button>
                    </div>
                ) : (
                    <form className="form" onSubmit={onSubmit}>
                        <div className="form-field">
                            <label className="form-label" htmlFor="firstName">First Name: </label>
                            <input className="form-input" type="text"  id="updateFirstName" name="firstName" value={updateForm.firstName} onChange={onChange}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="lastName">Last Name: </label>
                            <input className="form-input" type="text"  id="updateLasttName" name="lastName" value={updateForm.lastName} onChange={onChange}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="email">Email: </label>
                            <input className="form-input" type="email"  id="updateEmail" name="email" value={updateForm.email} onChange={onChange}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="phoneNumber">Phone Number: </label>
                            <input className="form-input" type="text"  id="updatePhoneNumber" name="phoneNumber" value={updateForm.phoneNumber} onChange={onChange}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="streetAddress">Street Address: </label>
                            <input className="form-input" type="text"  id="updateStreetAddress" name="streetAddress" value={address.streetAddress} onChange={onChange}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="city">City: </label>
                            <input className="form-input" type="text"  id="updateCity" name="city" value={address.city} onChange={onChange}/>
                            <br />
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="state">State: </label>
                            <select className="form-input" name="updateState" id="state" value={address.state} onChange={onChange}>
                                {states.length > 0 && (
                                    states.map(state => (
                                        <option key={state.code} value={state.code}>{state.name}</option>
                                    ))
                                )}
                            </select>
                        </div>

                        <div className="form-field">
                            <label className="form-label" htmlFor="zipCode">Zip Code: </label>
                            <select className="form-input" name="updateZipCode" id="zipCode" value={address.zipCode} onChange={onChange}>
                                {zipCodes.length > 0 && (
                                    zipCodes.map((zipCode, index) => (
                                        <option key={index} value={zipCode}>{zipCode}</option>
                                    ))
                                )}
                            </select>
                        </div>

                        <button id="updateInformationButton" className="form-btn-3" type="submit">Update Information</button>
                    </form>
                )}    
            </Modal>
        </>
    )
}