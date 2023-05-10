import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import authService from "../ApiService/authService";
import './signup.component.css'
function SignUpForm(params) {
  const navigate = useNavigate();

  const [formErr, setFormErr] = useState("");
  const [passwordShown, setPasswordShown] = useState(false);
  const [user, setUserDetails] = useState({
    firstname: "",
    lastname: "",
    username: "",
    emailid: "",
    password: "",
  });
  const togglePassword = (e) => {
    // When the handler is invoked
    // inverse the boolean state of passwordShown
    e.preventDefault();
    setPasswordShown(!passwordShown);
  };

  const changeHandler = (e) => {
    const { name, value } = e.target;
    setUserDetails({
      ...user,
      [name]: value,
    });
  };
  const signupHandler = (e) => {
    console.log(user);
    authService.register(user)
      .then((res) => {
        alert(res.data.message);
        navigate("/login", { replace: true });
      })
      .catch((error) => {
        console.log(error.response.data);
        setFormErr(error.response.data);
      });
    e.preventDefault();
  };

  // useEffect(() => {
  //   if (Object.keys(formErrors).length === 0 && isSubmit) {
  //     console.log(user);
  //     authService.register(user)
  //     .then((res) => {
  //       alert(res.data.message);
  //       navigate("/login", { replace: true });
  //     });
  //   }
  // }, [formErrors]);
  return (
    <div>
      <header className="App-header">
        <div
          className="container card border border-info mb-3 mt-4 rounded"
          style={{ width: "29rem" }}
        >
          <div className="m-4">
            <div className="blockquote">
            <i className="fa fa-user-circle-o text-dark fa-5x" aria-hidden="true" style={{width:"5rem"}}></i>
              <div className="text-danger">
                {
                  formErr !== "" ? <p>{formErr}</p> : ""
                }
              </div>
            </div>
            <h2 className="mb-5 text-dark">Create your Account</h2>
            <form id="register11">
              <div className="form-group form-row">
                <div className="col">
                  <input
                    type="text"
                    className="form-control"
                    name="firstname"
                    placeholder="First name"
                    onChange={changeHandler}
                    value={user.firstname}
                  />
                </div>
                <div className="col mb-3">
                  <input
                    type="text"
                    className="form-control"
                    name="lastname"
                    placeholder="Last name"
                    onChange={changeHandler}
                    value={user.lastname}
                  />
                </div>
              </div>
              <div className="form-group form-row">
                <div className="col mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="inputUsername4"
                    name="username"
                    placeholder="Username"
                    onChange={changeHandler}
                    value={user.username}
                  />
                </div>
              </div>
              <div className="form-group form-row">
                <div className="col mb-3">
                  <input
                    type="email"
                    className="form-control"
                    id="emailInputValue"
                    name="emailid"
                    placeholder="Email ID"
                    onChange={changeHandler}
                    value={user.emailid}
                    required
                  />
                </div>
              </div>
              <div className="form-group form-row">
                <div className="col-md-6 ">
                  <input
                    type={passwordShown ? "text" : "password"}
                    className="form-control"
                    id="inputPassword1"
                    name="password"
                    placeholder="Password"
                    onChange={changeHandler}
                    value={user.password}
                  />
                </div>
                <div className="col-md-6">
                  <input
                    type={passwordShown ? "text" : "password"}
                    className="form-control"
                    id="inputPassword2"
                    name="cpassword"
                    placeholder="Password"
                    onChange={changeHandler}

                  />
                  <div>
                    <i alt="show" className="fa fa-eye eye-show text-dark"
                      onClick={togglePassword} ></i>
                  </div>
                </div>
                {/* <div className="col">
                <small id="passwordHelpBlock" className="form-text text-muted">
                  Your password must be 8-20 characters long, contain letters
                  and numbers
                </small>
              </div> */}
              </div>
              <div className="form-group row">
                <div className="col-md-6">
                  <Link to="/login" > <button className="btn btn-link" type="submit">
                    Sign in instead
                  </button></Link>
                </div>
                <div className="card text-right col-md-6">
                  <button onClick={signupHandler} className="btn text-white bg-dark p-3" type="submit">
                    Next
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </header>
    </div>

  );
}

export default SignUpForm;
