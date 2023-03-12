package hu.nye.webapp.movies.repository;

import hu.nye.webapp.movies.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
