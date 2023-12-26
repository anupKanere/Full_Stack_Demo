import { toast } from "react-toastify";
import React from "react";
import { useState } from "react";
import LoginServiceCustomer from "../../Service/LoginServiceCustomer";

function AddBookForm() {

    const [title, setTitle] = useState("");
    const [markedPrice, setMarkedPrice] = useState("");
    const [sellingPrice, setSellingPrice] = useState("");
    const [stock, setStock] = useState('');
    const [description, setDescription] = useState("");
    const [publicationDate, setPublicationDate] = useState("");
    const [author, setAuthor] = useState("");
    const [edition, setEdition] = useState("");
    const [genre, setGenre] = useState("");
    const [languageNames, setLanguageNames] = useState([]);



    const handleAddProduct = (event) => {
        event.preventDefault();
        LoginServiceCustomer.addProduct(title, markedPrice, sellingPrice, stock, edition, genre, publicationDate, description, author, languageNames)
            .then((result) => {

                toast.success('Product added Sucessfully!');
                console.log(result);
            }).catch((err) => {
                toast.error('Internal Server error');
            });

    }

    return (
        <div className=" shadow p-2   admin-page" style={{ color: "white", backgroundColor: "#28282B", width: "100vw" }}>
            <h1 className="text-center m-5 pb-3" >Add A Book</h1>

            <div className="row align-items-center justify-content-evenly">
                <div className="col-6 align-self-center">
                    <form onSubmit={handleAddProduct} style={{ color: "white" }}>

                        <span className="row g-3 mb-3">
                            <div className="col-md-6">
                                <label className="form-label">Book Title</label>
                                <div>
                                    <input className="form-control" type="text" placeholder="Book Title" value={title} onChange={(event) => setTitle(event.target.value)} required></input>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <label className="form-label">Marked Price</label>
                                <div>
                                    <input className="form-control" type="text" placeholder="Marked Price" value={markedPrice} onChange={(event) => setMarkedPrice(event.target.value)} required></input>
                                </div>
                            </div>
                        </span>
                        <span className="row g-3 mb-3">
                            <div className="col-md-6">
                                <label className="form-label">Selling Price</label>
                                <div>
                                    <input className="form-control" type="text" placeholder="Selling Price" value={sellingPrice} onChange={(event) => setSellingPrice(event.target.value)} required></input>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <label className="form-label">Quantity</label>
                                <div>
                                    <input className="form-control" type="number" placeholder="Quantity" value={stock} onChange={(event) => setStock(event.target.value)} required></input>
                                </div>
                            </div>
                        </span>
                        <span className="row g-3 mb-3">
                            <div className="col-md-6">
                                <label className="form-label">Edition</label>
                                <div>
                                    <input className="form-control" type="number" placeholder="Edition" value={edition} onChange={(event) => setEdition(event.target.value)} required></input>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <label className="form-label">Genre</label>
                                <div>
                                    <select className="form-control" id="genre" placeholder="Genre" value={genre} onChange={(event) => setGenre(event.target.value)} required>
                                        <option>---Select a Genre---</option>
                                        <option>FICTION</option>
                                        <option>BIOGRAPHY</option>
                                        <option>HISTORY</option>
                                        <option>HORROR</option>
                                        <option>POETRY</option>
                                        <option>COMIC</option>
                                        <option>ROMANCE</option>
                                        <option>THRILLER</option>
                                        <option>DRAMA</option>
                                        <option>MYTHOLOGICAL</option>
                                    </select>

                                </div>
                            </div>
                        </span>
                        <span className="row g-3 mb-3">

                            <div className="col-md-6">
                                <label className="form-label">Publication Date</label>
                                <div>
                                    <input className="form-control" type="date" value={publicationDate} onChange={(event) => setPublicationDate(event.target.value)} required></input>

                                </div>
                            </div>
                            <div className="col-md-6">
                                <label className="form-label">Description</label>
                                <div>
                                    <textarea className="form-control" placeholder="Description" value={description} onChange={(event) => setDescription(event.target.value)}></textarea>

                                </div>
                            </div>
                        </span>
                        <span className="row  g-3 mb-3">
                            <div className="col-md-6">
                                <label className="form-label">Author</label>
                                <div>
                                    <input className="form-control" type="text" placeholder="Author" value={author} onChange={(event) => setAuthor(event.target.value)} required></input>
                                </div>
                            </div>
                            <div className="col-md-6">
                                <label className="form-label">Languages</label>
                                <input className="form-control" type="text" placeholder="Languages" value={languageNames} onChange={(event) => setLanguageNames(event.target.value)} required></input>
                            </div>
                            <div className="row g-3 mt-2">
                                <button className="btn btn-primary rounded">Add Book</button>
                            </div>
                        </span>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default AddBookForm;
