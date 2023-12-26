import "./App.css";
import { ToastContainer } from "react-toastify";
import AdminPage from "./components/AdminPage";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import UsersTable from "./components/admin components/UsersTable";
import RegisterAdmin from "./components/admin components/RegisterAdmin";
import Homepage from "./components/Homepage";
import Contact from "./components/Contact";
import About from "./components/About";
import LoginForm from "./components/LoginForm";
import RegisterCustomer from "./components/RegisterCustomer";
import Cart from "./components/Cart";
import Order from "./components/Order";
import AdminLogIn from "./components/AdminLogIn";
import AddBookForm from "./components/admin components/AddBookForm";
import AllBooks from "./components/AllProducts";
import Logout from "./components/Logout";
import NotFound from "./components/NotFound";
import UpdateUser from "./components/UpdateUser";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<Homepage />} />
        <Route exact path="/users/register-admin" element={<RegisterAdmin />} />
        <Route exact path="/users/admin-login" element={<AdminLogIn />} />
        <Route exact path="/admin" element={<AdminPage />} />
        <Route exact path="/users" element={<UsersTable />} />
        <Route exact path="/admin/books/add-book" element={<AddBookForm />} />
        <Route exact path="/users/signin" element={<LoginForm />} />
        <Route exact path="/contact" element={<Contact />} />
        <Route exact path="/about" element={<About />} />
        <Route exact path="/users/register-customer" element={<RegisterCustomer />}/>
        <Route exact path="/carts/getBooksInCart" element={<Cart />} />
        <Route exact path="/myorder" element={<Order />} />
        <Route exact path="/logout" element={<Logout />} />
        <Route exact path="*" element={<NotFound />} />
        <Route exact path="/books/getAllBooks" element={<AllBooks />} />
        <Route exact path="/UpdateUser" element={<UpdateUser />} />
      </Routes>
      <ToastContainer
        position="bottom-center"
        autoClose={1000}
      ></ToastContainer>
    </BrowserRouter>
  );
}

export default App;
