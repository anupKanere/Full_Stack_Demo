import axios from 'axios';

class LoginServiceCustomer{

    static validateuser(email,password){
        var loginCredentials = {email,password};
        return axios.post("http://localhost:8080/users/signin",loginCredentials)

    }

    static registerUser(firstName, lastName, email, password,confirmPassword,dateOfBirth,address,city,phoneNumber,registeredDate,role){
        var registerCredentials={firstName, lastName, email, password, confirmPassword,dateOfBirth,address,city,phoneNumber,registeredDate,role};
        return axios.post("http://localhost:8080/users/register-customer",registerCredentials);
    }

    static getUser(){
        return axios.get("http://localhost:8080/users/4");
    }
    static getAllCities(){
        return  axios.get("http://localhost:8080/cities")
    }

    static addUserCart(userId,config){
        
        return axios.post(`http://localhost:8080/carts/create`);
    }
    static getAllBooks(){
        return axios.get("http://localhost:8080/books/getAllBooks");
    }
    static buyNow(userId){

        return axios.post("http://localhost:8080/carts/buyNow", userId);
    }

    static addToCart(userId,id){
        return axios.post(
            `http://localhost:8080/carts/addToCart?userId=${userId}&bookId=${id}`);
    }

    static searchBook(title){
        return axios.get("http://localhost:8080/books/getBooksByTitle",title);
    }

    static getAllBooksInCart(userId){
        return axios.get(`http://localhost:8080/carts/getBooksInCart?userId=${userId}`)
    }

    static placeOrder(userId){
        return axios.post(`http://localhost:8080/carts/buyNow?userId=${userId}`)
    }
    static addProduct(title, markedPrice, sellingPrice, stock, edition, genre, publicationDate, description,author,languageNames,config){
        var productDetails = {title, markedPrice, sellingPrice, stock, edition, genre, publicationDate, description,author,languageNames};
        return axios.post("http://localhost:8080/admin/books/add-book",productDetails,config);

    }
}

export default LoginServiceCustomer;