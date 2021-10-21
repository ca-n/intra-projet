package com.example.intra.repository;

import com.example.intra.model.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuessRepository extends JpaRepository<Guess, Long> {
}
