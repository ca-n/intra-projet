package com.example.intra.service;

import com.example.intra.model.Guess;
import com.example.intra.repository.GuessRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GuessServiceTests {

    @Mock
    private GuessRepository repository;

    @InjectMocks
    private GuessService service;

    @Test
    void testAddGuess() {
        //Arrange
        Guess expected = new Guess();
        expected.setId(1);
        when(repository.save(any())).thenReturn(expected);

        //Act
        Optional<Guess> actual = service.addGuess(new Guess());

        //Assert
        assertThat(actual).isPresent();
        assertThat(actual).isEqualTo(Optional.of(expected));
    }

    @Test
    void testGetGuesses() {
        //Arrange
        List<Guess> expected = List.of(new Guess(), new Guess(), new Guess());
        when(repository.findAll()).thenReturn(expected);

        //Act
        List<Guess> actual = service.getGuesses();

        //Assert
        assertThat(actual.size()).isEqualTo(3);
    }
}
