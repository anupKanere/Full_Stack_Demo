import { FaSearch } from "react-icons/fa";
import { Container } from "reactstrap";
import { useState } from "react";
import "./SearchBar.css";
import LoginServiceCustomer from "../Service/LoginServiceCustomer";

function SearchBar() {
  const [searchTerm, setSearchTerm] = useState("");

  const handleChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSearch = (searchTerm) => {
    LoginServiceCustomer.searchBook(searchTerm)
      .then((response) => response.json())
      .then((data) => {
        // Handle the search results
        console.log("Search results:", data);
      })
      .catch((error) => {
        // Handle any errors
        console.error("Error:", error);
      });
    // Implement your search logic here
    console.log("Searching for:", searchTerm);
  };

  return (
    <Container>
      <div className="search-bar">
        <div className="search-input-container">
          <input
            type="search"
            onChange={handleChange}
            value={searchTerm}
            placeholder="Search Books Here"
            className="form-control"
          />
          <button onClick={handleSearch} className="search-button">
            <FaSearch />
          </button>
        </div>
      </div>
    </Container>
  );
}

export default SearchBar;
