import React, { useEffect, useState } from "react";
import {Link, useParams} from 'react-router-dom';
import productService from "../ApiService/productService";
import authService from "../ApiService/authService";
import Products from "./ProductCrousel";
import Notfound from "../util/Notfound";
function ProductSearch(props) {
  const [productList, setProductList] = useState([]);
  const isAuthorized = authService.getRole();
  const [err,setErr] = useState("");
  const {name}=useParams();
  console.log("searchkey",name)
  useEffect(() => {
    init();
  }, [name,err]);

  const init = () => {
    productService
      .searchProduct(name)
      .then((res) => {
        console.log('products: ',res.response);
        console.log(err);
        setProductList(res.data);
      })
      .catch((error) => {
        console.log("+++++++++++++++++++++++++++++");
        setErr(error.response.data);
        console.log(err+"---------");
      });
  };

  const deleteHandle = (e) => {
    e.preventDefault();
    alert("product deleted")
    console.log(e);
  }
  return (
    <div className="d-flex" style={{ marginTop: "4rem", marginLeft: "3rem" }}>
      {
      err.data!==undefined?
      productList.map((p, num) => (
        <div key={num} className="card p-1" style={{ width: "18rem" }}>
          <img
            className="card-img-top"
            src={p.features}
            alt="Card image cap"
          />
          <div className="card-body p-1">
            <h5 className="card-title">{p.productName}</h5>
            <p className="card-text">{p.productDescription}</p>
            <p className="card-text font-weight-bold">{p.status}</p>
            <p className="card-text font-weight-bold">₹{p.price}</p>
          </div>
          {isAuthorized[0] !== undefined && isAuthorized[0] === "ROLE_ADMIN" ?
            <div>
              <button className="btn btn-success m-3">update</button>
              <button className="btn btn-success" onClick={deleteHandle}>delete</button>
            </div>
            :
            <a className="btn btn-primary">
              Add to cart
            </a>}

        </div>
      ))
    :
     <Notfound message={err}></Notfound>
    }
    </div>
  );
}
export default ProductSearch;
