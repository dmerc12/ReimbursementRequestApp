import { useState, useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import AddCategoryButton from '@/components/ui/category/AddCategoryButton';
import CategoryList from '@/components/ui/category/CategoryList';

export default function ManageInformation() {

  const router = useRouter();

  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const sessionId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('sessionId=')).split('=')[1];
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
          console.log(data)
          setCategories(data)
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
      <AddCategoryButton />
      <CategoryList categories={categories}/>
    </>
  )
}