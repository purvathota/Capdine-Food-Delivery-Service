import React, { useState } from "react";
import Logo from "../components/capdine-logo.png";
import { BsCart2 } from "react-icons/bs";
import { HiOutlineBars3 } from "react-icons/hi2";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";
import CommentRoundedIcon from "@mui/icons-material/CommentRounded";
import PhoneRoundedIcon from "@mui/icons-material/PhoneRounded";
import ShoppingCartRoundedIcon from "@mui/icons-material/ShoppingCartRounded";
import AddRestaurant from "./AddRestaurant";
import LogIn from "./LogIn"; // Import the second modal component
import SignIn from "./SignIn";
import { useAuth } from "../AuthContext";
import { useNavigate } from "react-router";
import { CiLogout } from "react-icons/ci";
 
 
 
const Navbar = ({ setLogInVisible, setSignInVisible, setAddRestaurantVisible }) => {
  const { user, logout } = useAuth();
  const [openMenu, setOpenMenu] = useState(false);
  const navigate = useNavigate();
  const cartItemCount = user ? user.cartItems.length : 0;
  // const [addRestaurantVisible, setAddRestaurantVisible] = useState(false); // State for the first modal
  // const [logInVisible, setLogInVisible] = useState(false); // State for the second modal
  // const [signInVisible, setSignInVisible] = useState(false); //State for the third modal
 
  const handleAddRestaurantClick = (e) => {
    e.preventDefault();
    setAddRestaurantVisible(true);
  };
 
  const handleLogInClick = (e) => {
    e.preventDefault();
    setLogInVisible(true);
  };
 
  const handleSignInClick = (e) => {
    e.preventDefault();
    setSignInVisible(true);
  }
 
  const handleCartClick = (e) => {
    e.preventDefault();
    navigate("/cart");
  }
 
  const handleLogout = () => {
    logout();
    navigate("/");
  }
 
  const menuOptions = [
    {
      text: "Add Restaurant",
      icon: <HomeIcon />,
      onClick: handleAddRestaurantClick,
    },
    {
      text: "Log In",
      icon: <InfoIcon />,
      onClick: handleLogInClick,
    },
    {
      text: "Sign Up",
      icon: <CommentRoundedIcon />,
      onClick: handleSignInClick,
    },
    {
      text: "Cart",
      icon: <ShoppingCartRoundedIcon />,
      onClick: handleCartClick,
    },
    {
      text: "Logout",
      icon: <CiLogout />,
      onClick: () => handleLogout(),
    }
  ];
 
  return (
    <nav>
      <div className="nav-logo-container">
        <img src={Logo} alt="" onClick={()=>navigate("/")} style={{ cursor: "pointer", width: '120px', height: 'auto' }} />
      </div>
      <div className="navbar-links-container">
        {menuOptions.map((item, index) => {
          if (user && (item.text === "Cart" || item.text === "Logout")) {
            return (
              <a key={index} onClick={item.onClick} href="">
 
                {item.text === 'Cart' ? (
                  <span className="cart-icon-container">
                    <BsCart2 />
                    {cartItemCount > 0 && <span className="cart-badge">{cartItemCount}</span>}
                  </span>
                ) : (
                  <></>
                )}
 
                {item.text === 'Logout' ? (
                  <a key={index} onClick={item.onClick} href="">
                    {item.text}
                    {item.icon}
                  </a>
                ) : (
                  <></>
                )}
              </a>
            );
          } else if (!user && (item.text === "Log In" || item.text === "Sign Up" || item.text === "Add Restaurant")) {
            return (
              <a key={index} onClick={item.onClick} href="">
                {item.text}
              </a>
            );
          }
          return null;
        })}
      </div>
 
      {/* {menuOptions.map((item, index) => (
          <a key={index} onClick={item.onClick} href="#">
            {item.text === 'Cart' ? (
              <span>
                <BsCart2 style={{fontSize: '30px'}}/>
              </span>
            ) : (
              <></>
            )}
          </a>
        ))}
      </div> */}
 
      <div className="navbar-menu-container">
        <HiOutlineBars3 onClick={() => setOpenMenu(true)} />
      </div>
      <Drawer open={openMenu} onClose={() => setOpenMenu(false)} anchor="right">
        <Box
          sx={{ width: 250 }}
          role="presentation"
          onClick={() => setOpenMenu(false)}
          onKeyDown={() => setOpenMenu(false)}
        >
          <List>
            {menuOptions.map((item, index) => (
              <ListItem key={index} disablePadding>
                <ListItemButton onClick={item.onClick}>
                  <ListItemIcon>{item.icon}</ListItemIcon>
                  <ListItemText primary={item.text} />
                </ListItemButton>
              </ListItem>
            ))}
          </List>
          <Divider />
        </Box>
      </Drawer>
    </nav>
  );
};
 
export default Navbar;