'use client'

import { useState, useEffect } from 'react';
import { FiEdit } from 'react-icons/fi';
import { useRouter } from 'next/router';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';
import Modal from '@/components/Model';

export default function UpdateRequestComponent({ request }) {
    const [visible, setVisible] = useState(false);
    const [categories, setCategories] = useState([]);
    const [requestId, setRequestId] = useState(0);
    const [categoryId, setCategoryId] = useState(0);
    const [comment, setComment] = useState('');
    const [amount, setAmount] = useState(0);

    const router = useRouter();

    useEffect(() => {
        if (request) {
            setRequestId(request.requestId);
            setCategoryId(request.categoryId);
            setComment(request.comment);
            setAmount(request.amount);
        }
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
        }
        fetchCategories();
    }, [request]);

    const onSubmit = async (event) => {
        event.preventDefault();
        try {
            const sessionId = Cookies.get('sessionId');
            const response = await fetch('/api/request/handleUpdate', {
                method: 'PATCH',
                heades: {'Content-Type': 'application/json'},
                body: JSON.stringify({
                    'sessionId': sessionId,
                    'requestId': requestId,
                    'categoryId': categoryId,
                    'comment': comment,
                    'amount': amount
                })
            })

            const data = await response.json();

            if (data.success) {
                setVisible(false);
                setRequestId(0);
                setCategoryId(0);
                setComment('');
                setAmount(0);
                router.push('manage-requests');
                toast.success('Request Successfully Updated!', {
                    toastId: 'customId'
                })
            } else if (data.error.message) {
                throw new Error(`${data.error.message}`);
            } else if (data.error) {
                throw new Error(`${data.error}`);
            } else {
                throw new Error("Something went extremely wrong, please try again!");
            }
        } catch (error) {
            if (error.message === "Session has expired, please log in!") {
                setVisible(false)
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
            <FiEdit onClick={() => setVisible(true)} className='text-blue-500' cursor='pointer' size={15} />
            <Modal visible={visible} onClose={() => setVisible(false)}>
                <form className='form' onSubmit={onSubmit}>
                    <div className='form-field'>
                        <label className='form-label' htmlFor='categoryDropDown'>Category: </label>
                        <select className='form-input' id='updateCategoryDropDown' name='categoryDropDown' value={categoryId} onChange={event => setCategoryId(event.target.value)}>
                            {categories.map(category => (
                                <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                            ))}
                        </select>
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor='comment'>Comment: </label>
                        <input className='form-input' type='text' id='updateCommentInput' name='comment' value={comment} onChange={event => setComment(event.target.value)}></input>
                    </div>

                    <div className='form-field'>
                        <label className='form-label' htmlFor='amount'>Amount: </label>
                        <input className='form-input' type='number' id='updateAmountInput' name='amount' value={amount} onChange={event => setAmount(event.target.value)}></input>
                    </div>

                    <button className='form-btn-2' type='submit' id='updateRequestButton'>Update Request</button>
                </form>
            </Modal>
        </>
    )
}
