import React, { useState, useEffect } from "react";
import { Row, Col } from "reactstrap";
import Product from "./Product";
import { FaShoppingCart, FaSignOutAlt } from 'react-icons/fa';
import SearchBar from "./SearchBar"
import LoginServiceCustomer from "../Service/LoginServiceCustomer";

function AllBooks() {
  const [products, setProducts] = useState([]);

  useEffect(() => {

    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const response = await LoginServiceCustomer.getAllBooks();
      setProducts(response.data);
      console.log(response.data);
    } catch (error) {
      console.error("Error fetching books:", error);
    }
  };


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
      <h2 className="ms-2 my-4" style={{ fontSize: '24px', backgroundColor: "#5846ce", color: 'white', fontWeight: 'bold', textTransform: 'uppercase', textAlign: "center", margin: "0 1rem", padding: 10 }}>
        Showing Available Books
      </h2>
      <Row>
        {products.length > 0 ? (
          products.map((item) => (
            <Col sm="3" key={item.title}>
              <Product product={item} />
            </Col>

          ))
        ) : (
          <p>No Products under this category</p>
        )}
      </Row>

    </div>
  );
}

export default AllBooks;
