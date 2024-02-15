package com.caiohbs.restful.cinevault.movie;

import com.caiohbs.restful.cinevault.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * This endpoint is responsible for providing a list with all the movies on
     * the db.
     *
     * @return A list with all the movies in the db.
     */
    @GetMapping("/list-all")
    public List<Movie> getAllMoviesEndpoint() {
        return movieRepository.findAll();
    }

    /**
     * This endpoint returns a random movie from the database.
     *
     * @return A random, valid movie from the database.
     */
    @GetMapping("/get-random")
    public Movie getRandomMovieEndpoint() {
        long movieCount = movieRepository.count();

        Random random = new Random();
        while (true) {
            int randomId = random.nextInt((int) (movieCount - 1 + 1)) + 1;
            Optional<Movie> randMovie = movieRepository.findById(randomId);

            if (randMovie.isPresent()) {
                return randMovie.get();
            }
        }
    }

    /**
     * This endpoint is responsible for querying the database for a single object
     * based on its ID.
     *
     * @param id PathVariable with a valid id corresponding to an object on the
     *           database.
     * @return A single movie or a 404 response.
     */
    @GetMapping("/movie/{id}")
    public Movie singleMovieEndpoint(@PathVariable int id) {
        Optional<Movie> foundMovie = movieRepository.findById(id);

        if (foundMovie.isEmpty()) {
            throw new ResourceNotFoundException("No movies found with the given id: " + id);
        }

        return foundMovie.get();
    }

    /**
     * This endpoint is used to delete a movie based on its ID.
     *
     * @param id A valid ID for a movie in the database.
     */
    @DeleteMapping("/movie/{id}")
    public void deleteMovieEndpoint(@PathVariable int id) {
        Optional<Movie> foundMovie = movieRepository.findById(id);

        if (foundMovie.isEmpty()) {
            throw new ResourceNotFoundException("No movies found with the given id: " + id);
        }

        movieRepository.deleteById(id);
    }

    /**
     * This endpoint returns the result of a query on the movie titles.
     *
     * @param title A String that may be part of a title.
     * @return A list with movies whose titles contain the chosen string.
     */
    @GetMapping("/movie/search-title")
    public List<Movie> getMoviesByTitleEndpoint(@RequestParam String title) {
        List<Movie> foundMovies = movieRepository.findByEnglishTitleContaining(title);

        if (foundMovies.isEmpty()) {
            throw new ResourceNotFoundException("No movies found with the given title: " + title);
        }

        return foundMovies;
    }

    /**
     * This endpoint allows the user to add new movies to the database if the
     * payload is valid.
     *
     * @param movie The validated data for the movie being added.
     */
    @PostMapping("/movie")
    public ResponseEntity<Object> saveNewMovieEndpoint(@Valid @RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMovie.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
