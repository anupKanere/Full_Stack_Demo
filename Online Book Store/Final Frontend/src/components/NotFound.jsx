import React from "react";
import { Card, CardHeader } from "reactstrap";

function NotFound (){
    return(
        <div className="container-fluid text-center mt-5" style={{width:"50%"}}>
            <Card>
                <CardHeader className="text-center" style={{color:"red", fontWeight:"bold", fontSize:"1.5rem"}}>Error</CardHeader>
            <p className="mt-4" style={{color:"red", fontSize:"1.3rem"}}>The URL you are looking for  is NOT AVAILABLE, Please Enter valid URL!!!</p>
            </Card>
        </div>
    )
}

export default NotFound;