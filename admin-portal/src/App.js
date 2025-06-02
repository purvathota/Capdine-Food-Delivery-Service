import Home from "./pages/home/Home";
import List from "./pages/list/List";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./style/dark.css";
import ListR from "./pages/list/ListR";
import List2 from "./pages/list/List2";
import List3 from "./pages/list/List3";
import Logout from "./components/logout/Logout";


function App() {

  return (
      <BrowserRouter>
        <Routes>
          <Route path="/">
            <Route index element={<Home />} />
            <Route path="/users">
              <Route index element={<List />} />            
            </Route>
            <Route path="/Restaurant">
              <Route index element={<ListR/>} />
            </Route>
              <Route path="/Orders" >
              <Route index element={<List2 />} />
            </Route>
              <Route path="/deliverydrivers" >
              <Route index element={<List3 />} />
           </Route>
           <Route path="/Logout" >
              <Route index element={<Logout/>} />
           </Route>
           </Route>
          
        </Routes>
      </BrowserRouter>
  );
}

export default App;