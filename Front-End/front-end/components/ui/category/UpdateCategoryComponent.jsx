import { useState } from 'react';
import { FiEdit } from 'react-icons/fi';
import Modal from '@/components/Model';

export default function UpdateCategoryComponent() {
    const [editVisible, setEditVisible] = useState(false);

    return (
        <>
            <FiEdit onClick={() => setEditVisible(true)} className='text-blue-500' cursor="pointer" size={15}/>
            <Modal visible={editVisible} onClose={() => setEditVisible(false)}>

            </Modal>
        </>
    )
}