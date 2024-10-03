import axios from 'axios';

export const registerUser = async (username, password) => {
    try {
        const response = await axios.post('http://localhost:8080/api/auth/register', { username, password });
        if (response.status === 200) {
            return response.data;
        } else {
            throw new Error('Failed to register');
        }
    } catch (error) {
        console.error('Error registering user:', error);
        throw error;
    }
};

// User Login (For future use, e.g., JWT authentication)
export const loginUser = async (username, password) => {
    try {
        const response = await axios.post('http://localhost:8080/api/auth/login', { username, password });
        if (response.status === 200) {
            return response.data;
        } else {
            throw new Error('Login failed');
        }
    } catch (error) {
        if (error.response) {
            console.error('Error logging in user:', error.response.data);
        } else {
            console.error('Error logging in user:', error.message);
        }
        throw error;
    }
};

