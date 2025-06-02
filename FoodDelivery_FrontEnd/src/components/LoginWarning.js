import React, { useState } from 'react';
import ReactModal from 'react-modal';
import './LogIn.css';
import { Button } from 'react-bootstrap';
import LogIn from './LogIn';


// Import your CSS file for styling

const LoginWarning = ({ visible, toggleModal, setLogInVisible }) => {


    const handleCloseModal = () => {
        // Remove 'active' class from modal content to trigger slide-up animation
        const modalContent = document.querySelector('.modal-content');
        modalContent.classList.remove('active');

        // Add 'slide-out' class to close button to trigger slide-out animation
        const closeButton = document.querySelector('.modal-content');
        modalContent.classList.add('slide-out');

        // Delay closing the modal after the animation completes
        setTimeout(() => {
            toggleModal();
            // Remove 'slide-out' class after animation completes to reset for next opening
            modalContent.classList.remove('slide-out');
        }, 300); // Adjust the timeout based on animation duration
    };
    const handleLogInClick = (e) => {
        e.preventDefault();
        handleCloseModal();
        setLogInVisible(true);
    };


    return (
        <ReactModal
            isOpen={visible}
            onRequestClose={handleCloseModal}
            className={visible ? "modal-content active" : "modal-content"}
            overlayClassName={visible ? "modal-overlay active" : "modal-overlay"}
        >
            <div className="modal-header">
                <h1>Log In first to explore more!</h1>
                <button className="close-btn" onClick={handleCloseModal}>×</button>
            </div>
            <div className="modal-body">
                <p>
                    <Button onClick={handleLogInClick} style={{background: "border-box  #fe9e0d", borderColor:  "#fe9e0d"}}>
                        <span>Log In</span> {/* Add a span element */}
                    </Button>
                </p>
            </div>
        </ReactModal>
    );
};

export default LoginWarning;
