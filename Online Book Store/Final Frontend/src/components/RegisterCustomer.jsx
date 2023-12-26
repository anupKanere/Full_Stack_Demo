import { useState } from "react";
import Navbar from "./Navbar";
import LoginServiceCustomer from "../Service/LoginServiceCustomer";
import { useNavigate } from "react-router-dom";
import format from "date-fns/format";

function RegisterCustomer() {
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [city, setCity] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const currentDate = new Date();
  const formattedDate = format(currentDate, "yyyy-MM-dd");
  const [registeredDate, setRegisteredDate] = useState(formattedDate);
  const [status, setStatus] = useState("");
  const [address, setAddress] = useState("");
  const [role, setRole] = useState("CUSTOMER");

  const navigate = useNavigate();

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  const toggleConfirmPasswordVisibility = () => {
    setShowConfirmPassword(!showConfirmPassword);
  };

  const handleRegister = (event) => {
    event.preventDefault();
    LoginServiceCustomer.registerUser(
      firstName,
      lastName,
      email,
      password,
      confirmPassword,
      dateOfBirth,
      address,
      city,
      phoneNumber,
      registeredDate,
      role
    )
      .then((result) => {
        var msg = JSON.stringify(result.message);

        console.log(msg);
        setStatus("Registration successful!");

        navigate("/users/signin");
      })
      .catch((err) => {
        if (err.response) {
          const errorData = err.response.data;
          const formattedTimestamp = new Date(
            errorData.timeStamp
          ).toLocaleString();
          if (errorData.message) {
            setStatus(`Error: ${errorData.message} `);
            console.log(`${formattedTimestamp}`);
          }
        } else {
          // Handle non-backend errors
          setStatus("An error occurred.Please try later");
        }
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
            <h1 className="log-in-in mb-5">Register User</h1>

            <form onSubmit={handleRegister}>
              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">First Name</label>
                  <div className="">
                    <input
                      className="form-control "
                      maxLength={20}
                      minLength={3}
                      type="text"
                      placeholder="First Name"
                      value={firstName}
                      onChange={(event) => setFirstName(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6">
                  <label className="form-label">Last Name</label>
                  <div className="">
                    <input
                      className="form-control "
                      type="text"
                      maxLength={20}
                      minLength={3}
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
                      type="email"
                      placeholder="abc@gmail.com"
                      value={email}
                      onChange={(event) => setEmail(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6">
                  <label className="form-label">Password</label>
                  <div className="input-group">
                    <input
                      className="form-control"
                      type={showPassword ? "text" : "password"}
                      placeholder="****"
                      value={password}
                      onChange={(event) => setPassword(event.target.value)}
                      required
                    />
                    <div className="input-group-append">
                      <span
                        className="input-group-text"
                        onClick={togglePasswordVisibility}
                        style={{ cursor: "pointer" }}
                      >
                        {showPassword ? "Hide" : "Show"}
                      </span>
                    </div>
                  </div>
                </div>
              </span>
              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">Confirm Password</label>
                  <div className="input-group">
                    <input
                      className="form-control"
                      type={showConfirmPassword ? "text" : "password"}
                      placeholder="Confirm Password"
                      value={confirmPassword}
                      onChange={(event) =>
                        setConfirmPassword(event.target.value)
                      }
                      required
                    />
                    <div className="input-group-append">
                      <span
                        className="input-group-text"
                        onClick={toggleConfirmPasswordVisibility}
                        style={{ cursor: "pointer" }}
                      >
                        {showConfirmPassword ? "Hide" : "Show"}
                      </span>
                    </div>
                  </div>
                </div>

                <div className="col-md-6 ">
                  <label className="form-label">Date Of Birth</label>

                  <div className="">
                    <input
                      className="form-control "
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
                  <label className="form-label">Role</label>
                  <div className="">
                    <input
                      className="form-control "
                      type="text"
                      placeholder="Address Line 1"
                      value={role}
                      onChange={(event) => setRole(event.target.value)}
                      disabled
                    ></input>
                  </div>
                </div>
                <div className="col-md-6">
                  <label className="form-label">Address</label>
                  <div className="">
                    <textarea
                      className="form-control"
                      placeholder="Address"
                      value={address}
                      onChange={(event) => setAddress(event.target.value)}
                      required
                    ></textarea>
                  </div>
                </div>
              </span>

              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">Mobile No</label>
                  <div className="">
                    <input
                      className="form-control "
                      type="number"
                      maxLength={10}
                      placeholder="Phone Number"
                      value={phoneNumber}
                      onChange={(event) => setPhoneNumber(event.target.value)}
                      required
                    ></input>
                  </div>
                </div>

                <div className="col-md-6">
                  <label className="form-label">City</label>

                  <input
                    className="form-control "
                    type="text"
                    placeholder="City"
                    value={city}
                    onChange={(event) => setCity(event.target.value)}
                    required
                  ></input>
                  <div className="valid-feedback"></div>
                </div>
              </span>

              <span className="row g-3 mb-2">
                <div className="col-md-6">
                  <label className="form-label">Registered Date</label>
                  <input
                    type="date"
                    className="form-control "
                    value={registeredDate}
                    onChange={(event) => setRegisteredDate(event.target.value)}
                    disabled
                  ></input>
                  <div className="valid-feedback"></div>
                </div>
              </span>
              <span className="row g-3 mb-2"></span>
              <br></br>
              <div className=" d-grid gap-2 col-6 mx-auto mb-2 ">
                <button className="btn btn-success rounded" type="submit">
                  Register
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

export default RegisterCustomer;
