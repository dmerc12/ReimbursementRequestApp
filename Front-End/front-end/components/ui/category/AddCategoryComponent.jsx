'use client'

import { AiOutlinePlus } from "react-icons/ai";
import Modal from "@/components/Model";
import { useState } from 'react';
import Cookies from "js-cookie";
import { toast } from "react-toastify";
import { useRouter } from 'next/router';

export default function AddCategoryComponent({ sessionId }) {
    const [visible, setVisible] = useState(false);
    const [categoryName, setCategoryName] = useState('');

    const router = useRouter();

    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('/api/category/handleAdd', {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'categoryName': categoryName
                })
            })

            const data = await response.json();

            if (data.success) {
                router.push('/manage-categories');
                setVisible(false);
                setCategoryName('')
                toast.success("Category Successfully Added!", {
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
            <div className="add-category-component">
                <button onClick={() => setVisible(true)} className="add-category-btn" id='addNewCategoryModal'>Add New Category <AiOutlinePlus className="plus-icon" size={15} /></button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
                <form className='form' onSubmit={onSubmit}>
                    <div className='form-field'>
                        <label className="form-label" htmlFor="categoryName">New Category Name: </label>
                        <input className="form-input" type='text' id='newCategoryName' name='categoryName' value={categoryName} onChange={event => setCategoryName(event.target.value)}></input>
                    </div>

                    <button className='form-btn-2' type='submit' id='addNewCategoryButton'>Add Category</button>
                </form>
            </Modal>
        </>
    )
}