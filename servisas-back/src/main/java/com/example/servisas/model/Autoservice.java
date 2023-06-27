package com.example.servisas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autoservices")
public class Autoservice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "manager")
    private String manager;

    // Constructors, getters, and setters

    public Autoservice() {
    }

    public Autoservice(String companyName, String address, String manager) {
        this.companyName = companyName;
        this.address = address;
        this.manager = manager;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
