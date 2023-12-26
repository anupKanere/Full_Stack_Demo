import Navbar from "./Navbar";
import "../App.css"
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import LoginServiceCustomer from "../Service/LoginServiceCustomer";


function LoginForm() {

    const [showPassword, setShowPassword] = useState(false);
    const [userId, setuserId] = useState(0);
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [status, setStatus] = useState('');

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        if (email === '' || password === '') {
            setStatus('Enter email id and password')
        } else {
            LoginServiceCustomer.validateuser(email, password).then((result) => {
                console.log(email);
                var msg = JSON.stringify(result.data.message);
                console.log(msg);
                console.log(result);
                navigate("/books/getAllBooks");
                setuserId(userId);
                sessionStorage.setItem('userId', result.data.userDTO.userId);
                sessionStorage.setItem('email', email);
                sessionStorage.setItem("user", (result.data.userDTO));

            }).catch((err) => {
                // Handle the error and access the data in the err object
                if (err.response) {
                    // The request was made and the server responded with a status code
                    console.log("Response data:", err.response.data);
                    console.log("Response status:", err.response.status);

                    // Access the data from the response
                    const errorMessage = err.response.data.message;
                    const errorTimestamp = err.response.data.timestamp;

                    // Handle the error message and timestamp as needed
                    setStatus(errorMessage);
                    console.log("Error timestamp:", errorTimestamp);

                } else {
                    // The request was made but no response was received, or there was a network error
                    console.error("Network error:", err.message);
                }
            });
        }
    }

    return (
        <div>
            <Navbar></Navbar>

            <div className="container-fluid login-form " >
                <form onSubmit={handleSubmit}>
                    <div className="row align-items-center justify-content-evenly " style={{ height: "93vh" }}>
                        <div className="col-4 align-self-center p-5 rounded">
                            <h1 className="log-in-in mb-5">Sign In</h1>


                            <div className="row m-4 align-items-center justify-content-evenly">
                                <label className="form-label">User Name</label>
                                <div className="input-group">
                                    <input className="form-control form-control " type="email" placeholder="Email" value={email} onChange={(event) => setEmail(event.target.value)} required></input>
                                </div>
                            </div>
                            <div className="row m-4 align-items-center justify-content-evenly">
                                <label className="form-label">Password</label>
                                <div className="input-group">
                                    <input
                                        className="form-control"
                                        type={showPassword ? "text" : "password"}
                                        placeholder="****"
                                        value={password}
                                        onChange={(event) => setPassword(event.target.value)}
                                        required
                                    />
                                    <div className="input-group-append">
                                        <span
                                            className="input-group-text"
                                            onClick={togglePasswordVisibility}
                                            style={{ cursor: "pointer" }}
                                        >
                                            {showPassword ? "Hide" : "Show"}
                                        </span>
                                    </div>
                                </div>
                            </div>


                            <div className="row m-4 align-items-center justify-content-evenly">
                                <div className="row m-4 align-items-center justify-content-evenly">
                                    <button className="btn btn-success rounded" type="submit">Login</button>
                                    {status ? <div className='text-success'>{status}</div> : null}
                                </div>

                            </div>

                            <div className="row m-4 align-items-center justify-content-evenly">
                                <div className="col-10">
                                    <span className="lastspan" >Not Registered? <a href="/users/register-customer" className="link-primary">Create an Account</a></span>
                                </div>
                            </div>

                        </div>

                    </div>

                </form>
            </div>

        </div>
    )
}

export default LoginForm;

