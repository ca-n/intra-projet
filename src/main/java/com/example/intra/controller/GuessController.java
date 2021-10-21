package com.example.intra.controller;

import com.example.intra.model.Guess;
import com.example.intra.service.GuessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GuessController {

    private final GuessService service;

    GuessController(GuessService service) {
        this.service = service;
    }

    @PostMapping("/guess")
    public ResponseEntity<?> addGuess(@RequestBody Guess guess) {
        Optional<Guess> created = service.addGuess(guess);
        if (created.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(created.get());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to save guess");
    }

    @GetMapping("/guess")
    public ResponseEntity<List<Guess>> getGuesses() {
        List<Guess> guessList = service.getGuesses();
        return ResponseEntity.ok(guessList);
    }
}
