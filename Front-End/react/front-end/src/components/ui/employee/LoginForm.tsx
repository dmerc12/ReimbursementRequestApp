import { Modal } from '../../Modal';
import { useState } from 'react';

export const LoginForm = () => {
    const [visible, setVisible] = useState(false)

    return (
        <>
            <div>
                <button className='open-button' onClick={() => setVisible(true)}>Open me!</button>
            </div>

            <Modal visible={visible} onClose={() => setVisible(false)}>
                <form>
                    <h1>hello there from my modal!</h1>
                </form>
            </Modal>
        
        </>
    );
}
