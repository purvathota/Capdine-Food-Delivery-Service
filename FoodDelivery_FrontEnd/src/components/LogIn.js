import React, { useEffect, useState } from 'react';
import ReactModal from 'react-modal';
import './LogIn.css';
import axios from 'axios';
import { useNavigate } from 'react-router';
import { useAuth } from '../AuthContext';
import { Link } from 'react-router-dom';

import { BarLoader } from "react-spinners"; // Import your spinner component
 
const LogIn = ({ visible, toggleModal, setSignInVisible }) => {
    const { login } = useAuth();
    const navigate = useNavigate();
    const [otpSent, setOtpSent] = useState(false);
    const [otpVerifying, setOtpVerifying] = useState(false);
    const [otp, setOtp] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [errorTimeout, setErrorTimeout] = useState(null); // State to store the error timeout

    const clearErrorMessage = () => {
        if (errorTimeout) {
            clearTimeout(errorTimeout);
        }
        setErrorMessage('');
        setErrorTimeout(null);
    };

    const handleSendOtp = async (e) => {
        e.preventDefault();
        clearErrorMessage(); // Clear any existing errors first

        try {
            const email = document.getElementById('email').value;
            setOtpVerifying(true);
            const response = await axios.post('http://localhost:8085/api/otp/request', { email });
            if (response.status === 200) {
                setOtpSent(true);
            }
        } catch (error) {
            const errorMessage = error.response ? error.response.data : 'Error sending OTP';
            setErrorMessage(errorMessage);
            const timeout = setTimeout(() => {
                clearErrorMessage();
            }, 3000); // Clear the error message after 5 seconds
            setErrorTimeout(timeout);
        } finally {
            setOtpVerifying(false);
        }
    };

    const handleVerifyOtp = async (e) => {
        e.preventDefault();
        clearErrorMessage(); // Clear any existing errors first

        const enteredOtp = document.getElementById('otp').value;
        const email = document.getElementById('email').value;
        setOtpVerifying(true);

        try {
            const response = await axios.post('http://localhost:8085/api/otp/verify', { email, otp: enteredOtp });
            if (response.status === 200) {
                const userInfo = response.data;
                const userData = {
                    name: userInfo.customer_name,
                    email: userInfo.customer_email,
                    id: userInfo.customer_id,
                    phone: userInfo.customer_phone,
                    cartItems: []
                };
                login(userData);
                handleCloseModal();
                navigate('/restaurants');
            }
        } catch (error) {
            const errorMessage = error.response ? error.response.data : 'Error verifying OTP';
            setErrorMessage(errorMessage);
            const timeout = setTimeout(() => {
                clearErrorMessage();
            }, 3000); // Clear the error message after 5 seconds
            setErrorTimeout(timeout);
        } finally {
            setOtpVerifying(false);
        }
    };

    useEffect(() => {
        return () => {
            if (errorTimeout) {
                clearTimeout(errorTimeout);
            }
        };
    }, [errorTimeout]); // Cleanup on component unmount
 
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
 
    const handleSignUpClick = (e) => {
        e.preventDefault();
        handleCloseModal();
        setSignInVisible(true);
    };
 
    return (
        <ReactModal
            isOpen={visible}
            onRequestClose={handleCloseModal}
            className={visible ? "modal-content active" : "modal-content"}
            overlayClassName={visible ? "modal-overlay active" : "modal-overlay"}
        >
            <div className="modal-header">
                <h1>Log In</h1>
                <button className="close-btn" onClick={handleCloseModal}>×</button>
            </div>
            <div className="modal-body">
                <form>
                    <div className="form-group">
                        <input type="text" id="email" placeholder="Email Id" />
                    </div>
 
                    {otpSent && (
                        <div className="form-group">
                            <input type="text" id="otp" placeholder="Enter OTP" />
                        </div>
                    )}
                    <div className="form-group">
                        {otpSent ? (
                            <button className="submit-btn" onClick={handleVerifyOtp}>
                                {otpVerifying ? <BarLoader color={"white"} /> : "Verify OTP"}
                            </button>
                        ) : (
                            <button className="submit-btn" onClick={handleSendOtp}>
                                {otpVerifying ? <BarLoader color={"white"} /> : "Send OTP"}
                            </button>
                        )}
                    </div>
                    {errorMessage && <h5 style={{ color: '#f00', padding: '1rem' }}>{errorMessage}</h5>}
                </form>
                <p>
                    Don't have an account?{' '}
                    <Link onClick={handleSignUpClick} style={{ textDecoration: 'none', color: '#e48f0f' }}>
                        <span>Sign Up</span> {/* Add a span element */}
                    </Link>
                </p>
            </div>
        </ReactModal>
    );
};
 
export default LogIn;
 