import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import productService from "../ApiService/productService";
import authService from "../ApiService/authService";

function ProductsList() {
  const [productList, setProductList] = useState([]);
  const isAuthorized = authService.getRole();
  const [msg, setMsg] = useState("");//fix me---use in pop up
  useEffect(() => {
    init();
  }, []);

  const init = () => {
    productService
      .getAllProducts()
      .then((res) => {
        setProductList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const deleteHandle = (productId) => {//working 6:31
    //e.preventDefault();
    console.log("this is delete: ", productId)
    productService
      .deleteProduct(productId)
      .then((res) => {
        init();
        setMsg("Product deleted Sucessfully");
        console.log(res);
      })
      .catch((error) => {
        console.error(error);
      })
    alert("product deleted")
  }
  const addtoCartHandle = (e) => {
    e.preventDefault();
    alert("product added to the cart");
  }
  return (
      <div className="card-columns display: inline-block bg-muted" style={{ marginTop: "1rem",background:"#dfe0e8"}}>
      {productList.map((p, num) => (
        <div key={num} className="card rounded" style={{width:"23rem"}}>
          <img
            className="card-img-top"
            src={p.features}
            alt="product .i.mage"
            style={{width:"40%"}}
          />
          <div className="card-body p-3">
            <h5 className="card-title">{p.productName}</h5>
            <p className="card-text">{p.productDescription}</p>
            <p className="card-text">{p.quantity}</p>
            {
              p.quantity===0?<p className="card-text text-danger">{p.status}</p>:<p className="card-text text-success">{p.status}</p>
            }
            {/* <p className="card-text">{p.status}</p> */}
            <p className="card-text font-weight-bold">₹{p.price}</p>
          </div>
          {isAuthorized[0] !== undefined && isAuthorized[0] === "ROLE_ADMIN" ?
            <div>
              <Link to={`/editproduct/${p.id}`}> <button className="btn btn-success m-3">update</button></Link>
              <button className="btn btn-success" onClick={() => deleteHandle(p.id)}>delete</button>
            </div>
            :
            <a className="btn btn-primary" onClick={addtoCartHandle}>
              Add to cart
            </a>}
        </div>
      ))}
    </div>
  );
}
export default ProductsList;
