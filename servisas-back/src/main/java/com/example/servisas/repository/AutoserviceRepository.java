package com.example.servisas.repository;

import com.example.servisas.model.Autoservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoserviceRepository extends JpaRepository<Autoservice, Long> {
}
