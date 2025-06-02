import "./list.css"
import Sidebar from "../../components/sidebar/Sidebar"
import Navbar from "../../components/navbar/Navbar"
import Orders from "../../components/orders/Orders"


const List2 = () => {
  return (
    <div className="list">
      <Sidebar/>
      <div className="listContainer">
        <Navbar/>
        <Orders/>
      </div>
    </div>
  )
}

export default List2