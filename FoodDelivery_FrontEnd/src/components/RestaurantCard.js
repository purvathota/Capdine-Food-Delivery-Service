import React, { useState } from 'react';
import './Restaurants.css';
import axios from 'axios';
import { useNavigate } from 'react-router';
 
const RestaurantCard = ({ restaurant, image }) => {
 
  const navigate = useNavigate();
  const [menuItems, setMenuItems] = useState([]); // State to store menu items
 
  const fetchMenuAndNavigate = async (restaurantId) => {
    console.log(`Fetching menu for restaurant ID: ${restaurantId}`);
    try {
      const response = await axios.get(`http://localhost:8085/api/restaurants/${restaurantId}/menu`);
      console.log('Menu data received:', response.data);
      setMenuItems(response.data); // Set the menu items state
      navigate('/menuCard', { state: { menuItems: response.data, restaurantId } }); // Navigate to the MenuCard route
    } catch (error) {
      console.error('Error fetching menu:', error);
    }
  };
 
 
  return (
    <div className="restaurant-card">
      <img src={image} alt={restaurant.restaurant_name} className="restaurant-image" />
      <div className="restaurant-info">
        <h3 className="restaurant-name">{restaurant.restaurant_name}</h3>
        <p className="restaurant-address">{restaurant.restaurant_address}</p>
        <p className="restaurant-phone">{restaurant.restaurant_phone}</p>
        <button className="explore-menu-btn"  onClick={() => fetchMenuAndNavigate(restaurant.restaurant_id)}>Explore Menu</button>
      </div>
    </div>
  );
};
 
export default RestaurantCard;