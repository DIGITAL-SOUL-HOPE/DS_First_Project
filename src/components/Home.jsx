import React from "react";
import { Link } from "react-router-dom";
import logo from "../assets/images/Asha_logo.png";
import doctorImage from "../assets/images/doctorImage.png"; // Ensure this image exists
import videoConsultationIcon from "../assets/images/video-consultation-icon.png"; // Update the path to your icon
import findDoctorsIcon from "../assets/images/find-doctors-icon.png";
import manageProfileIcon from "../assets/images/surgeries-icon.png";
import viewAppointmentsIcon from "../assets/images/surgeries-icon.png";
import accountSettingsIcon from "../assets/images/surgeries-icon.png";
import "./scss/Home.scss";

function UserHome() {
  return (
    <div className="app">
      <div className="hero-section">
        <div className="hero-content">
          {/* User Dashboard Heading */}
          <div className="hero-text-container">
            <h1 className="hero-title">User Dashboard</h1>
            <p className="hero-subtitle">Manage your profile and appointments</p>
          </div>
          {/* Specialist Info */}
          <div className="specialist-info-container">
            <div className="doctor-info-wrapper">
              <div className="doctor-image-container">
                <img src={doctorImage} alt="Dr. Bokada" className="doctor-image" />
              </div>
              <div className="doctor-info">
                <h3 className="doctor-name">Dr. Bokada</h3>
                <p className="doctor-specialty">Bokada Specialist</p>
                <p className="doctor-hospital">Bokada Hospital, Hyderabad</p>
                <p className="doctor-rating">★★★★★</p>
                <button className="btn-primary">
                  <span className="default-btn"><span>BOOK NOW</span></span>
                  <span className="hover-btn"><span>BOOK NOW</span></span>
                </button>
              </div>
            </div>
          </div>
        </div>

        {/* Service Icons Section */}
        <div className="service-icons-container">
          <div className="service-card">
            <img
              src={manageProfileIcon}
              alt="Manage Profile"
              className="service-icon"
            />
            <p className="service-text">Manage Profile</p>
          </div>
          <div className="service-card">
            <img
              src={viewAppointmentsIcon}
              alt="View Appointments"
              className="service-icon"
            />
            <p className="service-text">View Appointments</p>
          </div>
          <div className="service-card">
            <img
              src={accountSettingsIcon}
              alt="Account Settings"
              className="service-icon"
            />
            <p className="service-text">Account Settings</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserHome;
