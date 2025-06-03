import React, { useState, useEffect } from 'react';
import axios from 'axios';
import RestaurantCard from './RestaurantCard';
import './Restaurants.css';
import { debounce } from 'lodash';

const API_KEY = process.env.REACT_APP_PIXABAY;

const Restaurants = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [images, setImages] = useState([]);
  const [visibleCards, setVisibleCards] = useState(6);
  const [loading, setLoading] = useState(false);
  const [hasMoreCards, setHasMoreCards] = useState(true);
  const [showTopBtn, setShowTopBtn] = useState(false);

  useEffect(() => {
    const fetchRestaurants = async () => {
      try {
        const res = await axios.get('http://localhost:8085/api/restaurants/');
        setRestaurants(res.data);
        if (res.data.length <= 6) setHasMoreCards(false);
      } catch (err) {
        console.error('Error fetching restaurants:', err);
      }
    };

    const fetchImages = async () => {
      try {
        const res = await axios.get(`https://pixabay.com/api/?key=${API_KEY}&q=restaurant&image_type=photo&per_page=60`);
        setImages(res.data.hits.map(hit => hit.webformatURL));
      } catch (err) {
        console.error('Error fetching images:', err);
      }
    };

    fetchRestaurants();
    fetchImages();
  }, []);

  const getImageForRestaurant = (restaurantId) => {
    const totalImages = images.length;
    if (totalImages === 0) return '/images/default-restaurant.jpg';
    const index = ((restaurantId - 1) % totalImages);
    return images[index];
  };

  const handleScroll = debounce(() => {
    const { scrollTop, clientHeight, scrollHeight } = document.documentElement;
    setShowTopBtn(scrollTop > 500);
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
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  return (
    <div className="restaurants-container">
      {restaurants.slice(0, visibleCards).map((restaurant) => (
        <RestaurantCard
          key={restaurant.restaurant_id}
          restaurant={restaurant}
          image={getImageForRestaurant(restaurant.restaurant_id)}
        />
      ))}
      {loading && <div className="loading-spinner"><div className="spinner"></div></div>}
      {!hasMoreCards && <p>No more restaurants to load.</p>}
      {showTopBtn && (
        <button onClick={scrollToTop} className="scroll-to-top">
          <b style={{ fontSize: "20px" }}>^</b>
        </button>
      )}
    </div>
  );
};

export default Restaurants;
