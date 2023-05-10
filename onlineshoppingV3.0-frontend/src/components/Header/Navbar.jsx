import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Dropdown from 'react-bootstrap/Dropdown';
import authService from "../ApiService/authService";
function Navbar() {
  const navigate = useNavigate();
  const [searchtext, setSearchtext] = useState('');
  const isAuthorized = authService.getRole();
  const currentUser = authService.getCurrentUser();
  function logoutHandler(e) {
    // alert("logout works");
    localStorage.clear();
    navigate("/login", { replace: true });
  }

  function regHandler(e){
    navigate("/register", { replace: true });
  }

  function profileHandler(e){
    navigate("/myprofile", { replace: true });
  }
  function handleChange(e) {
    setSearchtext(e.target.value);
    console.log(e.target.value);
  }
  function handleSubmit(e) {
    console.log("submitting: ", searchtext);
    navigate(`/search/${searchtext}`);
    console.log("*********: ", currentUser.username);
    e.preventDefault();
  }
  return (
    <div>
      <nav className="navbar navbar-dark bg-dark justify-content-between">
        <Link to="/home" className="navbar-brand text-light">Online<span style={{color:"lightseagreen"}}>Shopping</span></Link>
        {isAuthorized[0] !== undefined ?
          <form className="form-inline">
            <input
              className="form-control mr-sm-3"
              type="search"
              placeholder="Search for products testststs"
              aria-label="Search"
              onChange={(e) => handleChange(e)}
            />
            <button
              className="btn btn-outline-success my-2 my-sm-0"
              type="submit"
              onClick={(e) => handleSubmit(e)}
            >
              <i className="fa fa-search"></i>
            </button>
          </form>
          :
          <h3 className="text-dark">test</h3>
        }
        {isAuthorized[0] !== undefined ?
        <Dropdown>
        <Dropdown.Toggle className="btn btn-primary dropdown-toggle ml-2 text-white"><i className="fa d-inline fa-lg fa-user-circle-o"></i>{currentUser.username}</Dropdown.Toggle>
         <Dropdown.Menu>
           <Dropdown.Item onClick={profileHandler}><button className="btn btn-outline-success my-2 my-sm-0">View Profile</button></Dropdown.Item>
           {
             isAuthorized[0]==="ROLE_ADMIN"?
             <Dropdown.Item >
           <Link to="/addproduct">
            <button
              className="btn btn-outline-success my-2 my-sm-0"

            >
              Add Product
            </button>
          </Link>
           </Dropdown.Item>
           :
           <></>
           }
           <Dropdown.Item onClick={logoutHandler}><button
              className="btn btn-outline-danger my-2 my-sm-0"

            >
                Sign Out
            </button></Dropdown.Item>
         </Dropdown.Menu>
       </Dropdown>
          :
          <Dropdown>
           <Dropdown.Toggle className="btn btn-primary dropdown-toggle ml-2 text-white"><i className="fa d-inline fa-lg fa-user-circle-o"></i>GuestUser </Dropdown.Toggle>
            <Dropdown.Menu>
              <Dropdown.Item onClick={regHandler}>View Profile</Dropdown.Item>
              <Dropdown.Item onClick={regHandler}>Register now</Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        }
        {isAuthorized[0] !== undefined ?
          <button onClick={logoutHandler} className="btn btn-danger my-2 my-sm-0" type="submit">
            LogOut
          </button>
          :
          <button onClick={logoutHandler} className="btn btn-success my-2 my-sm-0" type="submit">
            Login
          </button>
        }
      </nav>
    </div>
  );
}

export default Navbar;
