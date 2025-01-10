import React from 'react';
import './scss/Footer.scss';
import logo from '../assets/images/Asha_logo.png';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-column">
          <h4>Practo</h4>
          <ul>
            <li><a href="#">About</a></li>
            <li><a href="#">Blog</a></li>
            <li><a href="#">Careers</a></li>
            <li><a href="#">Press</a></li>
            <li><a href="#">Contact Us</a></li>
          </ul>
        </div>
        <div className="footer-column">
          <h4>For patients</h4>
          <ul>
            <li><a href="#">Search for doctors</a></li>
            <li><a href="#">Search for clinics</a></li>
            <li><a href="#">Search for hospitals</a></li>
            <li><a href="#">Practo Plus</a></li>
            <li><a href="#">Covid Hospital listing</a></li>
            <li><a href="#">Practo Care Clinics</a></li>
            <li><a href="#">Read health articles</a></li>
            <li><a href="#">Read about medicines</a></li>
            <li><a href="#">Practo drive</a></li>
            <li><a href="#">Health app</a></li>
          </ul>
        </div>
        <div className="footer-column">
          <h4>For doctors</h4>
          <ul>
            <li><a href="#">Practo Profile</a></li>
          </ul>
        </div>
        <div className="footer-column">
          <h4>For clinics</h4>
          <ul>
            <li><a href="#">Ray by Practo</a></li>
            <li><a href="#">Practo Reach</a></li>
            <li><a href="#">Ray Tab</a></li>
            <li><a href="#">Practo Pro</a></li>
          </ul>
        </div>
        <div className="footer-column">
          <h4>For hospitals</h4>
          <ul>
            <li><a href="#">Insta by Practo</a></li>
            <li><a href="#">Qikwell by Practo</a></li>
            <li><a href="#">Practo Profile</a></li>
            <li><a href="#">Practo Reach</a></li>
            <li><a href="#">Practo Drive</a></li>
          </ul>
        </div>
      </div>
      <div className="footer-bottom">
        <div className="footer-logo">
          <a href="/">
            <img src={logo} alt="Practo Logo" />
          </a>
        </div>
        <p>Copyright © 2025, Asha. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
