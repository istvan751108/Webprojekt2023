package hu.nye.webapp.movies.service;

import hu.nye.webapp.movies.dto.MovieDTO;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDTO> findAll();

    Optional<MovieDTO> findById(Long id);

    MovieDTO save(MovieDTO movieDTO);
}
