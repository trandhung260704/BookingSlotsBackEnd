package com.example.demo.controller;

import com.example.demo.entity.Pitches;
import com.example.demo.repository.PitchesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pitches")
@CrossOrigin(origins = "http://localhost:3002", allowCredentials = "true")
@RequiredArgsConstructor
public class PitchesController {

    private final PitchesRepository pitchesRepository;

    @GetMapping
    public ResponseEntity<List<Pitches>> getAllPitches() {
        return ResponseEntity.ok(pitchesRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pitches> getPitchById(@PathVariable Integer id) {
        Optional<Pitches> pitch = pitchesRepository.findById(id);
        return pitch.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pitches> createPitch(@RequestBody Pitches pitches) {
        return ResponseEntity.ok(pitchesRepository.save(pitches));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pitches> updatePitch(@PathVariable Integer id, @RequestBody Pitches newPitch) {
        Optional<Pitches> optionalPitch = pitchesRepository.findById(id);
        if (optionalPitch.isPresent()) {
            Pitches existingPitch = optionalPitch.get();
            existingPitch.setName(newPitch.getName());
            existingPitch.setType(newPitch.getType());
            existingPitch.setStatus(newPitch.getStatus());
            return ResponseEntity.ok(pitchesRepository.save(existingPitch));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePitch(@PathVariable Integer id) {
        if (pitchesRepository.existsById(id)) {
            pitchesRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
