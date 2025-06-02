import React, { useState } from 'react';
import ReactModal from 'react-modal';
import './LogIn.css'; // Import CSS for styling
import axios from 'axios'; // Import axios for making HTTP requests
import { Link } from 'react-router-dom';
 
const SignIn = ({ visible, toggleModal, setLogInVisible }) => {
    const [formData, setFormData] = useState({
        customer_name: '',
        customer_email: '',
        customer_phone: '',
    });
   
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
 
    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.id]: e.target.value });
    };
 
    const handleSubmit = async () => {
   
    try {
        const response = await axios.post('http://localhost:8085/api/customers/', formData);
        console.log(response.data);
   
        // Alert after successful registration
        window.alert(response.data);
        handleCloseModal();
    } catch (error) {
        // If there's an error, display the error message from the response data
        if (error.response && error.response.data) {
            window.alert(error.response.data);
        } else {
            // If there's no response data, display a generic error message
            window.alert('An error occurred while processing your request.');
        }
    }
};
 
    return (
        <ReactModal
            isOpen={visible}
            onRequestClose={handleCloseModal}
            className={visible ? "modal-content active" : "modal-content"}
            overlayClassName={visible ? "modal-overlay active" : "modal-overlay"}
        >
            <div className="modal-header">
                <h1>Register</h1>
                <button className="close-btn" onClick={handleCloseModal}>×</button>
            </div>
            <div className="modal-body">
                <form>
                    <div className="form-group">
                        <input type="text" id="customer_name" placeholder="Enter Name" onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <input type="number" id="customer_phone" placeholder="Enter Phone Number" onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <input type="email" id="customer_email" placeholder="Enter Email" onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <button className="submit-btn" type="button" onClick={handleSubmit}>Sign In</button>
                    </div>
                </form>
                <p>
                    Already have an account?{' '}
                    <Link onClick={handleLogInClick} style={{ textDecoration: 'none', color: '#e48f0f' }}>
                        <span>Log In</span> {/* Add a span element */}
                    </Link>
                </p>
            </div>
        </ReactModal>
    );
};
 
export default SignIn;
 