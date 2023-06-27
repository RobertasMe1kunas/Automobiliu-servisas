import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditAutoservice() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [autoservice, setAutoservice] = useState({
    companyName: "",
    address: "",
    manager: "",
  });

  const { companyName, address, manager } = autoservice;

  const onInputChange = (e) => {
    setAutoservice({ ...autoservice, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadAutoservice();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/autoservices/${id}`, autoservice);
    navigate("/");
  };

  const loadAutoservice = async () => {
    const result = await axios.get(`http://localhost:8080/autoservices/${id}`);
    setAutoservice(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Autoservice</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="companyName" className="form-label">
                Company Name
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter company name"
                name="companyName"
                value={companyName}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="address" className="form-label">
                Address
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter address"
                name="address"
                value={address}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="manager" className="form-label">
                Manager
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter manager"
                name="manager"
                value={manager}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
