import { useEffect, useState } from "react";
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import ChangePasswordForm from "@/components/ui/employee/ChangePasswordForm";
import Cookies from "js-cookie";

export const metadata = {
    title: "Changing Password",
    description: "Changing password"
};

export default function ChangePassword() {
    const [sessionId, setSessionId] = useState(0);

    const router = useRouter();

    useEffect(() => {
        const sessionIdCookie = Cookies.get('sessionId');
        if (!sessionIdCookie) {
            router.push('/login');
            toast.info("Please login or register to gain access!", {
                toastId: 'customId'
            })
        }
        setSessionId(sessionIdCookie)
    }, [router]);

    return (
        <>
            <h1>Change Your Current Password Below</h1>
            <ChangePasswordForm  sessionId={sessionId}/>
        </>
    )
}
