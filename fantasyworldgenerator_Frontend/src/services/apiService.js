import api from './api';  // Use the Axios instance with JWT handling

// Register User
export const registerUser = async (username, password) => {
    try {
        const response = await api.post('/api/auth/register', { username, password });  // Append the endpoint here
        if (response.status === 200) {
            return response.data.accessToken;
        } else {
            throw new Error('Failed to register');
        }
    } catch (error) {
        console.error('Error registering user:', error);
        throw error;
    }
};

// Login User
export const loginUser = async (username, password) => {
    try {
        const response = await api.post('/api/auth/login', { username, password }); 
        const token  = response.data.accessToken; 
        if (response.status === 200) {            
            localStorage.setItem('token', token);  // Store JWT token
            //console.log("current token " + token);
            return response.data.accessToken;
        } else {
            throw new Error('Login failed');
        }
    } catch (error) {
        if (error.response) {
            console.error('Error logging in user:', error.response.data.accessToken);
        } else {
            console.error('Error logging in user:', error.message);
        }
        throw error;
    }
};
