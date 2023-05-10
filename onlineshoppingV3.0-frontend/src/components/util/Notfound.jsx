
function Notfound(props) {
  return (

    <div className="card text-center" style={{ margin: "8rem" ,width:"100%"}}>
      <div className="card-header">
      <h3 class="w3-center w3-animate-zoom">🚫🚫🚫🚫</h3>
      </div>
      <div className="card-body">
        <h5 className="card-title text-danger display-4">{props.message}</h5>
        <p className="card-text">try searching with exact keyword!</p>
        <a href="/home" class="btn btn-primary">Back to homepage</a>
      </div>
      <div class="card-footer text-muted">
      <h3 class="w3-center w3-animate-zoom">🚫🚫🚫🚫</h3>
      </div>
    </div>
  );
}

export default Notfound;