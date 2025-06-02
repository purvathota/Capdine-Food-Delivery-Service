import React from 'react'
import './OrderCompleted.css';

const OrderCompleted = () => {
  return (
    <div className="success-animation">
    <svg className="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
        <circle className="checkmark__circle" cx="26" cy="26" r="25" fill="none"/>
        <path className="checkmark__check" fill="none" d="M14 27 L22 34 L39 17"/>
    </svg>
    <h1>Order Completed Successfully!</h1>
</div>
  )
}

export default OrderCompleted
