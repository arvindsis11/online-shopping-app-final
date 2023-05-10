import React, { useEffect, useState } from "react";
import authService from "../ApiService/authService";
import "./profile.css";

const UserProfile= () => {
    const [userdetails,setUserdetails] = useState(
      {
      username:"",
      emailid:"",
      firstname:"",
      lastname:"",
      contactno:""
    }
    )
    const currentUser = authService.getCurrentUser();
    useEffect(() => {
      init();
    }, []);
  
    const init = () => {
      authService
        .getUserDetails(currentUser.username)
        .then((res) => {
          setUserdetails(res.data);
        })
        .catch((error) => {
          console.log(error);
        });
    };
  
  return (
    <div>
      <div className="card"  style={{ marginTop: "6rem"}}>
        <img src="/images/userpic.jpg" className="mx-auto d-block" alt="John" style={{ width: "10%",borderRadius: "50%" }} />
        <h1>{userdetails.firstname}  {userdetails.lastname}</h1>
        <p className="title">{currentUser.username}</p>
        <p>{userdetails.emailid}</p>
        <a href="#">
          <i className="fa fa-dribbble" />
        </a>
        <a href="#">
          <i className="fa fa-twitter" />
        </a>
        <a href="#">
          <i className="fa fa-linkedin" />
        </a>
        
        <p>{userdetails.contactno}</p>
        
      </div>
    </div>
  );
};
export default UserProfile;
