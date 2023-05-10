import "./footer.css";
function Footer2() {

    return (
        <div>
            {/* <section>Footer Example 4</section> */}
            <footer className="footer-distributed">

                <div className="footer-left">

                    <h3>Online<span>Shopping</span></h3>

                    <p className="footer-links">
                        <a href="#" className="link-1">Home</a>

                        <a href="#">Blog</a>

                        <a href="#">Pricing</a>

                        <a href="#">About</a>

                        <a href="#">Faq</a>

                        <a href="#">Contact</a>
                    </p>

                    <p className="footer-company-name">Cognizant © 2022</p>
                </div>

                <div className="footer-center">

                    <div>
                        <i className="fa fa-map-marker"></i>
                        <p><span>444 Indore ,IT CrystalPark</span> MP, India</p>
                    </div>

                    <div>
                        <i className="fa fa-phone"></i>
                        <p>+91.6264.588.069</p>
                    </div>

                    <div>
                        <i className="fa fa-envelope"></i>
                        <p><a href="mailto:support@company.com">arvindsis35@gmail.com</a></p>
                    </div>

                </div>

                <div className="footer-right">

                    <p className="footer-company-about">
                        <span>About the company</span>
                        Cognizant is an American multinational information technology services and consulting company. It is headquartered in Teaneck, New Jersey, United States.
                    </p>

                    <div className="footer-icons">

                        <a href="https://github.com/arvindsis11"><i className="fa fa-facebook"></i></a>
                        <a href="#"><i className="fa fa-twitter"></i></a>
                        <a href="https://github.com/arvindsis11"><i className="fa fa-linkedin"></i></a>
                        <a href="https://github.com/arvindsis11"><i className="fa fa-github"></i></a>

                    </div>

                </div>

            </footer>
        </div>
    );
}

export default Footer2;