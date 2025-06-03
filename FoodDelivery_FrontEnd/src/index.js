import React from 'react';
import { createRoot } from 'react-dom/client';
import App from './App';
import { AuthProvider } from './AuthContext';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min';

const container = document.getElementById('root');
const root = createRoot(container);

root.render(
  <React.StrictMode>
    <AuthProvider>
      <App />
    </AuthProvider>
  </React.StrictMode>
);
