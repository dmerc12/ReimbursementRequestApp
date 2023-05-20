import { useEffect, useState } from "react";
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import ChangePasswordForm from "@/components/ui/employee/ChangePasswordForm";

export const metadata = {
    title: "Changing Password",
    description: "Changing password"
};

export default function ChangePassword() {
    const [sessionId, setSessionId] = useState(0);

    const router = useRouter();

    useEffect(() => {
        const sessionId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('sessionId')).split('=')[1];
        if (!sessionId) {
            router.push('/login');
            toast.info("Please login or register to gain access!", {
                toastId: "customId"
            })
        }
        setSessionId(sessionId)
    }, [router]);

    return (
        <>
            <h1>Change Your Current Password Below</h1>
            <ChangePasswordForm  sessionId={sessionId}/>
        </>
    )
}
