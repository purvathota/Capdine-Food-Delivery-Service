import React from 'react';
import { BsXCircle } from "react-icons/bs";
import './PaymentFailed.css';
import { Button } from 'react-bootstrap';
import './Cart.css';

function PaymentFailed({ cartItems, getTotalPrice, retryPayment }) {
    return (
        <div className="payment-failed-container">
            <div className="payment-failed-icon">
                <BsXCircle size={60} color="red" className="animated-icon" />
            </div>
            <h1>Payment Failed</h1>
            <p>Please review your cart and try again.</p>
            <div className="cart-details">
                {cartItems.map(item => (
                    <div key={item.item_id} className="cart-item">
                        <div className="product-details">
                            <div>
                                <h3>{item.item_name}</h3>
                                <p>Price: &#8377;{item.item_price.toFixed(2)}</p>
                                <p>Quantity: {item.quantity}</p>
                            </div>
                        </div>
                    </div>
                ))}
                <h2>Total: &#8377;{getTotalPrice}</h2>
            </div>
            <Button variant="danger" onClick={retryPayment}>Retry Payment</Button>
        </div>
    );
}

export default PaymentFailed;
