import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewAutoservice() {
  const [autoservice, setAutoservice] = useState({
    companyName: "",
    address: "",
    manager: "",
  });

  const { id } = useParams();

  useEffect(() => {
    loadAutoservice();
  }, []);

  const loadAutoservice = async () => {
    const result = await axios.get(`http://localhost:8080/autoservices/${id}`);
    setAutoservice(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Autoservice Details</h2>

          <div className="card">
            <div className="card-header">
              Details of autoservice id : {id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Company Name:</b> {autoservice.companyName}
                </li>
                <li className="list-group-item">
                  <b>Address:</b> {autoservice.address}
                </li>
                <li className="list-group-item">
                  <b>Manager:</b> {autoservice.manager}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}
