'use client'

import { AiOutlinePlus } from "react-icons/ai";
import Modal from "../../Model";
import { useState } from 'react';

export default function AddCategoryButton() {
    const [visible, setVisible] = useState(false)


    return (
        <>
            <div className="add-category-component">
                <button onClick={() => setVisible(true)} className="add-category-btn">Add New Category <AiOutlinePlus className="plus-icon" size={15} /></button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>Hello</Modal>
        </>
    )
}