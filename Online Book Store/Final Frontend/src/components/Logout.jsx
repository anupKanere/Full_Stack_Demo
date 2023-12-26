import { Card } from "react-bootstrap";
import { CardBody, CardHeader } from "reactstrap";

function Logout() {

    const handleLogout = () => {
        sessionStorage.removeItem('user');
    }

    return (

        <div>
            <div className="container-fluid" style={{ marginLeft: "30rem", marginTop: "4rem" }}>

                <Card className="m-5 rounded text-center bg-light" style={{ width: "35%" }}>
                    <CardHeader style={{ fontSize: "1.5rem" }}>Logout</CardHeader>
                    <CardBody>
                        <div>
                            <p style={{ fontSize: "2rem" }}>Are you sure you want to log out?</p>
                        </div>
                        <a href="/" className="btn btn-secondary mt-3 pl-3 rounded" style={{ fontSize: "1.3rem" }} onClick={handleLogout}>Logout</a>
                    </CardBody>
                </Card>

            </div>
        </div>
    )
}

export default Logout;