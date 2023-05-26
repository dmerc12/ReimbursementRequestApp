import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import RequestList from '@/components/ui/request/RequestList';

export const metadata = {
  title: "Managing Requests",
  description: "Managing requests"
};

export default function ManageRequest() {
    const [sessionId, setSessionId] = useState(null);
    const [requests, setRequests] = useState([]);
    
    const router = useRouter();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const sessionIdCookie = Cookies.get('sessionId');
                if (!sessionIdCookie) {
                    router.push('/login');
                    toast.info("Please login or register to gain access!", {
                        toastId: 'customId'
                    });
                } else {
                  const response = await fetch("/api/request/handleGetAll", {
                      method: 'PATCH',
                      headers: {'Content-Type': 'application/json'},
                      body: JSON.stringify({
                          'sessionId': sessionIdCookie
                      })})
                      console.log(response)
                  const data = await response.json();
                  if (data.success) {
                    setRequests(data.success);
                    setSessionId(sessionIdCookie);
                  } else if (data.error.message) {
                    throw new Error(`${data.error.message}`);
                  } else if (data.error) {
                    throw new Error(`${data.error}`)
                  } else {
                    throw new Error("Something went extremely wrong, please try again!")
                  }
                }
            } catch (error) {
                if (error.message === "Session has expired, please log in!") {
                    Cookies.remove('sessionId');
                    router.push('/login');
                    toast.warn(error.message, {
                        toastId: 'customId'
                    });
                } else {
                    toast.error(error.message, {
                        toastId: 'customId'
                    });
                }
            }
        }
        fetchData();
    }, [router]);

    return (
        <>
            <h1>Manage Request Information Page</h1>
            <RequestList sessionId={sessionId} requests={requests}/>
        </>
    )
}
