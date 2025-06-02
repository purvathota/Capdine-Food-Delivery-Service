import React from "react";
import AboutBackground from "../components/about-background.png";
import AboutBackgroundImage from "../components/about-background-image.png";
import { BsFillPlayCircleFill } from "react-icons/bs";
import { Link } from 'react-router-dom';
import LearnMore from "./LearnMore";
 
 
const About = () => {
  return (
    <div className="about-section-container">
      <div className="about-background-image-container">
        <img src={AboutBackground} alt="about-background.png" />
      </div>
      <div className="about-section-image-container">
        <img src={AboutBackgroundImage} alt="about-background-image.png" />
      </div>
      <div className="about-section-text-container">
        <p className="primary-subheading">About</p>
        <h1 className="primary-heading">
          Food Is An Important Part Of A Balanced Diet
        </h1>
        <p className="primary-text">
        "Food is an important part of a balanced diet" is a humorous saying often used to highlight the importance
        of not only consuming a variety of foods but also considering the overall nutritional balance in one's diet.
        </p>
       
        <div className="about-buttons-container">
        <Link to="/LearnMore" className="about-buttons-container">
          <button className="secondary-button">Learn More</button>
            </Link>
            <Link to="/Video" className="watch-video-button">
          <button className="watch-video-button">
          <BsFillPlayCircleFill /> Watch Video
          </button>
            </Link>
           
        </div>
      </div>
    </div>
  );
};
 
export default About;