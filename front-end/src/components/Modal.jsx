import React from 'react';

export const Modal = ({ visible, onClose, children }) => {
    if (!visible) return null;

    const handleClose = (event) => {
        if (event.targer.id === "wrapper") onClose();
    };

    return (
        <>
            <div className='modal-exterior-wrapper' id='wrapper' onClick={handleClose}>
                <div className='modal-interior-wrapper'>
                    <button className='modal' onClick={() => onClose()}>Close</button>
                    <div className='modal-interior'>
                        {children}
                    </div>
                </div>
            </div>
        </>
    );
};
