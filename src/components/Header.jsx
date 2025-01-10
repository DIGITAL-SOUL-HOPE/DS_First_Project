import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import './scss/Header.scss';
import logo from '../assets/images/Asha_logo.png'; // Update the path to your logo

const Header = () => {
  const [menuActive, setMenuActive] = useState(false);
  const { isLoggedIn, logout } = useAuth();
  const navigate = useNavigate();

  const toggleMenu = () => {
    setMenuActive(!menuActive);
    document.body.style.overflow = menuActive ? 'auto' : 'hidden'; // Prevent scrolling when menu is open
  };

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <header className="header">
      {/* Hamburger menu toggle */}
      <div
        className="header__menu-toggle"
        onClick={toggleMenu}
        aria-label="Toggle menu"
      >
        ☰
      </div>

      {/* Logo */}
      <div className="header__logo">
        <Link to="/">
          <img src={logo} alt="Asha Logo" className="header__logo-img" />
        </Link>
      </div>

      {/* Navigation links */}
      <nav className={`header__nav ${menuActive ? 'active' : ''}`}>
        <div className="header__links">
          <Link to="/" className="header__link">
            Home
          </Link>
        </div>
        <a href="#find-doctors" className="header__link">
          Find Doctors
        </a>
        <a href="#video-consult" className="header__link">
          Video Consult
        </a>
        <a href="#surgeries" className="header__link">
          Surgeries
        </a>
        <a href="#contact-us" className="header__link">
          Contact Us
        </a>
        <a href="#help-center" className="header__link">
          Help Center
        </a>
      </nav>
      <div className="header__buttons">
        {isLoggedIn ? (
          <button className="header__buttons-btn" onClick={handleLogout}>
            Logout
          </button>
        ) : (
          <>
            <Link to="/register">
              <button className="header__buttons-btn">Signup</button>
            </Link>
            <Link to="/login">
              <button className="header__buttons-btn">Login</button>
            </Link>
          </>
        )}
      </div>
    </header>
  );
};

export default Header;
