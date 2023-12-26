import React from "react";
import { FaBook, FaSignInAlt, FaUserPlus, FaUser } from "react-icons/fa";

function Navbar() {

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark p-3" style={{ fontWeight: "bold" }}>
                <div className="container-fluid" style={{ marginLeft: "3rem" }}>
                    <a className="navbar-brand" href="/"><FaBook height={65} width={90}></FaBook>OnlineBookStore</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent" style={{ marginLeft: "15rem" }}>
                        <ul className="navbar-nav me-auto mb-2 mb-lg-1">

                            <li className="nav-item">
                                <a className="nav-link" aria-current="page" href="/about" style={{ fontSize: "1.3rem", marginRight: "1.6rem" }}>About Us</a>
                            </li>

                            <li className="nav-item">
                                <a className="nav-link" href="/contact" style={{ fontSize: "1.3rem" }}>Contact Us</a>
                            </li>

                            <div className='buttons' style={{ marginLeft: "10rem" }}>
                                <a href='/users/signin' className='btn btn-light'>
                                    Login <FaSignInAlt></FaSignInAlt>
                                </a>

                                <a href='/users/register-customer' className='btn btn-light ms-3'>
                                    Sign Up <FaUserPlus></FaUserPlus>
                                </a>

                                <a href='/users/register-admin' className='btn btn-light ms-3'>
                                    Register Admin <FaUserPlus></FaUserPlus>
                                </a>
                                <a href='/users/admin-login' className='btn btn-light ms-3'>
                                    Login As Admin <FaUser></FaUser>
                                </a>

                            </div>
                        </ul>
                    </div>
                </div>
            </nav>

        </div>
    )
}

export default Navbar;