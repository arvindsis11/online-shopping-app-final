import axios from 'axios'

const AUTH_URL = "http://localhost:8080/api/v1.0/shopping";

class authService{

    login(userDetais){
        return axios.post(AUTH_URL+"/login",userDetais);
    }
    register(user){
        return axios.post(AUTH_URL+"/register",user);
    }
    forgotPassword(username,userDetais){
        return axios.patch(AUTH_URL+`/${username}/forgot`,{
            "username":userDetais.username,
            "password":userDetais.password
        });
    }
    getUserDetails(username){
        return axios.get(AUTH_URL+`/getProfile/${username}`);
    }
    Logout = () => {
        localStorage.removeItem("user");
      };
      
    getCurrentUser = () => {
        return JSON.parse(localStorage.getItem("user"));
      }
    runLogoutTimer(timer){
        setTimeout(()=>{
            this.Logout();
        },timer);
    }
    getRole(){
        const user=JSON.parse(localStorage.getItem("user"));
        if(user){
            return user.roles;
        }
        else{
            return "";
        }
    }
    isExpired(user){
        console.log(user.accessToken);
        let expiredDate=new Date(user.expirationdate);
        console.log(expiredDate.getDate())
        let todayDate=new Date();
        if(todayDate>expiredDate){
            this.Logout();
            return true;
        }
        const timer=expiredDate.getTime()-todayDate.getTime();
        this.runLogoutTimer(timer);
        return false;
    }
}
export  default new authService();