import React from 'react';
import cl from './Modal.module.css'

const Modal = ({message, buttonText, visible, setVisible}) => {
    const rootClasses = [cl.background]

    if (visible) {
        rootClasses.push(cl.active)
    }

    return (
        <div className={rootClasses.join(' ')} onClick={() => setVisible(false)}>
            <div className={cl.modal} onClick={(e) => e.stopPropagation()}>
                <div>{message}</div>
                <div
                    className={cl.button}
                    onClick={() => setVisible(false)}
                >
                    {buttonText}</div>
            </div>
        </div>
    );
};

export default Modal;