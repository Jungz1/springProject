import { useEffect, useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';


function App() {
  const[image,setImage]=useState(null);
  const[name,setName]=useState("");
  const[description,setDescription]=useState("");
  const[category,setCategory]=useState("");
  const[price,setPrice]=useState("");
  const[products,setProducts]=useState([]);
  const [filterCategory, setFilterCategory] = useState("");
  useEffect(() => {
    fetchProducts();
  }, []);
  const handleFileChange=(e)=>{setImage(e.target.files[0]);}
  const handleSubmit=async (e) => {
    e.preventDefault();


    const formData=new FormData();
    formData.append("name",name);
    formData.append("description",description);
    formData.append("category",category);
    formData.append("image",image);
    formData.append("price",price);
try{
    const response= await fetch("http://localhost:8089/products/add",{method:"POST",body:formData});


    if(response.ok){
      alert('Product uploaded successfully')
      fetchProducts();
    }
    else{
      alert("failed to upload product");
    }


}
catch(error){
  console.error("Error:",error);
  alert("Error uploading the product")
}
  };




  const fetchProducts=async () => {
    try{
      const response= await fetch("http://localhost:8089/products/all");
      if(response.ok){
       const data=await response.json();
       setProducts(data);
      }
      else{
       console.error("failed to fetch products")
      }
    }
    catch(error){
      console.error("Error:",error);

    }
  };

  const handleFilterChange = (e) => {
      setFilterCategory(e.target.value);
    };

    const filteredProducts = filterCategory
      ? products.filter((product) => product.category === filterCategory)
      : products;
  return (
    <div className="container">
    <h1 className="my-4">Sporty Shoes</h1>
          <form onSubmit={handleSubmit} className="mb-4">
            <div className="form-group">
              <label>Shoe Name</label>
              <input
                type="text"
                className="form-control"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label>Description</label>
              <textarea
                className="form-control"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label>Category</label>
              <input
                type="text"
                className="form-control"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
                required
              />
            </div>
            <div>
            <label>Product Image</label> <input type="file" onChange={handleFileChange} required/>
            </div>
            <div className="form-group">
              <label>Price</label>
              <input
                type="text"
                className="form-control"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                required
              />
            </div>
            <button type="submit" className="btn btn-primary">
              Upload product
            </button>
          </form>
    <h2> Product List</h2>
    <div className="form-group mb-4">
            <label>Filter by Category</label>
            <select
              className="form-control"
              value={filterCategory}
              onChange={handleFilterChange}
            >
              <option value="">All Categories</option>
              {Array.from(new Set(products.map((product) => product.category))).map(
                (category) => (
                  <option key={category} value={category}>
                    {category}
                  </option>
                )
              )}
            </select>
          </div>

    <table className="table table-bordered">
            <thead className="thead-light">
              <tr>
                <th>ID</th>
                <th>Shoe</th>
                <th>Description</th>
                <th>Category</th>
                <th>Image Preview</th>
                <th>Price</th>
              </tr>
            </thead>
            <tbody>
                      {filteredProducts.map((product) => (
                        <tr key={product.id}>
                          <td>{product.id}</td>
                          <td>{product.name}</td>
                          <td>{product.description}</td>
                          <td>{product.category}</td>
                          <td>
                            <img
                              src={`http://localhost:8089/products/images/${product.image}`}
                              alt={product.name}
                              width="100"
                              height="100"
                            />
                          </td>
                          <td>${product.price}</td> {/* Changed currency symbol to $ */}
                        </tr>
                      ))}
                    </tbody>
          </table>
    </div>


  );
}


export default App;

