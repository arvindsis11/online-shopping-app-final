import React, { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import productService from "../ApiService/productService";
import "./product.component.css";
import { Carousel, Image } from 'react-bootstrap'
function ProductCrousel(props) {

  const [productList, setProductList] = useState([]);
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
  return (

    <Carousel pause='hover' className='bg-light'>
      {productList.map((products) => (
        <Carousel.Item key={products.id} interval={2000}>
          <Link to={`/search/${products.productName}`}> 
          {/* fix here */}
            <Image className="d-block w-100"  src="https://cdn-bijap.nitrocdn.com/AuMaQmessFRMSicXmZsEecJFLEquAyoT/assets/static/optimized/rev-4b9e7e7/wp-content/uploads/2020/01/thumbnail-d771a7f4e38fcf7614f297ea6c90f497-1200x370.jpeg" style={{hight:"30px"}}  alt={products.name} fluid />
            <Carousel.Caption className='carousel-caption'>
              <h2>
                {products.productName} (R{products.price})
              </h2>
            </Carousel.Caption>
          </Link>
        </Carousel.Item>
      ))}
    </Carousel>


  );
}
export default ProductCrousel;