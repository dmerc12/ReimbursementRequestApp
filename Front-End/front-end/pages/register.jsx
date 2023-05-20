import RegisterForm from '../components/ui/employee/RegisterForm'

export const metadata = {
    title: "Registering",
    description: "Registering"
  };

export default function Register() {

    return (
        <>
            <h1>Register below:</h1>
            <RegisterForm/>
        </>
    )
}