import "./App.css";
import React, { useState } from "react";
import Home from "./components/Home";

import AddRestaurant from "./components/AddRestaurant";
import { BrowserRouter, Routes, Route, Router, useNavigate, Navigate } from "react-router-dom";
import LogIn from "./components/LogIn";
import SignIn from "./components/SignIn";
// import RestaurantPage from "./components/RestaurantPage";
import Navbar from "./components/Navbar";
import { useAuth } from "./AuthContext";
import MenuCard from "./components/menuCard";
import Cart from "./components/Cart";
import Restaurants from "./components/Restaurants";
import LoginWarning from "./components/LoginWarning";
import OrderDetails from "./components/OrderDetails";
import Video from "./components/Video";
import LearnMore from "./components/LearnMore";

const App = () => {
  const { user } = useAuth();
  const [logInVisible, setLogInVisible] = useState(false);
  const [signInVisible, setSignInVisible] = useState(false);
  const [addRestaurantVisible, setAddRestaurantVisible] = useState(false);
  const [logInWarningVisible, setLogInWarningVisible] = useState(false);
  return (
    <BrowserRouter>
      <div className="App container-fluid">
        {logInVisible && <LogIn visible={logInVisible} toggleModal={() => setLogInVisible(false)} setSignInVisible={setSignInVisible}/>}
        {signInVisible && <SignIn visible={signInVisible} toggleModal={() => setSignInVisible(false)} setLogInVisible={setLogInVisible} />}
        {addRestaurantVisible && <AddRestaurant visible={addRestaurantVisible} toggleModal={() => setAddRestaurantVisible(false)} />}
        {logInWarningVisible && <LoginWarning visible={logInWarningVisible} toggleModal={() => setLogInWarningVisible(false)} setLogInVisible={setLogInVisible}/>}

        <Navbar setLogInVisible={setLogInVisible} setSignInVisible={setSignInVisible} setAddRestaurantVisible={setAddRestaurantVisible} />
        <Routes>
          <Route path="/" element={<Home setLogInVisible={setLogInVisible} setSignInVisible={setSignInVisible} setAddRestaurantVisible={setAddRestaurantVisible} />} />
          {/* <Route path="/restaurantPage" element={user ? <RestaurantPage /> : <Navigate to="/" />} /> */}
          {/* <Route path="/restaurantPage" element={<RestaurantPage  />} /> */}
          <Route path="/menuCard" element={<MenuCard setLogInWarningVisible={setLogInWarningVisible}/>} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/restaurants" element={<Restaurants />} />
          <Route path="/order/:orderId" element={<OrderDetails/>} />
          <Route path="/LearnMore" element={<LearnMore/>} />
          <Route path="/Video" element={<Video/>} />
        </Routes>

      </div>
    </BrowserRouter>
  );
}

export default App;

