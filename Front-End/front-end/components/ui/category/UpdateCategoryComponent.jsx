import { useState, useEffect } from 'react';
import { FiEdit } from 'react-icons/fi';
import Modal from '@/components/Model';
import Cookies from 'js-cookie';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';

export default function UpdateCategoryComponent({ sessionId, category }) {
    const [editVisible, setEditVisible] = useState(false);
    const [categoryName, setCategoryName] = useState('');

    const router = useRouter();

    useEffect(() => {
        if (category) {
            setCategoryName(category.categoryName);
        }
    }, [category]);

    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('/api/category/handleUpdate', 
                {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'categoryId': category.categoryId,
                    'categoryName': categoryName
                }) 
                }
            )
            const data = await response.json();

            if (data.success) {
                router.push('/manage-categories');
                setEditVisible(false);
                setCategoryName('')
                toast.success("Category Successfully Updated!", {
                    toastId: 'customId'
                });
            } else if (data.error.message) {
                throw new Error(`${data.error.message}`);
            } else if (data.error) {
                throw new Error(`${data.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again!")
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
    return (
        <>
            <FiEdit onClick={() => setEditVisible(true)} className='text-blue-500' cursor="pointer" size={15}/>
            <Modal visible={editVisible} onClose={() => setEditVisible(false)}>
                <form className='form' onSubmit={onSubmit}>
                    <div className='form-field'>
                        <label className='form-label' htmlFor='categoryName'>Category Name: </label>
                        <input className='form-input' type='text' id='updateCategoryName' name='categoryName' value={categoryName} onChange={event => setCategoryName(event.target.value)}/>
                    </div>

                    <button className='form-btn-2' type='submit' id='updateCategoryNameButton'>Update Category</button>
                </form>
            </Modal>
        </>
    )
}