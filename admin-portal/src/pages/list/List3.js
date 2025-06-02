import "./list.css"
import Sidebar from "../../components/sidebar/Sidebar"
import Navbar from "../../components/navbar/Navbar"
import DeliveryDrivers from "../../components/delivery_drivers/DeliveryDrivers"


const List3 = () => {
  return (
    <div className="list">
      <Sidebar/>
      <div className="listContainer">
        <Navbar/>
        <DeliveryDrivers/>
      </div>
    </div>
  )
}

export default List3