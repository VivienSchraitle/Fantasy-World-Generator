import axios from 'axios';

// Create an Axios instance
const api = axios.create({
  baseURL: 'http://localhost:8080', // Your base URL
});

// Add a request interceptor to include the token in all requests
api.interceptors.request.use((config) => {
    //console.log("intercepted");
    const token = localStorage.getItem('token');
    //console.log('Token in interceptor:', token); // Debugging line
    if (token && !config.url.includes('/api/auth/register') && !config.url.includes('/api/auth/login')) {
        config.headers['Authorization'] = `Bearer ${token}`;
        //console.log('Authorization header set:', config.headers['Authorization']); // Debugging line
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});
  export default api;
  
