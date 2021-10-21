package com.example.intra.service;

import com.example.intra.model.Guess;
import com.example.intra.repository.GuessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuessService {
    private final GuessRepository repository;

    GuessService(GuessRepository repository) {
        this.repository = repository;
    }

    public Optional<Guess> addGuess(Guess guess) {
        return Optional.of(repository.save(guess));
    }

    public List<Guess> getGuesses() {
        return repository.findAll();
    }
}
