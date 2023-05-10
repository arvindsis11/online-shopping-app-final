import { useEffect } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import authService from "../ApiService/authService";
import { Unauthorized } from "./unauthorized";
export function PrivateRoute(){
    const auth=authService.getRole();
    console.log(auth);
    const navigate=useNavigate();
    useEffect(()=>{
        if(!localStorage.getItem("user")){
            navigate("/login");
        }
    },[]);
    return(
        auth[0]!==undefined?auth[0]==="ROLE_ADMIN"?<Outlet/>:<Unauthorized />:<div>Unauthorized</div>
    );
}