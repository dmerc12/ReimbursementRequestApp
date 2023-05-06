import { useEffect } from 'react'
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';
import AddCategoryButton from '@/components/ui/category/AddCategoryButton';
import CategoryList from '@/components/ui/category/CategoryList';

export default function ManageInformation() {

  const router = useRouter();

  useEffect(() => {
    const sessionId = document.cookie.split(';').find(cookie => cookie.trim().startsWith('sessionId='));
    if (!sessionId) {
      router.push('/login');
      toast.info("Please login or register to gain access!", {
        toastId: "customId"
      });
    }
  }, [router]);

  return (
    <>
      <h1>Manage Category Information Page</h1>
      <AddCategoryButton />
      <CategoryList />
    </>
  )
}