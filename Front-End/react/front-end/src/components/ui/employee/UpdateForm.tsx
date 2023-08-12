import { Modal } from "../../Modal";
import { toast } from 'react-toastify';
import { useState, useEffect, ChangeEvent } from "react";
import Cookies from "js-cookie";
import { useNavigate } from "react-router-dom";
import { states } from "../../../lib/States";
import { zipCodeData } from "../../../lib/ZipCodes";

export const UpdateForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [visible, setVisible] = useState(false);
    const [sessionId, setSessionId] = useState('');
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

    const onSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setIsLoading(true);
        try {
            const fullAddress = `${streetAddress}`

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
        }
    }, []);

    return (
        <>
            <div className="update-employee-component">
                <button onClick={() => setVisible(true)} className="update-employee-btn" id="updateInformationModal">Update Information</button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
                <form className="form" onSubmit={onSubmit}>

                </form>
            </Modal>
        </>
    )
}