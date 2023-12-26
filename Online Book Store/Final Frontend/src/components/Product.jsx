import React from "react";
import { Card, CardBody, CardSubtitle, CardTitle, Button } from "reactstrap";
import { toast } from "react-toastify";
import { useState } from "react";
import LoginServiceCustomer from "../Service/LoginServiceCustomer";

function Product({ product }) {
  const [addingToCart, setAddingToCart] = useState(false);

  const [isHovered, setIsHovered] = useState(false);
  const [isClicked, setIsClicked] = useState(false);
  const addToCart = async () => {
    setAddingToCart(true);
    try {
      const response = LoginServiceCustomer.addToCart(
        sessionStorage.getItem("userId"),
        product.bookId
      );

      if (response.status === 200) {
        toast.success("Product added to cart!");
      }
    } catch (error) {
      toast.error("Error adding product to cart.");
    }
    setAddingToCart(false);
  };

  const randomSeed = Math.floor(Math.random() * 1000);

  const width = 300;
  const height = 200;

  const imageUrl = `https://picsum.photos/seed/${randomSeed}/${width}/${height}`;

  return (
    <div>
      <Card
        outline
        color="dark"
        className="ms-3 shadow rounded"
        style={{
          width: "15rem",
          marginBottom: "4rem",
        }}
      >
        <img alt="Sample" src={imageUrl} />
        <CardBody>
          <CardTitle>
            <h4 className="text-info">{product.title}</h4>
          </CardTitle>

          <CardSubtitle className="mb-2 text" tag="h6">
            Marked Price: {product.markedPrice}
            <br />
            Selling Price: {product.sellingPrice}
            <br />
            Author: {product.authorName}
            <br />
            Publication Date: {product.publicationDate}
            <br />
            Edition: {product.edition}
            <br />
            Genre: {product.genre}
            <br />
            Stock: {product.stock}
            <br />
          </CardSubtitle>
          <Button
            onClick={addToCart}
            color="warning"
            outline
            style={{
              backgroundColor: isClicked
                ? "red"
                : isHovered
                ? "orange"
                : "lightseagreen",
              color: isClicked ? "white" : isHovered ? "white" : "black",
              border: "1px solid black",
              transition: "background-color 0.3s, color 0.3s",
            }}
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
            onMouseDown={() => setIsClicked(true)}
            onMouseUp={() => setIsClicked(false)}
          >
            Add to Cart
          </Button>
        </CardBody>
      </Card>
    </div>
  );
}

export default Product;
