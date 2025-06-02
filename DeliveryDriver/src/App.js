import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import DriverLayout from './DriverLayout';
import MenuTable from './component/MenuTable';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/driver" element={<DriverLayout />} />
          <Route path="/menu" element={<MenuTable />} /> {/* Route for MenuTable */}
          {/* Add more routes here */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
