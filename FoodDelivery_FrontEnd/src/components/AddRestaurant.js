import React from 'react';
import ReactModal from 'react-modal';
import './AddRestaurant.css'; // Import CSS for styling

const AddRestaurant = ({ visible, toggleModal }) => {
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

    return (
        <ReactModal
            isOpen={visible}
            onRequestClose={handleCloseModal}
            className={visible ? "modal-content active" : "modal-content"}
            overlayClassName={visible ? "modal-overlay active" : "modal-overlay"}
        >
            <div className="modal-header">
                <h1>Add Restaurant</h1>
                <button className="close-btn" onClick={handleCloseModal}>×</button>
            </div>
            <div className="modal-body">
                <form>
                    <p style={{color:'rgb(79, 79, 79)'}}><i>Upload Your Restaurant Image:</i></p>
                    <div className="form-group">
                    <input
                            type="file"
                            id="image"
                            name="image"
                            accept="image/*"
                            
                        />
                    </div>
                    <div className="form-group">
                        <input type="text" id="restaurantName" placeholder="Restaurant Name" />
                    </div>
                    <div className="form-group">

                        <input type="text" id="address" placeholder="Restaurant Address" />
                    </div>
                    <div className="form-group">

                        <textarea id="phoneNo" rows="3" placeholder="Restaurant Phone Number"></textarea>
                    </div>
                    <div className="form-group">
                        <button className="submit-btn" type="submit">Add Restaurant</button>
                    </div>
                </form>
            </div>
        </ReactModal>
    );
};

export default AddRestaurant;
