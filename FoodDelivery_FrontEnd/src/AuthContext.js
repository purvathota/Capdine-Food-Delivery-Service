import React, { createContext, useContext, useState, useEffect } from 'react';
 
const AuthContext = createContext(null);
const getInitialUser = () => {
  const storedUser = sessionStorage.getItem('user');
  return storedUser ? JSON.parse(storedUser) : null;
};
 
export const AuthProvider = ({ children }) => {
  // const [user, setUser] = useState(() => {
  //   return JSON.parse(sessionStorage.getItem('user')) || null;
  // });
 
  const [user, setUser] = useState(getInitialUser);
 
 
 
  const clearCart = () => {
    updateUser({ cartItems: [] });
};
 
 
  const login = (userData) => {
    sessionStorage.setItem('user', JSON.stringify(userData));
    setUser(userData);
  };
 
  const logout = () => {
    sessionStorage.removeItem('user');
    setUser(null);
  };
 
  const updateUser = (updates) => {
    const updatedUser = { ...user, ...updates };
    sessionStorage.setItem('user', JSON.stringify(updatedUser));
    setUser(updatedUser);
  };
 
  const addToCart = (item) => {
    let found = user.cartItems.find(cartItem => cartItem.item_id === item.item_id);
    if (found) {
      found.quantity += 1;
    } else {
      item.quantity = 1;
      user.cartItems.push(item);
    }
    updateUser({ cartItems: [...user.cartItems] });
  };
 
  const removeFromCart = (itemId) => {
    const updatedCart = user.cartItems.filter(item => item.item_id !== itemId);
    updateUser({ cartItems: updatedCart });
  };
 
  const getTotalPrice = () => {
    return user.cartItems.reduce((total, item) => {
      return total + (item.item_price * item.quantity);
    }, 0);
  };
 
  const incrementItemQuantity = (itemId) => {
    const updatedCart = user.cartItems.map(item => {
      if (item.item_id === itemId) {
        return { ...item, quantity: item.quantity + 1 };
      }
      return item;
    });
    updateUser({ cartItems: updatedCart });
  };
 
  const decrementItemQuantity = (itemId) => {
    const updatedCart = user.cartItems.map(item => {
      if (item.item_id === itemId) {
        return { ...item, quantity: Math.max(0, item.quantity - 1) };
      }
      return item;
    }).filter(item => item.quantity > 0);
    updateUser({ cartItems: updatedCart });
  };
 
  useEffect(() => {
    const storedUser = sessionStorage.getItem('user');
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);
 
 
  return (
    <AuthContext.Provider value={{ user, login, logout, addToCart, removeFromCart,incrementItemQuantity,
      decrementItemQuantity, getTotalPrice, clearCart }}>
      {children}
    </AuthContext.Provider>
  );
};
 
export const useAuth = () => useContext(AuthContext);
 