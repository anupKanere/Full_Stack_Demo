
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./cart.css";
import { toast } from "react-toastify"
import LoginServiceCustomer from "../Service/LoginServiceCustomer";
import { FaShoppingCart, FaSignOutAlt } from 'react-icons/fa';
import SearchBar from "./SearchBar";

const Cart = () => {
  const [totalPrice, setTotalPrice] = useState();
  const [cart, setCart] = useState([]);
  const [status, setStatus] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    LoginServiceCustomer.getAllBooksInCart(sessionStorage.getItem("userId"))
      .then((result) => {
        setCart(result.data);
        console.log(JSON.stringify(result.data));
      })
      .catch((err) => {
        console.log("NO BOOKS");
      });
  }, []);

  useEffect(() => {
    const totalPriceSum = cart.reduce(
      (sum, item) => sum + item.sellingPrice,
      0
    );
    const formattedTotalPrice = parseFloat(totalPriceSum.toFixed(2));
    setTotalPrice(formattedTotalPrice);
  }, [cart]);

  const placeOrder = () => {
    LoginServiceCustomer.placeOrder(sessionStorage.getItem("userId")).then((result) => {
      console.log(result);
      navigate("/myorder");
      toast.message("Order Placed Successfully");
    })
      .catch((err) => {
        if (err.response) {
          // If the error has a response, it means it's coming from the backend
          const errorData = err.response.data;
          if (errorData.message) {
            const errorMessage = err.response.data.message;
            console.log(`Error: ${errorData.message}`);
            setStatus(errorMessage)
          }
          if (errorData.timeStamp) {
            // Assuming 'timeStamp' is a date, you can format it as needed
            const formattedTimestamp = new Date(errorData.timeStamp).toLocaleString();
            console.log(`Error timestamp: ${formattedTimestamp}`);
          }
        }
      });
  }

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark" style={{ fontWeight: "bold" }}>
        <div className="container-fluid" style={{ marginLeft: "3rem" }}>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-1">
              <li className="nav-item">
                <a className="nav-link" aria-current="page" href="/" style={{ fontSize: "1.3rem", marginRight: "1.3rem" }}>Home</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/about" style={{ fontSize: "1.3rem", marginRight: "1.3rem" }}>About Us</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/contact" style={{ fontSize: "1.3rem" }}>Contact Us</a>
              </li>
              <div style={{ marginLeft: "15rem" }}>

                <SearchBar />
              </div>

              <div className='buttons' style={{ marginLeft: "20rem" }}>

                <a href='/carts/getBooksInCart' className='btn btn-light ms-3'>

                  <FaShoppingCart />
                </a>
                <a href='/logout' className='btn btn-light ms-3'>

                  Logout <FaSignOutAlt />
                </a>

              </div>


            </ul>

          </div>
        </div>
      </nav>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author Name</th>
            <th>Selling Price</th>

          </tr>
        </thead>
        <tbody>
          {cart.map((data, index) => (
            <tr key={index}>
              <td>{data.bookId}</td>
              <td>{data.title}</td>
              <td>{data.authorName}</td>
              <td>{data.sellingPrice}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="card">
        <h2 className="card-title">Total Price :&nbsp;{totalPrice}</h2>
        <button className="place-order-button" onClick={placeOrder}>Place Order</button>
        {status ? <div className='text-success'>{status}</div> : null}
      </div>
    </div>
  );
};

export default Cart;
