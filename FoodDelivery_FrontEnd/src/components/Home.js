import React from "react";
import BannerBackground from "../components/home-banner-background.png";
import BannerImage from "../components/home-banner-image.png";
import Navbar from "./Navbar";
import { FiArrowRight } from "react-icons/fi";
import { useUser } from "../AuthContext";
import About from "./About";
import Testimonial from "./Testimonial";
import Contact from "./Contact";
import Footer from "./Footer";
import { useNavigate } from "react-router";

const Home = ({setLogInVisible, setSignInVisible, setAddRestaurantVisible}) => {

  const navigate = useNavigate();
  
  return (
    <>
    <div className="home-container">
      <div className="home-banner-container">
        <div className="home-bannerImage-container">
          <img src={BannerBackground} alt="home-banner-background.png" />
        </div>
        <div className="home-text-section">
          <h1 className="primary-heading">
            Your Favourite Food Delivered Hot & Fresh
          </h1>
          <p className="primary-text">
          Enjoy your favorite dishes delivered hot and fresh, straight to your doorstep.
          </p>
          <button className="secondary-button" onClick={()=>navigate("/restaurants")}>
            Order Now <FiArrowRight />{" "}
          </button>
        </div>
        <div className="home-image-section">
          <img src={BannerImage} alt="home-banner-image.png" class="moving-image"/>
        </div>
      </div>
    </div>
    <About />
    <Testimonial />
    <Contact />
    <Footer />
    </>
  );
};

export default Home;
