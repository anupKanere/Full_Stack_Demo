import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

function ViewUser() {
    const [user, setUser] = useState({
        name: '',
        userName: '',
        email: '',
    });
    const { id } = useParams();

    useEffect(() => {
        loadUser();
    }, []);

    const loadUser = async () => {
        const result = await axios.get(`http://localhost:8080/user/${id}`);
        setUser(result.data);
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card shadow">
                        <div className="card-body">
                            <h2 className="card-title text-center mb-4">User Details</h2>
                            <div className="card">
                                <div className="card-header">
                                    <h5>Details of {user.name}</h5>
                                    <ul className="list-group list-group-flush">
                                        <li className="list-group-item d-flex justify-content-between align-items-start">
                                            <b>Id :</b>
                                            <span className="ms-2">{user.id}</span>
                                        </li>
                                        <li className="list-group-item d-flex justify-content-between align-items-start">
                                            <b>User Name :</b>
                                            <span className="ms-2">{user.userName}</span>
                                        </li>
                                        <li className="list-group-item d-flex justify-content-between align-items-start">
                                            <b>Email_Id :</b>
                                            <span className="ms-2">{user.email}</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <Link className="btn btn-primary my-2" to="/">
                                Back to Home
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ViewUser;
