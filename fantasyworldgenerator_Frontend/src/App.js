import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import RegisterForm from './components/RegisterForm';
import FactionGenerator from './components/FactionGenerator';
import Menu from './components/Menu';
import './styles/index.css'; // Importing your global CSS

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<RegisterForm />} /> {/* Default to register page */}
        <Route path="/register" element={<RegisterForm />} />
        <Route path="/faction-generator" element={<FactionGenerator />} />
        <Route path="/menu" element={<Menu />} />
      </Routes>
    </Router>
  );
};

export default App;
