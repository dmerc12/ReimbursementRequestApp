import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import DeleteForm from '@/components/ui/employee/DeleteForm';

export default function DeleteEmployeeInformation() {
    const [sessionId, setSessionId] = useState(0);

    const router = useRouter();

    useEffect(() => {
        const sessionId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('sessionId='));
        if (sessionId) {
            const sessionIdValue = sessionId.split('=')[1];
            setSessionId(sessionIdValue);
        }
        if (!sessionId) {
          router.push('/login');
          toast.info("Please login or register to gain access!", {
            toastId: "customId"
          })
        }
      }, [router]);

    return (
        <>
            {sessionId && <DeleteForm sessionId={sessionId}/>}
        </>
    )
}