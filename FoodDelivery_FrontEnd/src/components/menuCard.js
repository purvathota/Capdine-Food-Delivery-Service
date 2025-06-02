import React, { useState, useEffect } from "react";
import "./menuCard.css";
import { useLocation } from "react-router-dom";
import MenuFooter from "./menuFooter";
import { useAuth } from '../AuthContext';
import { Button } from "react-bootstrap";
 
const MenuCard = ({ setLogInWarningVisible }) => {
    const location = useLocation(); // Use useLocation to access the current location object
    const { menuItems, restaurantId } = location.state || { menuItems: [], restaurantId: null };
    const { user, addToCart, removeFromCart } = useAuth();
    const [showCartPopup, setShowCartPopup] = useState(false);
    const [removedItemName, setRemovedItemName] = useState(null);
    const [addedItemIds, setAddedItemIds] = useState([]);
    const [addedItemName, setAddedItemName] = useState(false);
 
    useEffect(() => {
        // Initialize addedItemIds with the IDs of items already in the user's cart
        if (user) {
            const cartItemIds = user.cartItems.map(cartItem => cartItem.item_id);
            setAddedItemIds(cartItemIds);
        }
    }, [user]);
 
    const handleAddClick = (item) => {
        if (!user) {
            setLogInWarningVisible(true);
        } else {
            addToCart({
                item_id: item.item_id,
                item_name: item.item_name,
                restaurant_id: item.restaurant_id,
                item_price: item.item_price,
                quantity: 1
            });
            setAddedItemIds([...addedItemIds, item.item_id]); // Update addedItemIds
            setAddedItemName(item.item_name);
            setShowCartPopup(true); // Show the popup
            setTimeout(() => setShowCartPopup(false), 3000); // Hide the popup after 3 seconds
        }
    };
 
    const handleRemoveClick = (item) => {
        removeFromCart(item.item_id);
        setRemovedItemName(item.item_name); // Set the name of the removed item
        setTimeout(() => setRemovedItemName(null), 3000); // Clear removedItemName after 3 seconds
        setAddedItemIds(addedItemIds.filter(id => id !== item.item_id)); // Update addedItemIds
    };
 
    return (
        <div className="restaurantContainer">
            <img
                src="https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg"
                alt="restaurant"
                className="restaurantImage"
                style={{ marginBottom: "1rem" }}
            />
            <div className="restaurantsHeader">
                <p className="menuSubTitle">DAILY SPECIALS</p>
            </div>
            {menuItems.map((item, index) => (
                <div key={index} className="menuCardContainer">
                    <div className="itemContainer">
                        <div className="topBorder" />
                        <div style={{ marginTop: "5px" }}>
                            <span className="itemTitle">{item.item_name}</span>
                        </div>
                        <div>
                            <span className="itemDescription">{item.item_description}</span>
                        </div>
                    </div>
                    <div className="priceContainer">
                        <span className="itemPrice">&#8377;{item.item_price}</span>
                        <div className="addBar">
                            {addedItemIds.includes(item.item_id) ? (
                                <>
                                    <button style={{backgroundColor:"red"}} className="remove-button" onClick={() => handleRemoveClick(item)}>Remove</button>
                                    <button className="added-button" disabled>Added</button>
                                </>
                            ) : (
                                <button onClick={() => handleAddClick(item)}>Add</button>
                            )}
                        </div>
                    </div>
                </div>
            ))}
            <MenuFooter restaurantId={restaurantId} />
 
            {showCartPopup && (
                <div className="cart-popup"> {addedItemName} added to cart!</div>
            )}
 
            {removedItemName && (
                <div className="cart-popup">{removedItemName} removed from cart!</div>
            )}
 
        </div>
    );
};
 
export default MenuCard;
 