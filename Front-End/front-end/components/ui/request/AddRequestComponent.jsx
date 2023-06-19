'use client'

import { AiOutlinePlus } from 'react-icons/ai';
import Modal from '@/components/Model';
import { useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { toast } from 'react-toastify';
import { useRouter } from 'next/router';

export default function AddRequestComponent() {
    const [visible, setVisible] = useState(false);
    const [categoryId, setCategoryId] = useState(0);
    const [comment, setComment] = useState('');
    const [amount, setAmount] = useState(0);
    const [categories, setCategories] = useState([]);

    const router = useRouter();

    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const sessionId = Cookies.get('sessionId')
                const response = await fetch('api/category/handleGetAll', {
                    method: 'PATCH',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({
                        'sessionId': sessionId
                    })})
                const data = await response.json();

                if (data.success) {
                    setCategories(data.success);
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
        };

        fetchCategories();
    }, []);

    const onSubmit = async (event) => {
        event.preventDefault(); 
        try {
            const sessionId = Cookies.get('sessionId');
            const response = await fetch('/api/request/handleAdd', {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'categoryId': categoryId,
                    'comment': comment,
                    'amount': amount
                })
            })

            const data = await response.json();

            if (data.success) {
                setVisible(false);
                setComment('');
                setAmount(0);
                setCategories([]);
                router.push('/manage-requests');
                toast.success('Request Successfully Added!', {
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
            <div className='add-request-component'>
                <button onClick={() => setVisible(true)} className='add-request-btn' id='addNewRequestModal'>Add New Request <AiOutlinePlus className='plus-icon' size={15} /></button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
                <form className='form' onSubmit={onSubmit}>
                    <div className='form-field'>
                        <label className='form-label' htmlFor='categoryDropDown'>Category: </label>
                        <select className='form-input' id='addCategoryDropDown' name='categoryDropDown' value={categoryId} onChange={event => setCategoryId(event.target.value)}>
                            {categories && categories.length > 0 && (
                                (() => {
                                    const options = [];
                                    for (let i=0; i < categories.length; i++) {
                                        const category = categories[i];
                                        options.push(<option key={category.categoryIc} value={category.categoryId}>{category.categoryName}</option>);
                                    }
                                    return options;
                                })
                            )}
                        </select>
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor='comment'>Comment: </label>
                        <input className='form-input' type='text' id='createCommentInput' name='comment' value={comment} onChange={event => setComment(event.target.value)}></input>
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor='amount'>Amount: </label>
                        <input className='form-input' type='number' id='createAmountInput' name='amount' value={amount} onChange={event => setAmount(event.target.value)}></input>
                    </div>

                    <button className='form-btn-2' type='submit' id='addNewRequestButton'>Add Request</button>
                </form>
            </Modal>
        </>
    )
}