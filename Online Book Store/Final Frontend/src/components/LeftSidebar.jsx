import React from "react";
import { Link } from "react-router-dom";
import { ListGroup, Container, Row } from "reactstrap";

function LeftSidebar() {

    return (
        <>
            <Container >
                <Row className="text-center justify-content-evenly align-items-center">
                    <div className="mt-3 col-4 pt-5">
                        <ListGroup color="dark">

                            <Link className="list-group-item list-group-item-action mb-3" to="/users/register-customer" tag="a">
                                Add User
                            </Link>

                            <Link className="list-group-item list-group-item-action mb-3" to="/users" tag="a" >
                                Show List of Users
                            </Link>

                            <Link className="list-group-item list-group-item-action mb-3" to="/admin/books/add-book" tag="a" >
                                Add a Book
                            </Link>
                        </ListGroup>
                    </div>
                </Row>
            </Container>

        </>
    );
}

export default LeftSidebar; 