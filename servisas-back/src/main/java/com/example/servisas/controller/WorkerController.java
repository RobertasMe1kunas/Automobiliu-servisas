package com.example.servisas.controller;

import com.example.servisas.model.Worker;
import com.example.servisas.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerController(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    // Get all workers
    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    // Get a worker by ID
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Long id) {
        Optional<Worker> worker = workerRepository.findById(id);
        return worker.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add a new worker
    @PostMapping
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker) {
        Worker createdWorker = workerRepository.save(worker);
        return new ResponseEntity<>(createdWorker, HttpStatus.CREATED);
    }

    // Update a worker
    @PutMapping("/{id}")
    public ResponseEntity<Worker> updateWorker(@PathVariable Long id, @RequestBody Worker worker) {
        Optional<Worker> existingWorkerOptional = workerRepository.findById(id);
        if (existingWorkerOptional.isPresent()) {
            Worker existingWorker = existingWorkerOptional.get();
            existingWorker.setName(worker.getName());
            existingWorker.setLastName(worker.getLastName());
            existingWorker.setSpecialization(worker.getSpecialization());
            existingWorker.setCity(worker.getCity());

            Worker updatedWorker = workerRepository.save(existingWorker);
            return new ResponseEntity<>(updatedWorker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a worker
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long id) {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (workerOptional.isPresent()) {
            workerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
