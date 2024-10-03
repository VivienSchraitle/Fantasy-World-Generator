import React, { useState } from 'react';
import axios from 'axios';  // Use Axios for API calls
import { Navigate } from 'react-router-dom'; // Import useNavigate
import styles from './RegisterForm.module.css'; // Import CSS module

const RegisterForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [isLogin, setIsLogin] = useState(false); // State to toggle between login and register

  
  const handleSubmit = async () => {
    try {
      let response;
      if (isLogin) {
        // Call the login API
        response = await axios.post('http://localhost:8080/api/auth/login', { username, password });
        const { token } = response.data;
        // Store the JWT token in localStorage
        localStorage.setItem('token', token);
        setMessage('Login successful!');

        if (response && response.data.success) { // Check for successful response
          // Redirect to menu on successful login
          if (isLogin) {
            return <Navigate to="/menu" replace />;
          }
        }
        
      } else {
        // Call the registration API
        response = await axios.post('http://localhost:8080/api/auth/register', { username, password });
        setMessage('Registration successful! Please log in.');
        // Optionally, switch to login mode after successful registration
        setIsLogin(true);
      }
    } catch (error) {
      var errorMessage = error.response && error.response.data ? error.response.data : 'An error occurred. Please try again.';
      if (errorMessage.includes('Username is already taken')) {
        setMessage('This username is already taken. Please choose another one.');
      } else if (errorMessage.includes('Invalid username or password')) {
        setMessage('Incorrect username or password. Please try again.');
      } else if (errorMessage.includes("User account is disabled")) {
        setMessage("Your account is disabled. Please contact support.");
      } else if (errorMessage.includes("User account is locked")) {
        setMessage("Your account is locked. Please contact support.");
      } else {
        setMessage("An unexpected error occurred. Please try again later.");
      }
    }
  };

  return (
    <div className={styles.formContainer}>
      <h1>{isLogin ? 'Login' : 'Register'}</h1>
      <div>
        <label>Username:</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Enter your username"
        />
      </div>
      <div>
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Enter your password"
        />
      </div>
      <button onClick={handleSubmit}>
        {isLogin ? 'Login' : 'Register'}
      </button>
      <div className={styles.message}>{message}</div>

      <button onClick={() => setIsLogin(!isLogin)} className={styles.toggleButton}>
        {isLogin ? 'Switch to Register' : 'Switch to Login'}
      </button>
    </div>
  );
};

export default RegisterForm;
