import axios from "axios";

class CartItemService {
  static addCartItem(cartId, productId, quantity, config) {
    var data = { quantity };
    return axios.post(
      `http://localhost:8080/carts/addToCart/${cartId}/${productId}`,
      data,
      config
    );
  }
}

export default CartItemService;
