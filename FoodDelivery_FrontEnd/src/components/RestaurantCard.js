import React, { useState } from 'react';
import './Restaurants.css';
import axios from 'axios';
import { useNavigate } from 'react-router';

const RestaurantCard = ({ restaurant, image }) => {
  const navigate = useNavigate();
  const [menuItems, setMenuItems] = useState([]);

  const fetchMenuAndNavigate = async (restaurantId) => {
    try {
      const response = await axios.get(`http://localhost:8085/api/restaurants/${restaurantId}/menu`);
      setMenuItems(response.data);
      navigate('/menuCard', { state: { menuItems: response.data, restaurantId } });
    } catch (error) {
      console.error('Error fetching menu:', error);
    }
  };

  return (
    <div className="restaurant-card">
      <img
        src={image}
        alt={restaurant.restaurant_name}
        className="restaurant-image"
        onError={(e) => {
          e.target.onerror = null;
          e.target.src = '/images/default-restaurant.jpg'; // fallback image
        }}
      />
      <div className="restaurant-info">
        <h3 className="restaurant-name">{restaurant.restaurant_name}</h3>
        <p className="restaurant-address">{restaurant.restaurant_address}</p>
        <p className="restaurant-phone">{restaurant.restaurant_phone}</p>
        <button className="explore-menu-btn" onClick={() => fetchMenuAndNavigate(restaurant.restaurant_id)}>
          Explore Menu
        </button>
      </div>
    </div>
  );
};

export default RestaurantCard;
