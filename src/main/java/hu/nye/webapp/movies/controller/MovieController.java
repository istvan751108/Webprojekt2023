package hu.nye.webapp.movies.controller;

import hu.nye.webapp.movies.dto.MovieDTO;
import hu.nye.webapp.movies.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<MovieDTO> findAll() {
        List<MovieDTO> movies = movieService.findAll();
        return movies;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        Optional<MovieDTO> movieDTO = movieService.findById(id);

        ResponseEntity<MovieDTO> response;

        if (movieDTO.isPresent()) {
            response = ResponseEntity.ok(movieDTO.get());
            //response = ResponseEntity.status(200).body(movieDTO.get());
        } else {
            //response = ResponseEntity.status(404).build();
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO) {
        MovieDTO savedMovie = movieService.save(movieDTO);

        return ResponseEntity.status(201).body(savedMovie);
    }
}
