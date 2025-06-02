import React, { useState, useEffect } from 'react';
import './Cart.css';
import { BsCart2 } from "react-icons/bs";
import { useAuth } from '../AuthContext';
import axios from 'axios';
import loadScript from 'load-script';
import OrderCompleted from './OrderCompleted';
import PaymentFailed from './PaymentFailed';
import { useNavigate } from 'react-router';
import OrderDetails from './OrderDetails';
import { Button } from 'react-bootstrap';

function Cart() {
    const { user, removeFromCart, incrementItemQuantity, decrementItemQuantity, getTotalPrice, clearCart } = useAuth();
    const [orderCompleted, setOrderCompleted] = useState(false);
    const [isCheckingOut, setIsCheckingOut] = useState(false);
    const [paymentFailed, setPaymentFailed] = useState(false);
    const [orderId, setOrderId] = useState(null);
    const [selectedCouponId, setSelectedCouponId] = useState('');
    const [discountAmount, setDiscountAmount] = useState(0);
    const [driverId, setDriverId] = useState(null);
    const [coupons, setCoupons] = useState([]); // State to store coupons
    const navigate = useNavigate();
    const [discountedTotal, setDiscountedTotal] = useState(getTotalPrice())

    useEffect(() => {
        fetchCoupons();
    }, []);

    const fetchCoupons = async () => {
        try {
            const response = await axios.get('http://localhost:8085/api/coupons/');
            const randomCoupons = selectRandomCoupons(response.data, 4);
            setCoupons(randomCoupons);
            console.log(response.data);
        } catch (error) {
            console.error('Failed to fetch coupons:', error);
        }
    };

    // Selects a specified number of random coupons
    const selectRandomCoupons = (coupons, count) => {
        return coupons
            .sort(() => 0.5 - Math.random()) // Shuffle the array
            .slice(0, count); // Get the first 'count' elements
    };

    const handleCouponChange = (e) => {
        const selectedId = e.target.value;
        console.log(selectedId);
        setSelectedCouponId(selectedId);
        const coupon = coupons.find(c => c.coupon_id == selectedId);
        console.log(coupon);
        const discount = coupon ? coupon.discount_amount : 0;
        console.log(discount);

        setDiscountAmount(discount);
        setDiscountedTotal(getTotalPrice() - discount);
    };


    const handleClickOnDetails = () => {
        navigate(`/order/${orderId}`, { state: { driverId: driverId } });
    }


    // Save order details once payment is confirmed and orderId is set
    useEffect(() => {
        if (orderId && orderCompleted) {
            saveOrderDetails();
        }
    }, [orderId, orderCompleted]);

    const getRandomNumber = (min, max) => {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };

    const fetchRandomDriver = async () => {
        try {
            const response = await axios.get('http://localhost:8085/api/drivers/');
            console.log('Response data:', response.data); // Log the entire response data

            const drivers = response.data; // Store all drivers
            const availableDrivers = drivers.filter(driver => driver.driver_available !== 0);
            console.log('Available drivers:', availableDrivers); // Log the available drivers

            if (availableDrivers.length === 0) {
                console.log("No available drivers found.");
                // Handle this case - maybe display a message to the user
                return null;
            }

            const randomNumber = getRandomNumber(0, availableDrivers.length - 1);
            const selectedDriver = availableDrivers[randomNumber];
            const driverId = selectedDriver.driver_id;

            console.log("Selected Driver ID:", driverId);
            setDriverId(driverId);
            return driverId;
        } catch (error) {
            console.error('Error fetching drivers:', error);
            return null;
        }
    };

    const updateDriver = async (orderId, driverId) => {
        const endpoint = `http://localhost:8085/api/drivers/updateDriver/${orderId}/${driverId}`;

        try {
            const response = await axios.put(endpoint);
            console.log('Driver updated successfully:', response.data);
        } catch (error) {
            console.error('Error updating driver:', error);
        }
    };

    const handleCheckout = async () => {
        setIsCheckingOut(true);
        const products = user.cartItems.map(item => ({
            item_id: item.item_id,
            item_name: item.item_name,
            item_price: item.item_price,
            quantity: item.quantity,
            restaurant_id: item.restaurant_id
        }));

        try {
            var totalToSend = getTotalPrice();
            if (selectedCouponId) {
                totalToSend = discountedTotal;
            }

            const { data: paymentOptions } = await axios.post('http://localhost:8085/api/checkout', { total: totalToSend });


            loadScript('https://checkout.razorpay.com/v1/checkout.js', (err) => {
                if (err) {
                    console.error('Failed to load Razorpay SDK:', err);
                    setIsCheckingOut(false);
                    setPaymentFailed(true);
                    return;
                }
                initiatePayment(paymentOptions);
            });
        } catch (error) {
            console.error('Checkout failed:', error);
            setIsCheckingOut(false);
            setPaymentFailed(true);
        }
    };

    const initiatePayment = (paymentOptions) => {
        const options = {
            ...paymentOptions,
            handler: async (response) => {
                console.log(paymentOptions);
                setOrderId(paymentOptions.orderId);
                console.log('Payment successful:', response);
                // saveOrderDetails(response);
                setOrderCompleted(true);
                clearCart();
            },
            modal: {
                ondismiss: () => {
                    console.log('User closed payment gateway');
                    setPaymentFailed(true);
                }
            },
            prefill: {
                name: user.name,
                email: user.email,
                contact: user.phone,
            },
        };

        const razorpay = new window.Razorpay(options);
        razorpay.open();
        razorpay.on('payment.failed', function (response) {
            console.error('Payment Failed:', response);
            setPaymentFailed(true);
        });
    };

    const saveOrderDetails = async () => {
        // Check if there are any cart items before proceeding
        if (!user.cartItems || user.cartItems.length === 0) {
            console.error("No items in the cart to save order details for.");
            setPaymentFailed(true); // Consider handling this more gracefully
            return;
        }

        const driverId = await fetchRandomDriver();
        if (!driverId) {
            console.error("No available drivers found.");
            setPaymentFailed(true); // Consider handling this more gracefully
            return;
        }

        const order = {
            order_id: orderId,
            order_status: 'Pending',
            driver_id: driverId,
            customer_id: user.id,
            restaurant_id: parseInt(user.cartItems[0].restaurant_id)
            // Add other fields if necessary
        };

        console.log(order);
        const endpoint = `http://localhost:8085/api/orders/restaurant/${order.restaurant_id}/driver/${order.driver_id}/customer/${order.customer_id}`;
        console.log(endpoint);

        try {
            await axios.post(endpoint, order);
            updateDriver(orderId, driverId);
            console.log("Order details saved successfully!");

        } catch (error) {
            console.error("Failed to save order details:", error);
            setPaymentFailed(true); // Consider handling this more gracefully
        }
    };

    if (orderCompleted) {
        return (
            <>
                <OrderCompleted />
                {/* <p>Your Order ID is: {orderId}</p> */}
                <Button className="exploreButton" onClick={handleClickOnDetails}>Go to Details</Button>
            </>
        );
    }

    if (paymentFailed) {
        var totalToSend = getTotalPrice();
        if (selectedCouponId) {
            totalToSend = discountedTotal;
        }
        return <PaymentFailed cartItems={user.cartItems} getTotalPrice={totalToSend} retryPayment={handleCheckout} />;
    }

    return (
        <div className="cart-container">
            <h2>Your Cart&nbsp;&nbsp;<BsCart2 /></h2>
            <div className="cart-items">
                {user.cartItems.map(item => (
                    <div key={item.item_id} className="cart-item">
                        <div className="product-details">
                            <div>
                                <h3>{item.item_name}</h3>
                                <p>&#8377;{item.item_price.toFixed(2)}</p>
                            </div>
                            <div className="quantity-control">
                                <button className="q-btn" onClick={() => decrementItemQuantity(item.item_id)}>-</button>
                                <span>{item.quantity}</span>
                                <button className="q-btn" onClick={() => incrementItemQuantity(item.item_id)}>+</button>
                                <button className="delete-button" onClick={() => removeFromCart(item.item_id)}>Delete</button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            {getTotalPrice() > 100 && (
                <div>
                    <label htmlFor="coupon">Apply Coupon:</label>
                    <select id="coupon" name="coupon" className="coupon-select" value={selectedCouponId} onChange={handleCouponChange}>
                        <option value="">Select Coupon</option>
                        {coupons.map((coupon, index) => (
                            <option key={index} value={coupon.coupon_id}>
                                {coupon.coupon_code} - &#8377;{coupon.discount_amount} DISCOUNT
                            </option>
                        ))}
                    </select>
                </div>
            )}
            <div>
                <h3>Total: &#8377;{getTotalPrice().toFixed(2)}</h3>
                {selectedCouponId &&
                    <>
                        <h4>Discount: -&#8377;{discountAmount.toFixed(2)}</h4>
                        <h4>Discounted Total: &#8377;{discountedTotal.toFixed(2)}</h4></>}
            </div>
            <button disabled={isCheckingOut || user.cartItems.length === 0} onClick={handleCheckout} className="checkout-button">Checkout</button>
        </div>
    );
}

export default Cart;