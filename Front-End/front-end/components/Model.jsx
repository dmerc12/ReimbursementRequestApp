
const Modal = ({ visible, onClose, children }) => {
    if (!visible) return null;

    const handleClose = (e) => {
        if ( e.target.id === 'wrapper' ) onClose();
    }

    return (
        <>
            <div className="fixed inset-0 bg-black bg-opacity-25 backdrop-blur-lg flex justify-center items-center z-0" id="wrapper" onClick={handleClose}>
                <div className="w-[600px] flex flex-col">
                <button className="text-white text-xl place self-end" onClick={() => onClose()}>x</button>
                    <div className="bg-black p-2 rounded">
                        {children}
                    </div>
                </div>
            </div>
        </>
    )
}

export default Modal;