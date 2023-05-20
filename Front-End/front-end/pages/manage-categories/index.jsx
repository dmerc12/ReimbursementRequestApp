import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import AddCategoryComponent from '@/components/ui/category/AddCategoryComponent';
import CategoryList from '@/components/ui/category/CategoryList';

export default function ManageInformation() {

  const router = useRouter();

  const [categories, setCategories] = useState([]);

  const sessionId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('sessionId=')).split('=')[1];

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (!sessionId) {
          router.push('/login');
          toast.info("Please login or register to gain access!", {
            toastId: "customId"
          });
        } else {
          const response = await fetch("/api/category/handleGetAll", {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'}, 
            body: JSON.stringify({
              'sessionId': sessionId
            })})
          const data = await response.json();
          setCategories(data);
        }
      } catch (error) {
        toast.error(error.message, {
          toastId: "customId"
        });
      }
    }
    fetchData();
  }, [router]);

  return (
    <>
      <h1>Manage Category Information Page</h1>
      <AddCategoryComponent sessionId={sessionId}/>
      <CategoryList categories={categories}/>
    </>
  )
}