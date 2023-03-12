package hu.nye.webapp.movies.service.impl;

import hu.nye.webapp.movies.dto.MovieDTO;
import hu.nye.webapp.movies.entity.MovieEntity;
import hu.nye.webapp.movies.repository.MovieRepository;
import hu.nye.webapp.movies.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    private ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle("Star Wars");
        movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieDTO> findAll() {
        List<MovieEntity> movies = movieRepository.findAll();
        List<MovieDTO> result = new ArrayList<>();
        for (MovieEntity movieEntity : movies) {
            MovieDTO movieDTO = modelMapper.map(movieEntity, MovieDTO.class);
            result.add(movieDTO);
        }
        return result;
    }

    @Override
    public Optional<MovieDTO> findById(Long id) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);

        Optional<MovieDTO> movieDTO = movieEntityOptional.map(movieEntity -> modelMapper.map(movieEntity, MovieDTO.class));
        return movieDTO;
    }

    @Override
    public MovieDTO save(MovieDTO movieDTO) {
        MovieEntity movieEntity = modelMapper.map(movieDTO, MovieEntity.class);
        movieEntity.setId(null);

        MovieEntity savedMovie = movieRepository.save(movieEntity);
        return modelMapper.map(savedMovie, MovieDTO.class);
    }
}
