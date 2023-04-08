import { useState } from 'react'
import { signIn } from 'next-auth/react'
import { useRouter } from 'next/router'


export default function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    
    const router = useRouter();

    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await signIn('credentials', { email, password, callbackUrl: '/' });
            console.log(response)

            if (response.status === 400) {
                const error = await response.json();
                alert(error.message)
            } else if (response.status === 200) {
                alert("Welcome!")
            } else if (!response) {
                alert("no response recieved")
            } else {
                alert("something went wrong")
            }

            const userData = await response.json();
            const user = {
                id: userData.employeeId,
                name: `${userData.firstName} ${userData.lastName}`,
                email: userData.email
                // Add any other user properties you need to store in the session here
            };
        } catch (error) {
            // Handle the error message returned by the Java backend
            console.log(error.message)
            const errorMessage = await error
            alert(errorMessage.message);
        }
    };

    return (
        <>
            <h1>Login</h1>
            <form onSubmit={onSubmit}>
                <label htmlFor="email">Email</label>
                <input
                type="email"
                id="loginEmail"
                name="email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                />
                <label htmlFor="password">Password</label>
                <input
                type="password"
                id="loginPassword"
                name="password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
                />
                <button type="submit">Sign in</button>
            </form>
        </>
    )
}