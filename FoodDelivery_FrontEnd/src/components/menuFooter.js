import React, { useState, useEffect } from "react";
import "./menuFooter.css";
import Logo from "../components/capdine-logo.png";
 
const MenuFooter = ({ restaurantId }) => {
  const [restaurantData, setRestaurantData] = useState({});
 
  useEffect(() => {
    if (!restaurantId) return; // If restaurantId is not provided, do nothing
 
    // Fetch restaurant data from your backend API using the provided restaurantId
    fetch(`http://localhost:8085/api/restaurants/${restaurantId}`)
      .then((response) => response.json())
      .then((data) => {
        setRestaurantData(data); // Assuming your API returns an object with keys 'restaurant_name', 'restaurant_phone', and 'restaurant_address'
      })
      .catch((error) => {
        console.error("Error fetching restaurant data:", error);
      });
  }, [restaurantId]); // Run the effect whenever restaurantId changes
 
  return (
    <div className="menuFooterContainer">
      <div className="logoNameContainer">
        <img
          src={Logo}
          alt="logo"
          className="companyLogo"
        />
        <div style={{ marginLeft: "10px" }}>
          {/* Display restaurant data only if it exists */}
          {restaurantData && (
            <div>
              <span className="logo">Name: {restaurantData.restaurant_name}</span>
            </div>
          )}
        </div>
      </div>
 
      {/* Display phone and address if restaurantData exists */}
      {restaurantData && (
        <div>
          <span className="phone">Phone: {restaurantData.restaurant_phone}</span>
        </div>
      )}
      {restaurantData && (
        <div className="address">
          <span>Address: {restaurantData.restaurant_address}</span>
        </div>
      )}
    </div>
  );
};
 
export default MenuFooter;
 