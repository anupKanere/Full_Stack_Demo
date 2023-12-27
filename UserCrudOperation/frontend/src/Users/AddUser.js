import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function AddUser() {

    let navigate = useNavigate();

    const [user, setUser] = useState({
        name: "",
        userName: "",
        email: ""
    })

    const { name, userName, email } = user

    const onInputChange = (e) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value,
        });
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post("http://localhost:8080/addUser", user)
        navigate("/");

    }

    return (
        <div className="container">
            <div className="row justify-content-center mt-5">
                <div className="col-md-6">
                    <div className="card shadow">
                        <div className="card-body">
                            <h2 className="card-title text-center mb-4">Register User</h2>
                            <form onSubmit={(e) => onSubmit(e)}>
                                <div className="mb-3">
                                    <label htmlFor="UserName" className="form-label">
                                        User Name:
                                    </label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Enter Your User Name"
                                        name="userName"
                                        value={userName}
                                        onChange={(e) => onInputChange(e)}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="Name" className="form-label">
                                        Name:
                                    </label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Enter Your Name"
                                        name="name"
                                        value={name}
                                        onChange={(e) => onInputChange(e)}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="Email" className="form-label">
                                        E-mail:
                                    </label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        placeholder="Enter Your e-mail address"
                                        name="email"
                                        value={email}
                                        onChange={(e) => onInputChange(e)}
                                        required
                                    />
                                </div>
                                <div className="d-grid gap-2">
                                    <button type="submit" className="btn btn-primary">
                                        Submit
                                    </button>
                                    <Link className="btn btn-danger" to="/">
                                        Cancel
                                    </Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AddUser;
