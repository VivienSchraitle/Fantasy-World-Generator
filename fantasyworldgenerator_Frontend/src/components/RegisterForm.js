import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { registerUser, loginUser } from '../services/apiService'; // Import the API services
import '../styles/index.css'; // Import CSS module
import { getMessageClass } from '../util/messageUtils';

const RegisterForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState('');
  const [isLogin, setIsLogin] = useState(false); // State to toggle between login and register
  const navigate = useNavigate();

  const handleSubmit = async () => {
    try {
      if (isLogin) {
        // Call the login API service
        await loginUser(username, password); // We don't need to assign this to 'response' anymore
        setMessage('Login successful!');
        setMessageType('success');
        navigate('/menu'); // Redirect to menu after successful login
      } else {
        // Call the register API service
        await registerUser(username, password); // Same here, we can just await the function
        setMessage('Registration successful! Please log in.');
        setMessageType('success');
        setIsLogin(true); // Switch to login mode after successful registration
      }
    } catch (error) {
      var errorMessage = error.response && error.response.data ? error.response.data : 'An error occurred. Please try again.';
      if (errorMessage.includes('Username is already taken')) {
        setMessage('This username is already taken. Please choose another one.');
        setMessageType('error');
      } else if (errorMessage.includes('Invalid username or password')) {
        setMessage('Incorrect username or password. Please try again.');
        setMessageType('error');
      } else if (errorMessage.includes("User account is disabled")) {
        setMessage("Your account is disabled. Please contact support.");
        setMessageType('error');
      } else if (errorMessage.includes("User account is locked")) {
        setMessage("Your account is locked. Please contact support.");
        setMessageType('error');
      } else {
        setMessage("An unexpected error occurred. Please try again later.");
        setMessageType('error');
      }
    }
  };

  return (
    <div className="formContainer">
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
      <button className="button" onClick={handleSubmit}>
        {isLogin ? 'Login' : 'Register'}
      </button>
      <div className={getMessageClass(messageType)}>{message}</div>

      <button className="toggleButton" onClick={() => setIsLogin(!isLogin)}>
        {isLogin ? 'Switch to Register' : 'Switch to Login'}
      </button>
    </div>
  );
};

export default RegisterForm;
