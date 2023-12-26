import axios from 'axios';

class LoginService{


    static validateuser(email,password){
        var loginCredentials = {email,password};
        return axios.post("http://localhost:8080/users/admin-login",loginCredentials)

    }

    static registerAdmin(firstName, lastName, email, password,confirmPassword,dateOfBirth, phoneNumber, address,city,role,config){
        var registerDetails={firstName, lastName, email, password,confirmPassword,dateOfBirth, phoneNumber, address,city,role};
        return axios.post("http://localhost:8080/users/register-admin",registerDetails,config)
    }

}

export default LoginService;