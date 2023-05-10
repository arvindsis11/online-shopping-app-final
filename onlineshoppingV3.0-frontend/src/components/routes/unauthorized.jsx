import Card from 'react-bootstrap/Card';
export function Unauthorized() {
    return (
        <Card className="text-center bg-dark" style={{ marginTop: "8rem" }}>
            <Card.Header><h3 class="w3-center w3-animate-zoom">🚫🚫🚫🚫</h3></Card.Header>
            <Card.Body>
                <Card.Title><h1 class="w3-jumbo w3-animate-top w3-center text-danger"><strong><code>Access Denied</code></strong></h1></Card.Title>
                <Card.Text>
                    <hr class="w3-border-white w3-animate-left bg-white" style={{margin:"auto",width:"25%"}}/>
                        <h3 class="w3-center w3-animate-right text-white">You dont have permission to view this Page.</h3>
                </Card.Text>
              <h6 class="w3-center w3-animate-zoom text-danger"><u>error code:403 forbidden</u></h6>
            </Card.Body>
            <Card.Footer><h3 class="w3-center w3-animate-zoom">🚫🚫🚫🚫</h3></Card.Footer>
        </Card>
    )
}