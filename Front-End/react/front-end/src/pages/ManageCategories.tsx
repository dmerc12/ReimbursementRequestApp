import { Modal } from "../components/Modal";
import { useState } from "react";

export const ManageCategories = () => {
    const [visible, setVisible] = useState(false);

    return (
        <>
            <h1>categories here</h1>

            <div className='home-component'>
                <button onClick={() => setVisible(true)}>Open me for a modal</button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
                <h1>Welcome to my modal</h1>
            </Modal>
        </>
    );
}