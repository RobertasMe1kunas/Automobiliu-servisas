import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function Home() {
  const [autoservices, setAutoservices] = useState([]);

  const { id } = useParams();

  useEffect(() => {
    loadAutoservices();
  }, []);

  const loadAutoservices = async () => {
    const result = await axios.get("http://localhost:8080/autoservices");
    setAutoservices(result.data);
  };

  const deleteAutoservice = async (id) => {
    await axios.delete(`http://localhost:8080/autoservices/${id}`);
    loadAutoservices();
  };

  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">S.N</th>
              <th scope="col">Company Name</th>
              <th scope="col">Address</th>
              <th scope="col">Manager</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {autoservices.map((autoservice, index) => (
              <tr key={autoservice.id}>
                <th scope="row">{index + 1}</th>
                <td>{autoservice.companyName}</td>
                <td>{autoservice.address}</td>
                <td>{autoservice.manager}</td>
                <td>
                  <Link
                    className="btn btn-primary mx-2"
                    to={`/viewautoservice/${autoservice.id}`}
                  >
                    View
                  </Link>
                  <Link
                    className="btn btn-outline-primary mx-2"
                    to={`/editautoservice/${autoservice.id}`}
                  >
                    Edit
                  </Link>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => deleteAutoservice(autoservice.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
