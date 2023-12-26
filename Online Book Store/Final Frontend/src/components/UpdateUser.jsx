import { useState } from "react";
import Navbar from "./Navbar";
import LoginServiceCustomer from "../Service/LoginServiceCustomer";
import { Dropdown } from "react-bootstrap";

var ruserId;
var user;
function UpdateUser() {
  let token = sessionStorage.getItem("user");
  const config = {
    headers: {
      authorization: `Bearer ${token}`,
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  };

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [addressLine1, setAddressLine1] = useState("");
  const [addressLine2, setAddressLine2] = useState("");
  const [city, SetCity] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [registeredDate, setRegisteredDate] = useState("");
  const [userImage, setUserImage] = useState([]);
  const [status, setStatus] = useState("");

  const handleRegister = (event) => {
    event.preventDefault();
    LoginServiceCustomer.UpdateUser(
      firstName,
      lastName,
      email,
      password,
      confirmPassword,
      dateOfBirth,
      addressLine1,
      addressLine2,
      city,
      phoneNumber,
      registeredDate
    )
      .then((result) => {
        var msg = JSON.stringify(result.message);
        var idMessage = JSON.stringify(result.data.message);
        var ketos = idMessage.slice(37, 40);
        ruserId = parseInt(ketos);
        console.log(user);
        setStatus("User Updated successful!");
      })
      .catch((err) => {
        setStatus("Internal SERVER error...please try again after some time");
      });
  };

  return (
    <div>
      <Navbar></Navbar>

      <div className="container-fluid register-form">
        <div
          className="row align-items-center justify-content-evenly "
          style={{ height: "100vh" }}
        >
          <div className="col-6 align-self-center p-5  rounded">
            <h1 className="log-in-in mb-5">Update User</h1>

            <form onSubmit={handleRegister}>
              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label disabled">First Name</label>
                  <div className="">
                    <input
                      className="form-control "
                      type="text"
                      disabled={true}
                      placeholder="First Name"
                      value={firstName}
                      onChange={(event) => setFirstName(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6">
                  <label className="form-label disabled">Last Name</label>
                  <div className="">
                    <input
                      className="form-control "
                      disabled={true}
                      type="text"
                      placeholder="Last Name"
                      value={lastName}
                      onChange={(event) => setLastName(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>
              </span>
              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">Email</label>
                  <div className="">
                    <input
                      className="form-control "
                      disabled={true}
                      type="email"
                      placeholder="abc@gmail.com"
                      value={email}
                      onChange={(event) => setEmail(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6 ">
                  <label className="form-label">Password</label>

                  <div className="">
                    <input
                      className="form-control "
                      type="password"
                      placeholder="****"
                      value={password}
                      onChange={(event) => setPassword(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>
              </span>
              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">Confirm Password</label>
                  <div className="">
                    <input
                      className="form-control "
                      type="password"
                      placeholder="Confirm Password"
                      value={confirmPassword}
                      onChange={(event) =>
                        setConfirmPassword(event.target.value)
                      }
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6 ">
                  <label className="form-label">Date Of Birth</label>

                  <div className="">
                    <input
                      className="form-control "
                      disabled={true}
                      type="date"
                      value={dateOfBirth}
                      onChange={(event) => setDateOfBirth(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>
              </span>
              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">Address Line 1</label>
                  <div className="">
                    <input
                      className="form-control "
                      type="text"
                      placeholder="Address Line 1"
                      value={addressLine1}
                      onChange={(event) => setAddressLine1(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6 ">
                  <label className="form-label">Address Line 2</label>

                  <div className="">
                    <input
                      className="form-control "
                      type="text"
                      placeholder="Address Line 2"
                      value={addressLine2}
                      onChange={(event) => setAddressLine2(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>
              </span>

              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">Mobile No</label>
                  <div className="">
                    <input
                      className="form-control "
                      type="text"
                      placeholder="Phone Number"
                      value={phoneNumber}
                      onChange={(event) => setPhoneNumber(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6">
                  <label for="city" className="form-label">
                    City
                  </label>
                  <input
                    type="text"
                    className="form-control "
                    placeholder="City"
                    id="city"
                    value={city}
                    onChange={(event) => SetCity(event.target.value)}
                    required
                  ></input>
                  <div className="valid-feedback"></div>
                </div>
              </span>

              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">User Image</label>
                  <div className="">
                    <input
                      className="form-control "
                      type="file"
                      placeholder="Customer Image"
                      value={userImage}
                      onChange={(event) => setUserImage(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6">
                  <label className="form-label">Registered Date</label>
                  <input
                    type="date"
                    className="form-control "
                    disabled={true}
                    value={registeredDate}
                    onChange={(event) => setRegisteredDate(event.target.value)}
                  ></input>
                  <div className="valid-feedback"></div>
                </div>
              </span>
              <span className="row g-3 mb-2"></span>
              <br></br>
              <div className=" d-grid gap-2 col-6 mx-auto mb-2 ">
                <button className="btn btn-success rounded" type="submit">
                  Update
                </button>
                {status ? <div className="text-success">{status}</div> : null}
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UpdateUser;
