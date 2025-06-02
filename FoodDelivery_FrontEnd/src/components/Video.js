import React from 'react';
import video from './video.mp4'; // Import the video file
import './video.css'; // Import the CSS file
 
const Video = () => {
  return (
    <div className="video-container">
      <video controls autoplay loop>
        <source src={video} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
    </div>
  );
};
 
export default Video;