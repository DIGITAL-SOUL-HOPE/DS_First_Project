import React, { createContext, useState, useContext, useEffect } from 'react';
import AuthService from '../Service/AuthService';  // Assuming AuthService handles your login API logic

// Create a Context for authentication
const AuthContext = createContext();

// Custom hook to access the authentication context
export const useAuth = () => {
  return useContext(AuthContext);
};

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Check if user is logged in on mount
  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsLoggedIn(!!token);
  }, []);

  // Function to set login state
  const login = () => setIsLoggedIn(true);

  // Function to set logout state
  const logout = () => {
    AuthService.logout(); // Assuming this clears the token from localStorage
    setIsLoggedIn(false);
    localStorage.removeItem("token"); // Ensure token is removed on logout
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
