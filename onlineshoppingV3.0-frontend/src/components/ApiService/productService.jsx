import axios from 'axios'

const PRODUCT_URL = "http://localhost:8080/api/v1.0/shopping/products";

class productService{
    getAllProducts(){
        return axios.get(PRODUCT_URL+"/all");
    }
    getProductById(productId){
        return axios.get(PRODUCT_URL+`/${productId}`);
    }
    searchProduct(itemname){
        console.log(itemname)
        let token=this.bearerAuth();
        return axios.get(PRODUCT_URL+`/search/${itemname}`,{
            headers: {
                'Authorization': token
              }
        });
    }
    addProduct(itemdetails){
        let token=this.bearerAuth();
        console.log(token);
        return axios.post(PRODUCT_URL+"/add",itemdetails,{
            headers: {
              'Authorization': token
            }
        });
    }
    deleteProduct(productId,productName){
        let token=this.bearerAuth();
        console.log("inside deleteproduct service");
        console.log(token);
        return axios.delete(PRODUCT_URL+`/${productName}/delete/${productId}`,{
            headers: {
              'Authorization': token
            }
        });
    }
    editProduct(productName,productId,productDto){
        let token = this.bearerAuth();
        console.log("inside editproduct service");
        return axios.patch(PRODUCT_URL+`/${productName}/update/${productId}`,productDto,{
            headers: {
              'Authorization': token
            }
        });
    }
    bearerAuth() {
        const user=JSON.parse(localStorage.getItem("user"));
        console.log("bearerAuth"+user.token);
        return `Bearer ${user.token}`
    }
}
export  default new productService