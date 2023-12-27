
import React, { useEffect, useState } from "react";
import axios from 'axios';
import { Link, useParams } from "react-router-dom";

function Home() {

    const [users, setUsers] = useState([])

    const {id} = useParams

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        const result = await axios.get("http://localhost:8080/getAll")
        setUsers(result.data);
    }

    const headingRowStyle = {
        borderBottom: "2px solid #000", // Dark border for bottom
    };

    const deleteUser = async(id)=>{
        await axios.delete(`http://localhost:8080/user/${id}`)
        loadUsers();
    }
    return (
        <div className="container">
            <div className="py-4">
                <table className="table table-bordered table-striped shadow">
                    <thead style={headingRowStyle}>
                        <tr className="custom-border">
                            <th scope="col">Id</th>
                            <th scope="col">UserName</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            users.map((user, index) => (
                                <tr>
                                    <th scope="row" key={index}>{index + 1}</th>
                                    <td>{user.userName}</td>
                                    <td>{user.name}</td>
                                    <td>{user.email}</td>
                                    <td>
                                        {/* <button className="btn btn-primary mx-2">View</button> */}
                                        <Link className="btn btn-primary mx-2" to={`/user/${user.id}`}>View</Link>
                                        <Link className="btn btn-warning mx-2" to={`/edituser/${user.id}`}>Edit</Link>
                                        <button className="btn btn-danger mx-2"
                                        onClick={()=> deleteUser(user.id)}
                                        >Delete</button>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Home;
