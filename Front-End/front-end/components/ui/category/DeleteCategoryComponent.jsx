import { useState } from 'react';
import { FiTrash2 } from 'react-icons/fi';
import Modal from '@/components/Model';
import Cookies from 'js-cookie';
import { useRouter } from 'next/router'
import { toast } from 'react-toastify';

export default function DeleteCategoryComponent({ category }) {
    const [visible, setVisible] = useState(false);

    const router = useRouter();

    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const sessionId = Cookies.get('sessionId');
            const response = await fetch('/api/category/handleDelete', {
                method: "PATCH",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'categoryId': category.categoryId
                })
            })

            const data = await response.json();

            if (data.success) {
                router.push('/manage-categories');
                setVisible(false);
                toast.success("Category Successfully Deleted!", {
                    toastId: 'customId'
                });
            } else if (data.error.message) {
                throw new Error(`${data.error.message}`);
            } else if (data.error) {
                throw new Error(`${data.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again!");
            }
        } catch (error) {
            if (error.message === "Session has expired, please try again!") {
                setVisible(false);
                Cookies.remove('sessionId');
                router.push('/login');
                toast.warn(error.message, {
                    toastId: 'customId'
                });
            } else {
                setVisible(false)
                toast.error(error.message, {
                    toastId: 'customId'
                });
            }
        } 
    }

    return (
        <>
            <FiTrash2 onClick={() => setVisible(true)} className='text-red-500' cursor="pointer" size={15}/>
            <Modal visible={visible} onClose={() => setVisible(false)}>
                <form className='form' onSubmit={onSubmit}>
                    <div className='form-field'>
                        <label className='form-label'>Are you sure?</label>
                    </div>

                    <button className='form-btn-2' type='submit' id='deleteCategoryButton'>Delete Category</button>
                </form>
            </Modal>
        </>
    )
}