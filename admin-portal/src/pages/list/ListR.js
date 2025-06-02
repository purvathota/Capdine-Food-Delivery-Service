import "./list.css"
import Sidebar from "../../components/sidebar/Sidebar"
import Navbar from "../../components/navbar/Navbar"
import Restaurant1 from "../../components/restaurants/Restaurant1"


const ListR = () => {
  return (
    <div className="list">
      <Sidebar/>
      <div className="listContainer">
        <Navbar/>
       <Restaurant1/>
      </div>
    </div>
  )
}

export default ListR;