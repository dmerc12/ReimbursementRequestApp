import { useState } from 'react';
import { FiTrash2 } from 'react-icons/fi';
import Modal from '@/components/Model';

export default function DeleteCategoryComponent() {
    const [deleteVisible, setDeleteVisible] = useState(false);

    return (
        <>
            <FiTrash2 onClick={() => setDeleteVisible(true)} className='text-red-500' cursor="pointer" size={15}/>
            <Modal visible={deleteVisible} onClose={() => setDeleteVisible(false)}>

            </Modal>
        </>
    )
}