// App.js or index.js

import React from 'react';
import ReactDOM from 'react-dom';
import App from './App'; // Import your main App component
import { AuthProvider } from './AuthContext'; // Import the UserProvider from the UserContext file
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

ReactDOM.render(
  <React.StrictMode>
    <AuthProvider> {/* Wrap your App component with UserProvider */}
      <App />
    </AuthProvider>
  </React.StrictMode>,
  document.getElementById('root')
);
