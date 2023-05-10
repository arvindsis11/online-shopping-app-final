import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import './login.component.css';
import authService from "../ApiService/authService";
function LoginForm() {

  const navigate = useNavigate();
  const [userError, setUserError] = useState(false);
  const [passError, setPassError] = useState(false);
  const [passwordShown, setPasswordShown] = useState(false);
  const [userDetais, setUserDetails] = useState({
    username: "",
    password: ""
  }
  );


  const togglePassword = (e) => {
    // When the handler is invoked
    // inverse the boolean state of passwordShown
    e.preventDefault();
    setPasswordShown(!passwordShown);
  };

  function loginHandle(e) {
    console.log(userDetais);
    authService
      .login(userDetais)
      .then((res) => {
        console.log(res);
        localStorage.setItem("user", JSON.stringify(res.data));
        console.log(localStorage.getItem('user'));
        navigate("/home", { replace: true });
        alert("logged in");
      })
      .catch((error) => {
        console.log(error);
      });
    alert("submitted")
    e.preventDefault();

  }
  function userHandler(e) {
    const item = e.target.value;
    if (item.length > 4) {
      setUserDetails({
        ...userDetais, [e.target.name]: e.target.value.trim()
      });
      setUserError(false);
    } else {
      setUserError(true);
    }
  }
  function passwordHandler(e) {
    const item = e.target.value;
    if (item.length > 4) {
      setUserDetails({
        ...userDetais, [e.target.name]: e.target.value.trim()
      });
      setPassError(false);
    } else {
      setPassError(true);
    }
  }
  return (
    <div>
      <header className="App-header">
        <div
          className="container card border border-info mb-3 mt-4 rounded"
          style={{ width: "29rem"}}
        >
          <div className="m-4">
            <div className="blockquote">
              {/* <button
                type="button"
                className="btn btn-info w-50 p-3 mb-4 font-weight-bold"
              > */}
              <i className="fa fa-user-circle-o text-dark fa-5x" aria-hidden="true" style={{width:"5rem"}}></i>
              {/* </button> */}
            </div>
            <h2 className="mb-5 text-dark">Sign in your Account</h2>
            <form>
              <div className="form-group form-row">
                <div className="col mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="inputUsername1"
                    name="username"
                    placeholder="Username"
                    onChange={(e) => userHandler(e)}
                  />
                  {userError ? <small className="text-danger">user is invalid</small> : ""}
                </div>
              </div>
              <div className="form-group form-row">
                <div className="col mb-3">
                  <input
                    type={passwordShown ? "text" : "password"}
                    className="form-control"
                    id="inputPassword4"
                    name="password"
                    placeholder="Password"
                    onChange={passwordHandler}
                  />
                  <div>
                    <i alt="show" className="fa fa-eye eye-show text-dark"
                      onClick={togglePassword} ></i>
                  </div>
                  {passError ? <small className="text-danger">password is invalid</small> : ""}
                </div>
              </div>
              <div className="form-group row">
                <div className="col-md-6 col-sm-4">
                </div>
                <div className="col-md-6 col-sm-6">
                  <Link className="align-self-end" to="/forgotpassword" ><small>forgot password?</small></Link>
                </div>
              </div>
              <div className="form-group row">
                <div className="col-md-6">
                  <Link to="/register" > <button className="btn btn btn-link p-3" type="submit">
                    Create account
                  </button></Link>
                </div>
                <div className="text-right col-md-6">
                  <Link to="/home"><button onClick={(e) => loginHandle(e)} className="btn text-white bg-dark p-3" type="submit">
                    Login
                  </button></Link>
                </div>
              </div>
            </form>
          </div>
        </div >
      </header >
    </div >
  );
}

export default LoginForm;
