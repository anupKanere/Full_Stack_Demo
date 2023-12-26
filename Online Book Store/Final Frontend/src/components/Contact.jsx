import { FaGoogle, FaMobile, FaLinkedinIn } from "react-icons/fa";


import Navbar from "./Navbar"
function Contact() {
    return (
        <div className="contact-us-us">
            <Navbar></Navbar>
            <div className="container-fluid ">
                <div className="row justify-content-evenly align-items-center m-5">
                    <h1 className="log-in-in mb-5">Contact Us</h1>

                    <div className="card" style={{ width: "18rem" }}>
                        <img src="../../contacts/Adarsh.jpg" className="card-img-top" alt="Contact 1"></img>
                        <div className="card-body">
                            <h5 className="card-title">Mr. Adarsh Kadam</h5>
                            <div className="wrapper mb-5">

                                <a href="mailto:adarshkadam07@gmail.com" className="icon gmail">
                                    <div className="tooltip">Gmail</div>
                                    <FaGoogle className="ms-3" />
                                </a>

                                <a href="#" className="icon phone">
                                    <div className="tooltip">Phone</div>
                                    <FaMobile className="ms-3" />
                                </a>
                                <a href="www.linkedin.com/in/kadam-adarsh" className="icon linkedin">
                                    <div className="tooltip">LinkedIn</div>
                                    <FaLinkedinIn className="ms-3" />
                                </a>
                                <p style={{ marginLeft: "4rem" }}>8983173235</p>

                            </div>
                        </div>
                    </div>

                    <div className="card" style={{ width: "18rem" }}>
                        <img src="../../contacts/Anup.jpg" className="card-img-top" alt="Contact 2"></img>
                        <div className="card-body">
                            <h5 className="card-title">Mr. Anup Kanere</h5>
                            <div className="wrapper mb-5">

                                <a href="mailto:kanereanup@gmail.com" className="icon gmail">
                                    <div className="tooltip">Gmail</div>
                                    <FaGoogle className="ms-3" />
                                </a>

                                <a href="#" className="icon phone">
                                    <div className="tooltip">Phone</div>
                                    <FaMobile className="ms-3" />
                                </a>
                                <a href="https://www.linkedin.com/in/anup-kanere-02a021205/" className="icon linkedin">
                                    <div className="tooltip">LinkedIn</div>
                                    <FaLinkedinIn className="ms-3" />
                                </a>
                                <p style={{ marginLeft: "4rem" }}>7720888632</p>
                            </div>

                        </div>
                    </div>

                    <div className="card" style={{ width: "18rem" }}>
                        <img src="../../contacts/Shruti.jpg" className="card-img-top" alt="Contact 3"></img>
                        <div className="card-body">
                            <h5 className="card-title">Ms. Shruti Chintawar</h5>
                            <div className="wrapper mb-5">

                                <a href="mailto:schintawar99@gmail.com" className="icon gmail">
                                    <div className="tooltip">Gmail</div>
                                    <FaGoogle className="ms-3" />
                                </a>

                                <a href="#" className="icon phone">
                                    <div className="tooltip">Phone</div>
                                    <FaMobile className="ms-3" />
                                </a>
                                <a href="https://www.linkedin.com/in/shruti-chintawar-57599b184/" className="icon linkedin">
                                    <div className="tooltip">LinkedIn</div>
                                    <FaLinkedinIn className="ms-3" />
                                </a>
                                <p style={{ marginLeft: "4rem" }}>9359666234</p>
                            </div>
                        </div>
                    </div>

                    <div className="card" style={{ width: "18rem" }}>
                        <img src="../../contacts/Pranal.jpg" className="card-img-top" alt="Contact 4"></img>
                        <div className="card-body">
                            <h5 className="card-title">Mr. Pranal Ghule</h5>
                            <div className="wrapper mb-5">

                                <a href="mailto:pranalghulep99@gmail.com" className="icon gmail">
                                    <div className="tooltip">Gmail</div>
                                    <FaGoogle className="ms-3" />
                                </a>

                                <a href="#" className="icon phone">
                                    <div className="tooltip">Phone</div>
                                    <FaMobile className="ms-3" />
                                </a>
                                <a href="https://www.linkedin.com/in/pranalghulep99" className="icon linkedin">
                                    <div className="tooltip">LinkedIn</div>
                                    <FaLinkedinIn className="ms-3" />
                                </a>
                                <p style={{ marginLeft: "4rem" }}>8805121573</p>
                            </div>
                        </div>
                    </div>

                    <div className="card" style={{ width: "18rem" }}>
                        <img src="../../contacts/Shreyas.png" className="card-img-top" alt="Contact 4"></img>
                        <div className="card-body">
                            <h5 className="card-title">Mr. Shreyas Dhule</h5>
                            <div className="wrapper mb-5">

                                <a href="mailto:shreyash10dhule@gmail.com" className="icon gmail">
                                    <div className="tooltip">Gmail</div>
                                    <FaGoogle className="ms-3" />
                                </a>

                                <a href="#" className="icon phone">
                                    <div className="tooltip">Phone</div>
                                    <FaMobile className="ms-3" />
                                </a>
                                <a href=" https://www.linkedin.com/in/shreyas-dhule/" className="icon linkedin">
                                    <div className="tooltip">LinkedIn</div>
                                    <FaLinkedinIn className="ms-3" />
                                </a>
                                <p style={{ marginLeft: "4rem" }}>9673253464</p>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
    )
}

export default Contact;