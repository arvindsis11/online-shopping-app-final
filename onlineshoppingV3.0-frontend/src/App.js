// import logo from './logo.svg';
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import LoginForm from './components/Login/LoginForm';
import SignUpForm from './components/SignUp/SignUpForm';
import ForgotPassword from './components/Login/ForgotPassword';
import Home from './components/Home/Home';
import Navbar from './components/Header/Navbar';
import Footer from './components/Footer/Footer';
import Footer2 from './components/Footer/Footer2'
import ProductSearch from './components/Products/ProductsSearch';
import { PrivateRoute } from './components/routes/privateRoutes';
import AddProduct from './components/Products/addProducts';
import EditProduct from './components/Products/EditProduct';
import UserProfile from './components/util/UserProfile';
import Notfound from "./components/util/Notfound";
import './App.css';

function App() {
  return (
    <div className="App">
      <div className="fixed-top">
        <Navbar></Navbar>
      </div>
      
        <Routes>
          <Route path='/login' element={<LoginForm />}></Route>
          <Route path='/register' element={<SignUpForm />}></Route>
          <Route path="/forgotpassword" element={<ForgotPassword />} />
          <Route path='/home' element={<Home />}></Route>
          <Route path="/search/:name" element={<ProductSearch />} />
          <Route path="/myprofile" element={<UserProfile/>} exact />
          <Route path="/notfound" element={<Notfound/>} exact />
          <Route element={<PrivateRoute/>}>
              <Route path="/addproduct" element={<AddProduct/>} exact />
              <Route path="/editproduct/:id" element={<EditProduct/>} exact />
          </Route>
          <Route path="/*" element={<LoginForm />} />
        </Routes>
        <div >
        <Footer2 />
      </div>
    </div>
  );
}

export default App;
