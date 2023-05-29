import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import AddCategoryComponent from '@/components/ui/category/AddCategoryComponent';
import CategoryList from '@/components/ui/category/CategoryList';
import Cookies from 'js-cookie';

export const metadata = {
  title: "Managing Categories",
  description: "Managing categories"
};

export default function ManageInformation() {

  const router = useRouter();

  const [categories, setCategories] = useState([]);
  var [sessionId, setSessionId] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const sessionIdCookie = Cookies.get('sessionId');
        if (!sessionIdCookie) {
          router.push('/login');
          toast.info("Please login or register to gain access!", {
            toastId: "customId"
          });
        } else {
          const response = await fetch("/api/category/handleGetAll", {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'}, 
            body: JSON.stringify({
              'sessionId': sessionIdCookie
            })
          })

          const data = await response.json();
          if (data.success) {
            setCategories(data);
            setSessionId(sessionIdCookie);
          } else if (data.error.message) {
            throw new Error(`${data.error.message}`);
          } else if (data.error) {
            throw new Error(`${data.error}`);
          } else {
            throw new Error("Something went extremely wrong, please try again!");
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
            toastId: "customId"
          });
        }
      }
    }
    fetchData();
  }, [router]);

  return (
    <>
      <h1>Manage Category Information Page</h1>
      <AddCategoryComponent sessionId={sessionId} />
      <CategoryList sessionId={sessionId} categories={categories}/>
    </>
  )
}