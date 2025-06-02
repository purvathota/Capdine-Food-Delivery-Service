
import "./sidebar.css";
import DashboardIcon from "@mui/icons-material/Dashboard";
import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
import LocalShippingIcon from "@mui/icons-material/LocalShipping";
import CreditCardIcon from "@mui/icons-material/CreditCard";
import StoreIcon from "@mui/icons-material/Store";
import InsertChartIcon from "@mui/icons-material/InsertChart";
import SettingsApplicationsIcon from "@mui/icons-material/SettingsApplications";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";
import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
import SettingsSystemDaydreamOutlinedIcon from "@mui/icons-material/SettingsSystemDaydreamOutlined";
import PsychologyOutlinedIcon from "@mui/icons-material/PsychologyOutlined";
import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";
import { Link } from "react-router-dom";
import { useContext } from "react";
 
const Sidebar = () => {
 
  return (
    <div className="sidebar">
      <div className="top">
        <Link to="/" style={{ textDecoration: "none" }}>
          <span className="logo">CD Admin</span>
        </Link>
      </div>
      <hr />
      <div className="center">
        <ul>
          <p className="title">Dashboard</p>
          <Link to ="http://localhost:3000/" style={{ textDecoration:"none"}}>
          <li>
            <DashboardIcon className="icon" />
            <span>Dashboard</span>
 
         
          </li>
          </Link>
          <p className="title">LISTS</p>
          <Link to="/users" style={{ textDecoration: "none" }}>
            <li>
              <PersonOutlineIcon className="icon" />
              <span>Customers</span>
 
            </li>
          </Link>
          <p className="items"></p>
          <Link to ="/Restaurant" style={{ textDecoration:"none"}}>
         
            <li>
              <StoreIcon className="icon" />
              <span >Restaurants</span>
            </li>
            </Link>
            <p className="Orders"></p>
          <Link to ="/orders" style={{ textDecoration:"none"}}>
          <li>
            <CreditCardIcon className="icon" />
            <span>Orders</span>
          </li>
          </Link>
          <p className="items"></p>
          <Link to ="/DeliveryDrivers" style={{ textDecoration:"none"}}>
          <li>
            <LocalShippingIcon className="icon" />
            <span>Delivery Drivers</span>
 
          </li>
          </Link>
          <p className="title">USEFUL</p>
          <li>
            <InsertChartIcon className="icon" />
            <span onClick={() => alert('Button clicked!')}>Stats</span>
 
          </li>
          <li>
            <NotificationsNoneIcon className="icon" />
            <span>Notifications</span>
 
          </li>
          <p className="title">SERVICE</p>
          <li>
            <SettingsSystemDaydreamOutlinedIcon className="icon" />
            <span>System Health</span>
 
          </li>
          <li>
            <PsychologyOutlinedIcon className="icon" />
            <span onClick={() => alert('Button clicked!')}>Profile</span>
 
          </li>
          <li>
            <SettingsApplicationsIcon className="icon" />
            <span>Settings</span>
 
          </li>
          <p className="title">USER</p>
          <Link to="/Logout" style={{ textDecoration: "none" }}>
          <li>
            <ExitToAppIcon className="icon" />
            <span >Logout</span>
 
          </li>
          </Link>
        </ul>
      </div>
      <div className="bottom">
      </div>
    </div>
  );
};
 
export default Sidebar;


// import "./sidebar.css";
// import DashboardIcon from "@mui/icons-material/Dashboard";
// import PersonOutlineIcon from "@mui/icons-material/PersonOutline";
// import LocalShippingIcon from "@mui/icons-material/LocalShipping";
// import CreditCardIcon from "@mui/icons-material/CreditCard";
// import StoreIcon from "@mui/icons-material/Store";
// import InsertChartIcon from "@mui/icons-material/InsertChart";
// import SettingsApplicationsIcon from "@mui/icons-material/SettingsApplications";
// import ExitToAppIcon from "@mui/icons-material/ExitToApp";
// import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
// import SettingsSystemDaydreamOutlinedIcon from "@mui/icons-material/SettingsSystemDaydreamOutlined";
// import PsychologyOutlinedIcon from "@mui/icons-material/PsychologyOutlined";
// import AccountCircleOutlinedIcon from "@mui/icons-material/AccountCircleOutlined";
// import { Link } from "react-router-dom";
// import { useContext } from "react";

// const Sidebar = () => {
 
//   return (
//     <div className="sidebar">
//       <div className="top">
//         <Link to="/" style={{ textDecoration: "none" }}>
//           <span className="logo">CD Admin</span>
//         </Link>
//       </div>
//       <hr />
//       <div className="center">
//         <ul>
//           <p className="title">Dashboard</p>
//           <Link to ="http://localhost:3000" style={{ textDecoration:"none"}}>
//           <li>
//             <DashboardIcon className="icon" />
//             <span>Dashboard</span>

          
//           </li>
//           </Link>
//           <p className="title">LISTS</p>
//           <Link to="/users" style={{ textDecoration: "none" }}>
//             <li>
//               <PersonOutlineIcon className="icon" />
//               <span>Customers</span>
//             </li>

//           </Link>
//           <p className="Restaurant"></p>
//           <Link to ="/Restaurant" style={{ textDecoration:"none"}}>
//             <li>
//               <StoreIcon className="icon" />
//               <span >Restaurants</span>
//             </li>
//             </Link>
//             <p className="Orders"></p>
//           <Link to ="/orders" style={{ textDecoration:"none"}}>
//           <li>
//             <CreditCardIcon className="icon" />
//             <span>Orders</span>
//           </li>
//           </Link>
//           <p className="items"></p>
//           <Link to ="/DeliveryDrivers" style={{ textDecoration:"none"}}>
//           <li>
//             <LocalShippingIcon className="icon" />
//             <span>Delivery Drivers</span>

//           </li>
//           </Link>
//           <p className="title">USEFUL</p>
//           <li>
//             <InsertChartIcon className="icon" />
//             <span onClick={() => alert('Button clicked!')}>Stats</span>

//           </li>
//           <li>
//             <NotificationsNoneIcon className="icon" />
//             <span>Notifications</span>

//           </li>
//           <p className="title">SERVICE</p>
//           <li>
//             <SettingsSystemDaydreamOutlinedIcon className="icon" />
//             <span>System Health</span>

//           </li>
//           <li>
//             <PsychologyOutlinedIcon className="icon" />
//             <span onClick={() => alert('Button clicked!')}>Profile</span>

//           </li>
//           <li>
//             <SettingsApplicationsIcon className="icon" />
//             <span>Settings</span>

//           </li>
//           <p className="title">USER</p>
//           <Link to="/Logout" style={{ textDecoration: "none" }}>
//           <li>
//             <ExitToAppIcon className="icon" />
//             <span >Logout</span>

//           </li>
//           </Link>
//         </ul>
//       </div>
//       <div className="bottom">
//       </div>
//     </div>
//   );
// };

// export default Sidebar;