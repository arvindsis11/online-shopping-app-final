import ProductsList from "../Products/ProductsList";
import ProductCrousel from "../Products/ProductCrousel";
//fix--use some authentication

function Home() {

    return (
        <div>
            <ProductCrousel />
            <ProductsList />
            {/* <ProductsList/> */}
        </div>
    );
}

export default Home;