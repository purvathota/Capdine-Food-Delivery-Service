import "./list.css"
import Sidebar from "../../components/sidebar/Sidebar"
import Navbar from "../../components/navbar/Navbar"
import Restaurant1 from "../../components/restaurants/Restaurant1"



const List1 = () => {
  return (
    <div className="list1">
      <Sidebar/>
      <div className="listContainer">
        <Navbar/>
        <Restaurant1/>
      </div>
    </div>
  )
}

export default List1;