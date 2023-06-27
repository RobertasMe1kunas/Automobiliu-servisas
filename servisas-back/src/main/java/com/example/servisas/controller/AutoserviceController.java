package com.example.servisas.controller;

import com.example.servisas.model.Autoservice;
import com.example.servisas.repository.AutoserviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3001")
@RequestMapping("/autoservices")
public class AutoserviceController {

    private final AutoserviceRepository autoserviceRepository;

    @Autowired
    public AutoserviceController(AutoserviceRepository autoserviceRepository) {
        this.autoserviceRepository = autoserviceRepository;
    }

    @PostMapping
    public ResponseEntity<Autoservice> addAutoservice(@RequestBody Autoservice autoservice) {
        Autoservice createdAutoservice = autoserviceRepository.save(autoservice);
        return new ResponseEntity<>(createdAutoservice, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutoservice(@PathVariable Long id) {
        autoserviceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autoservice> updateAutoservice(@PathVariable Long id, @RequestBody Autoservice updatedAutoservice) {
        Optional<Autoservice> optionalAutoservice = autoserviceRepository.findById(id);
        if (optionalAutoservice.isPresent()) {
            Autoservice existingAutoservice = optionalAutoservice.get();
            existingAutoservice.setCompanyName(updatedAutoservice.getCompanyName());
            existingAutoservice.setAddress(updatedAutoservice.getAddress());
            existingAutoservice.setManager(updatedAutoservice.getManager());
            Autoservice savedAutoservice = autoserviceRepository.save(existingAutoservice);
            return new ResponseEntity<>(savedAutoservice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Autoservice>> getAllAutoservices() {
        List<Autoservice> autoservices = autoserviceRepository.findAll();
        return new ResponseEntity<>(autoservices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autoservice> getAutoserviceById(@PathVariable Long id) {
        Optional<Autoservice> optionalAutoservice = autoserviceRepository.findById(id);
        if (optionalAutoservice.isPresent()) {
            Autoservice autoservice = optionalAutoservice.get();
            return new ResponseEntity<>(autoservice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
