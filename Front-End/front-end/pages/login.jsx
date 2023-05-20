import LoginForm from '@/components/ui/employee/LoginForm';

export const metadata = {
    title: "Logging In",
    description: "Logging in"
  };

export default function LoginPage() {

    return (
        <>
            <h1>Please Login Below</h1>
            <LoginForm />
        </>
    )
}