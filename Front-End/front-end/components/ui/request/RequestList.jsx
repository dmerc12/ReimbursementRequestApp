'use client'

import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import Cookies from 'js-cookie';

export default function RequestList({ requests }) {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchCategories = async () =>{
            try {
                const sessionId = Cookies.get('sessionId');
                const response = await fetch('api/category/handleGetAll', {
                    method: 'PATCH',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({
                        'sessionId': sessionId
                    })
                })
                
                const data = await response.json();
    
                if (data.success) {
                    setCategories(data.success);
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
                      toastId: "customId"
                    });
                  }
            }
        }
        fetchCategories();
    }, []);

    const getCategoryName = categoryId => {
        const category = categories.find(cat => cat.categoryId === categoryId);
        return category ? category.categoryName : '';
    }

    let requestRows = [];

    if (requests) {
        for (let i=0; i<requests.length; i++) {
            const request = requests[i];
            const categoryName = getCategoryName(request.categoryId);
            requestRows.unshift(
                <tr key={request.requestId}>
                    <td className='request-table-data'>{request.requestId}</td>
                    <td className='request-table-data'>{categoryName}</td>
                    <td className='request-table-data'>{request.comment}</td>
                    <td className='request-table-data'>{request.amount}</td>
                    <td className='request-table-data flex gap-5'>

                    </td>
                </tr>
            )
        }
    }

    return (
        <>
            <div className="request-list overflow-x-auto">
                <table className='table w-full'>
                    <thead>
                        <tr>
                            <th className="request-table-head">Request ID</th>
                            <th className="request-table-head">Category</th>
                            <th className="request-table-head">Comment</th>
                            <th className="request-table-head">Amount</th>
                            <th className="request-table-head">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {requestRows}
                    </tbody>
                </table>
            </div>
        </>
    )
}