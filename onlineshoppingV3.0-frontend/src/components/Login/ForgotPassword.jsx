import { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import authService from "../ApiService/authService";

function Forgotpassword() {

  const navigate = useNavigate();

  const [username, setUsername] = useState('');
  const [matchpassword, setMatchPassword] = useState(false);
  const [userError, setUserError] = useState(false);
  const [passError, setPassError] = useState(false);
  const [passwordShown, setPasswordShown] = useState(false);
  const [userDetais, setUserDetails] = useState({
    username: "",
    password: "",
    confirmpassword: ""
  }
  );
  useEffect(() => {
    setMatchPassword(false);
  }, [userDetais.confirmpassword, userDetais.password]);

  const togglePassword = (e) => {
    // When the handler is invoked
    // inverse the boolean state of passwordShown
    e.preventDefault();
    setPasswordShown(!passwordShown);
  };

  function loginHandle(e) {


    console.log(userDetais.password + " " + userDetais.confirmpassword);
    if (userDetais.password !== userDetais.confirmpassword)
      setMatchPassword(true);
    else {
      authService.forgotPassword(userDetais.username, userDetais)
        .then((res) => {
          console.log(res);
          alert(res);
          navigate("/login", { replace: true });
        })
        .catch((error) => {
          console.log(error);
        });
    }
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
  function passwordHandler2(e) {
    const item = e.target.value;
    console.log(item + "item" + e.target.name)
    if (item.length > 3) {
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
          className="container card border border-info mb-3 mt-1 rounded"
          style={{ width: "29rem" }}
        >
          <div className="m-4">
            <div className="blockquote">
            <i className="fa fa-user-circle-o text-dark fa-5x" aria-hidden="true" style={{width:"5rem"}}></i>
            </div>
            <h2 className="mb-5 text-dark">Reset your password</h2>
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
                  {passError ? <small className="text-danger">password is invalid</small> : ""}
                </div>
                <div className="form-group form-row">
                  <div className="col mb-3">
                    <input
                      type={passwordShown ? "text" : "password"}
                      className="form-control"
                      id="inputPassword4"
                      name="confirmpassword"
                      placeholder="Confirm Password"
                      onChange={passwordHandler2}
                    />
                    <div>
                      <i alt="show" className="fa fa-eye eye-show text-dark"
                        onClick={togglePassword} ></i>
                    </div>
                    {passError ? <small className="text-danger">password is invalid</small> : ""}
                  </div>
                </div>
              </div>
              {matchpassword ? <small className="text-danger">password not match with confirmpassword</small> : ""}
              <div className="form-group row">
                <div className="col-md-6">
                  <Link to="/login" > <button className="btn btn-link" type="submit">
                    login instead
                  </button></Link>
                </div>
                <div className="card text-right col-md-6">
                  <Link to="/login"><button onClick={(e) => loginHandle(e)} className="btn text-white bg-dark p-3" type="submit">
                    Next
                  </button></Link>
                </div>
              </div>
            </form>
          </div>
        </div>
      </header>
    </div>
  );
}

export default Forgotpassword;