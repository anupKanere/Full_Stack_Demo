import React from "react";
import { useState, useEffect } from "react";
import { Table, Button, Row, Col } from "reactstrap"
import axios from "axios";
import { toast } from "react-toastify";
import { FaSignOutAlt, FaBook } from "react-icons/fa";


function UsersTable() {
    const [users, setUsers] = useState([]);
    let token = sessionStorage.getItem("user");
    console.log(token);
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };


    function handleDelete(userId) {
        axios.delete(`http://localhost:8080/users/deleteUser/${userId}`, config).then(
            (response) => {
                toast.success("User deleted sucessfully!!");
                window.location.reload();
            },
            (error) => {
                toast.error("Server error/insufficient privileges");
            }
        )


    }
    useEffect(() => {
        getAllUsers();
    }, [])

    const getAllUsers = () => {
        axios.get("http://localhost:8080/users/getAllUsers", config).then((response) => {
            setUsers(response.data);
            console.log(response.data);
        },
            (error) => {
                toast.error("server down123/unauthorized", { position: "bottom-center" })
            })
    }

    return (
        <div className="user-table admin-page">
            <nav className="navbar navbar-expand-lg navbar-light bg-light" style={{ fontWeight: "bold" }}>
                <div className="container-fluid" style={{ marginLeft: "3rem" }}>
                    <a className="navbar-brand" href="/"><FaBook height={65} width={90}></FaBook>OnlineBookStore</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarSupportedContent" style={{ marginLeft: "25rem" }}>
                        <ul className="navbar-nav me-auto mb-2 mb-lg-1">

                            <li className="nav-item">
                                <a className="nav-link" href="/about" style={{ fontSize: "1.3rem", marginRight: "1.3rem" }}>About Us</a>
                            </li>

                            <li className="nav-item">
                                <a className="nav-link" href="/contact" style={{ fontSize: "1.3rem" }}>Contact Us</a>
                            </li>

                            <div className='buttons' style={{ marginLeft: "30rem" }}>
                                <a href='/logout' className='btn btn-light ms-3'>
                                    Logout <FaSignOutAlt />
                                </a>
                            </div>
                        </ul>
                    </div>
                </div>
            </nav>
            <Row className="text-center justify-content-evenly align-items-center">
                <Col className="col-8">
                    <h2 className="pb-2" style={{ color: "white", marginTop: "5rem", marginBottom: "2rem" }}>All User Accounts</h2>
                    <Table responsive className=" border"
                        size="sm"
                        dark
                        hover
                        striped>
                        <thead>
                            <tr>
                                <td>UserId</td>
                                <td>First Name</td>
                                <td>Last Name</td>
                                <td>Email</td>
                                <td>Mobile Number</td>
                                <td>Delete User</td>
                            </tr>
                        </thead>
                        <tbody>

                            {
                                users.length > 0 && users.filter(item => item.role === "CUSTOMER").map(item => (
                                    <tr key={item.userId}>
                                        <td>{item.userId}</td>
                                        <td>{item.firstName}</td>
                                        <td>{item.lastName}</td>
                                        <td>{item.email}</td>
                                        <td>{item.phoneNumber}</td>

                                        <td>
                                            <Button
                                                type="Submit"
                                                className="btn btn-danger"
                                                onClick={() => handleDelete(item.userId)}
                                            >
                                                Delete
                                            </Button>
                                        </td>
                                    </tr>
                                ))
                            }

                        </tbody>
                    </Table>
                </Col>
            </Row>
        </div>
    );
}

export default UsersTable;