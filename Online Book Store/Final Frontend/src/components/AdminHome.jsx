import React from "react";
import LeftSidebar from "./LeftSidebar";

function AdminHome(){

    return(
        <div className="jumbotron text-center admin-home admin-page pt-5" style={{color:"white"}}>
        <h1 className="display-4">Welcome, Admin! </h1>
        <h1 className="display-4"></h1>
        <p className="lead">Manage Users and Books.</p>
        <p className="lead pt-3">
        <a className="btn btn-info btn-lg button-cool" href="#" role="button">Learn more</a>
        </p>
        <LeftSidebar></LeftSidebar>
        </div>
    );
}

export default AdminHome;
