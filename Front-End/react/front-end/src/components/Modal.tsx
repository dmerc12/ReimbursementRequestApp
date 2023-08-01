import React from 'react';

interface ModalProps {
    visible: boolean;
    onClose: () => void;
    children: React.ReactNode;
}

export const Modal = ({ visible, onClose, children }: ModalProps) => {
    if (!visible) return null;

    const handleClose = (event: React.MouseEvent<HTMLDivElement>) => {
        const targetElement = event.target as HTMLDivElement;
        if (targetElement.id === "wrapper") onClose();
    }

    return (
        <>
            <div id='wrapper' onClick={handleClose}>
                <div>
                    <button onClick={() => onClose()}>x</button>
                    <div>
                        {children}
                    </div>
                </div>
            </div>
        </>
    );
}
