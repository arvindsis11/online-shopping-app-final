import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import productService from "../ApiService/productService";
//fix-- set...fixed
const EditProduct = () => {
  const [product, setProduct] = useState({
    productName: "",
    productDescription: "",
    price: "",
    features: "",
    quantity: "",
    status: "",
  });

  const navigate = useNavigate();

  const { id } = useParams();
  console.log("this is the id ", id);

  const [msg, setMsg] = useState("");

  useEffect(() => {
    productService
      .getProductById(id)
      .then((res) => {
        setProduct(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleChange = (e) => {
    const value = e.target.value;
    setProduct({ ...product, [e.target.name]: value });
  };

  const ProductUpdate = (e) => {
    e.preventDefault();

    productService
      .editProduct(product.productName, id, product)
      .then((res) => {
        setMsg("Product updated Sucessfully");
        // navigate("/home"); fix here--
      })
      .catch((error) => {
        setMsg("something went wrong");
        console.log(error);
      });
  };

  return (
    <>
      <div className="container mt-3">
        <div className="row">
          <div className="col-md-6 offset-md-3">
            <div className="card">
              <div className="card-header fs-3 text-center">Edit Product</div>
              {msg && <p className="fs-4 text-center text-success">{msg}</p>}

              <div className="card-body">
                <form onSubmit={(e) => ProductUpdate(e)}>
                  <div className="mb-1">
                    <label>Enter Product Name</label>
                    <input
                      type="text"
                      name="productName"
                      className="form-control"
                      onChange={(e) => handleChange(e)}
                      value={product.productName}
                    />
                  </div>

                  <div className="mb-1">
                    <label>Enter Description</label>
                    <input
                      type="text"
                      name="productDescription"
                      className="form-control"
                      onChange={(e) => handleChange(e)}
                      value={product.productDescription}
                    />
                  </div>
                  <div className="mb-1">
                    <label>Enter Price</label>
                    <input
                      type="text"
                      name="price"
                      className="form-control"
                      onChange={(e) => handleChange(e)}
                      value={product.price}
                    />
                  </div>
                  <div className="mb-1">
                    <label>Enter quantity</label>
                    <input
                      type="text"
                      name="quantity"
                      className="form-control"
                      onChange={(e) => handleChange(e)}
                      value={product.quantity}
                    />
                  </div>
                  <div className="mb-1">
                    <label>Enter featurers/image</label>
                    <input
                      type="text"
                      name="features"
                      className="form-control"
                      onChange={(e) => handleChange(e)}
                      value={product.features}
                    />
                  </div>
                  <div className="mb-1">
                    <label>Enter Status</label>
                    <input
                      type="text"
                      name="status"
                      className="form-control"
                      onChange={(e) => handleChange(e)}
                      value={product.status}
                    />
                  </div>
                  <button className="btn btn-primary col-md-12">Update</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default EditProduct;