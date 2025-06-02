import React from "react";
import ProfilePic from "../components/kunal-kapoor.png";
import { AiFillStar } from "react-icons/ai";
 
const Testimonial = () => {
  return (
    <div className="work-section-wrapper">
      <div className="work-section-top">
        <p className="primary-subheading">Testimonial</p>
        <h1 className="primary-heading">What They Are Saying</h1>
        <p className="primary-text">
          {}
        </p>
      </div>
      <div className="testimonial-section-bottom">
        <img src={ProfilePic} alt="kunal-kapoor.png" />
        <p>
          Kunal Kapoor
        </p>
        <div className="testimonials-stars-container">
          <AiFillStar />
          <AiFillStar />
          <AiFillStar />
          <AiFillStar />
          <AiFillStar />
        </div>
        <h2>Taste the World, Delivered to Your Doorstep</h2>
      </div>
    </div>
  );
};
 
export default Testimonial;