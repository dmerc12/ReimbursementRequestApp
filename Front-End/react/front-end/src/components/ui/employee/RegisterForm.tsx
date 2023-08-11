import React, { useState } from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export const RegisterForm = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmationPassword, setConfirmationPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [streetAddress, setStreetAddress] = useState('');
    const [city, setCity] = useState('');
    const [state, setState] = useState('');
    const [zipCode, setZipCode] = useState('');
    const [states, setStates] = useState([]);
    const [zipCodes, setZipCodes] = useState([]);
    const [isLoading, setIsLoading] = useState(false)

    const navigate = useNavigate();

    const handlePhoneNumberChange = (event: any) => {
        const phoneNumberValue = event.target.value;
        const formattedPhoneNumber = formatPhoneNumber(phoneNumberValue);
        setPhoneNumber(formattedPhoneNumber);
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

    const onSubmit = async(event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault;
        setIsLoading(true);
        try {
            const fullAddress = `${streetAddress}, ${city}, ${state} ${zipCode}`;

            const response = await fetch ('http://localhost:8080/register/now', 
                {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({
                        'firstName': firstName,
                        'lastName': lastName,
                        'email': email,
                        'password': password,
                        'confirmationPassword': confirmationPassword,
                        'phoneNumber': phoneNumber,
                        'address': fullAddress
                    })
                }
            );
            const data = await response.json();

            if (response.status === 201) {
                navigate('/login');
                toast.success("Employee successfully created, please log in!", {
                    toastId: 'customId'
                });
            } else if (response.status === 400) {
                throw new Error(`${data.message}`);
            } else {
                throw new Error("Cannot connect to the back end of the application, please try again!");
            }
        } catch (error: any) {
            toast.error(error.message);
        }
    }

    return (
        <>
            <h1>requests here</h1>
        </>
    );
}