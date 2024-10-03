import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import RegisterForm from './components/RegisterForm';
import FactionGenerator from './components/FactionGenerator';
import Menu from './components/Menu';
import { ThemeProvider } from 'styled-components';
import { theme } from './styles/theme'; // Import the global theme

const App = () => {
  return (
    <ThemeProvider theme={theme}>
    <Router>
      <Routes>
        <Route path="/" element={<RegisterForm />} /> {/* Default to register page */}
        <Route path="/register" element={<RegisterForm />} />
        <Route path="/faction-generator" element={<FactionGenerator />} />
        <Route path="/menu" element={<Menu />} />
      </Routes>
    </Router>
    </ThemeProvider>
  );
};

export default App;
