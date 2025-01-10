import React from "react";
import { Link } from "react-router-dom";
import logo from "../assets/images/Asha_logo.png";
import manageUsersIcon from "../assets/images/surgeries-icon.png";
import viewReportsIcon from "../assets/images/surgeries-icon.png";
import "./scss/AdminDashboard.scss";

function AdminDashboard() {
  return (
    <div className="app">
      <div className="hero-section">
        <div className="hero-container hero-content">
          {/* Left Section: Title and Subtitle */}
          <div className="hero-text-container">
            <h1 className="hero-title">Admin Dashboard</h1>
            <p className="hero-subtitle">Manage Users and View Reports</p>
          </div>

          {/* Right Section: Logo */}
          <div className="hero-logo-container">
            <img src={logo} alt="Asha Hospital Logo" className="hero-logo" />
          </div>
        </div>

        {/* Service Icons Section */}
        <div className="service-icons-container">
          <div className="service-card">
            <img
              src={manageUsersIcon}
              alt="Manage Users"
              className="service-icon"
            />
          </div>
          <div className="service-card">
            <img
              src={viewReportsIcon}
              alt="View Reports"
              className="service-icon"
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminDashboard;
