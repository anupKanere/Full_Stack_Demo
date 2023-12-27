import logo from "./logo.svg";
import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./Layout/Navbar";
import Home from "./Pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddUser from "./Users/AddUser";
import EditUser from "./Users/EditUser";
import ViewUser from "./Users/ViewUser";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar></Navbar>
        <Routes>
          <Route exact path="/" element={<Home></Home>}></Route>
          <Route exact path="/addUser" element={<AddUser></AddUser>}></Route>
          <Route
            exact
            path="/edituser/:id"
            element={<EditUser></EditUser>}
          ></Route>
          <Route exact path="/user/:id" element={<ViewUser></ViewUser>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
