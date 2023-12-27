import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

function EditUser() {

    let navigate = useNavigate();

    const {id} = useParams()

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

    const onSubmit = async(e) => {
        e.preventDefault();
        await axios.put(`http://localhost:8080/edituser/${id}` , user)
        navigate("/");

    }

    useEffect(() =>{
        loadUser();
    },[]);

    const loadUser = async ()=>{
        const result = await axios.get(`http://localhost:8080/user/${id}`)
        setUser(result.data)
    }

    return (
        <div className="container">
            <div className="row justify-content-center mt-5">
                <div className="col-md-6">
                    <div className="card shadow">
                        <div className="card-body">
                            <h2 className="card-title text-center mb-4">Edit User</h2>
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
                                    />
                                </div>
                                <div className="d-grid gap-2">
                                    <button type="submit" className="btn btn-primary">
                                        Update
                                    </button>
                                    <Link className="btn btn-danger" to= "/">
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

export default EditUser;
