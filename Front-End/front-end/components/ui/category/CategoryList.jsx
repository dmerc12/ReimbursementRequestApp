import { FiEdit, FiTrash2 } from 'react-icons/fi';
import Modal from '@/components/Model';
import { useState } from 'react';

export default function CategoryList({ categories }) {
    const [editVisible, setEditVisible] = useState(false);
    const [deleteVisible, setDeleteVisible] = useState(false);

    let categoryRows = [];
    if (categories.success) {
        for (let i=0; i<categories.success.length; i++) {
            const category = categories.success[i];
            console.log(category)
            categoryRows.unshift(
                <tr key={category.categoryId}>
                    <td className="category-table-data w-full">{category.categoryName}</td>
                    <td className="category-table-data flex gap-5">
                        <FiEdit onClick={() => setEditVisible(true)} className='text-blue-500' cursor="pointer" size={15}/>
                        <Modal visible={editVisible} onClose={() => setEditVisible(false)}>

                        </Modal>
                        <FiTrash2 onClick={() => setDeleteVisible(true)} className='text-red-500' cursor="pointer" size={15}/>
                        <Modal visible={deleteVisible} onClose={() => setDeleteVisible(false)}>

                        </Modal>
                    </td>
                </tr>
            )
            console.log(categoryRows)
        }
    }

    return (
        <>
            <div className="category-list overflow-x-auto">
                <table className="table w-full">
                    <thead>
                        <tr>
                            <th  className="category-table-head">Category Name</th>
                            <th className="category-table-head">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {categoryRows}
                    </tbody>
                </table>
            </div>
        </>
    )
}