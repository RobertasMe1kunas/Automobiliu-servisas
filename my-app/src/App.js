import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import logo from "./logo.svg";
import "./App.css";
import Home from "./pages/Home";
import AddAutoservice from "./pages/AddAutoservice";
import EditAutoservice from "./pages/EditAutoservice";
import ViewAutoservice from "./pages/ViewAutoservice";
import Navbar from "./components/Navbar";

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <div className="container">
          <Routes>
            <Route exact path="/" component={Home} />
            <Route exact path="/add" component={AddAutoservice} />
            <Route exact path="/edit/:id" component={EditAutoservice} />
            <Route exact path="/view/:id" component={ViewAutoservice} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
