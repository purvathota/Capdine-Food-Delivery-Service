import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import './OrderDetails.css'; // Ensure this CSS file exists and is correctly styled

const OrderDetails = () => {
    const [order, setOrder] = useState(null);
    const [restaurant, setRestaurant] = useState(null);
    const [driver, setDriver] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const { orderId } = useParams();
    const location = useLocation();  // Using useLocation to access state passed in navigation
    const navigate = useNavigate();  // Setup the navigate function

    useEffect(() => {
        if (orderId) {
            fetchOrderDetails(orderId);
        }
    }, [orderId]);

    useEffect(() => {
        const driverId = location.state?.driverId;  // Accessing driverId from state passed during navigation
        if (driverId) {
            fetchDriverDetails(driverId);
        }
    }, [location.state]);

    const fetchOrderDetails = async (orderId) => {
        try {
            const response = await axios.get(`http://localhost:8085/api/orders/${orderId}`);
            setOrder(response.data);
            if (response.data.restaurant_id) {
                fetchRestaurantDetails(response.data.restaurant_id);
            } else {
                setLoading(false);
            }
        } catch (err) {
            setError('Failed to fetch order details');
            setLoading(false);
        }
    };

    const fetchRestaurantDetails = async (restaurantId) => {
        try {
            const response = await axios.get(`http://localhost:8085/api/restaurants/${restaurantId}`);
            setRestaurant(response.data);
        } catch (err) {
            setError('Failed to fetch restaurant details');
        } finally {
            setLoading(false);
        }
    };

    const fetchDriverDetails = async (driverId) => {
        try {
            const response = await axios.get(`http://localhost:8085/api/drivers/${driverId}`);
            setDriver(response.data);
        } catch (err) {
            setError('Failed to fetch driver details');
        }
    };

    const handleExploreRestaurants = () => {
        navigate('/restaurants');
    };

    if (loading) return <div className="loading">Loading...</div>;
    if (error) return <div className="error">Error: {error}</div>;

    return (
        <div className="container">
            <h2 className="header">Order Details</h2>
            {order ? (
                <div className="details">
                    <p className="detailItem"><strong>Order ID:</strong> {order.order_id}</p>
                    <p className="detailItem"><strong>Order Date:</strong> {order.order_date}</p>
                    <p className="detailItem"><strong>Restaurant Name:</strong> {restaurant ? restaurant.restaurant_name : 'Loading restaurant details...'}</p>
                    {order.order_status !== "Delivered" && (
                        <>
                            <p className="detailItem"><strong>Driver ID:</strong> {driver ? driver.driver_id : 'Loading driver details...'}</p>
                            <p className="detailItem"><strong>Driver Name:</strong> {driver ? driver.driver_name : 'Loading driver details...'}</p>
                        </>
                    )}
                    <p className="detailItem"><strong>Order Status:</strong> {order.order_status}</p>
                    {/* Button to navigate to the restaurants page */}
                    <button onClick={handleExploreRestaurants} className="exploreButton">
                        Explore More Restaurants
                    </button>
                </div>
            ) : (
                <p>No order details found.</p>
            )}
        </div>
    );
};

export default OrderDetails;
