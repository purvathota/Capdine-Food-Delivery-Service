import React, { useState, useEffect } from 'react';
import RestaurantCard from './RestaurantCard';
import './Restaurants.css';
import axios from 'axios';
import { faker } from '@faker-js/faker';
import { debounce } from 'lodash';
import MenuFooter from './menuFooter';
 
const Restaurants = () => {
  const [visibleCards, setVisibleCards] = useState(6);
  const [loading, setLoading] = useState(false);
  const [restaurants, setRestaurants] = useState([]);
  const [hasMoreCards, setHasMoreCards] = useState(true);
  const [showTopBtn, setShowTopBtn] = useState(false); // State to show or hide the top button
 
  faker.seed(99);
 
 
const generateRandomImage = () => {
  return faker.image.imageUrl(640, 480, 'restaurant', true);
};
 
  useEffect(() => {
    const fetchRestaurants = async () => {
      try {
        const response = await axios.get('http://localhost:8085/api/restaurants/');
        setRestaurants(response.data);
        if (response.data.length <= 6) {
          setHasMoreCards(false);
        }
      } catch (error) {
        console.error('Error fetching restaurants:', error);
      }
    };
 
    fetchRestaurants();
  }, []);
 
  const handleScroll = debounce(() => {
    const { scrollTop, clientHeight, scrollHeight } = document.documentElement;
 
    // Show or hide the top button
    if (scrollTop > 500) {
      setShowTopBtn(true);
    } else {
      setShowTopBtn(false);
    }
 
    if (scrollTop + clientHeight >= scrollHeight - 300 && !loading && hasMoreCards) {
      loadMoreCards();
    }
  }, 100);
 
  const loadMoreCards = () => {
    setLoading(true);
    setTimeout(() => {
      const newVisibleCards = visibleCards + 6;
      setVisibleCards(newVisibleCards);
      if (newVisibleCards >= restaurants.length) {
        setHasMoreCards(false);
      }
      setLoading(false);
    }, 2000);
  };
 
  useEffect(() => {
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, [handleScroll]);
 
  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    });
  };
 
  return (
    <div className="restaurants-container">
      {restaurants.slice(0, visibleCards).map((restaurant) => (
        <RestaurantCard key={restaurant.restaurant_id} restaurant={restaurant} image={generateRandomImage()}/>
      ))}
      {loading && (
        <div className="loading-spinner">
          <div className="spinner"></div>
        </div>
      )}
      {!hasMoreCards && <p>No more restaurants to load.</p>}
      {showTopBtn && (
        <button onClick={scrollToTop} className="scroll-to-top">
          <b style={{fontSize:"20px"}}>^</b>
        </button>
      )}
    </div>
  );
};
 
export default Restaurants;
 