package com.caiohbs.restful.cinevault.movie;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
     * Request types accepted: GET.
     * @return A list with all the movies in the db.
     */
    @GetMapping("/list-all")
    public List<Movie> getAllMoviesEndpoint() {
        return movieRepository.findAll();
    }

    @GetMapping("/get-random")
    public Movie getRandomMovieEndpoint() {
        long movieCount = movieRepository.count();

        Random random = new Random();
        int randomId = random.nextInt((int) (movieCount - 1 + 1)) + 1;

        Optional<Movie> randMovie = movieRepository.findById(randomId);
        return randMovie.orElse(null);
    }

    @GetMapping("/movie/{id}")
    public Movie singleMovieEndpoint(@PathVariable int id) {
        Optional<Movie> foundMovie = movieRepository.findById(id);

        // TODO: Make it throw a 404.
        return foundMovie.orElse(null);
    }

    @GetMapping("/movie/search-title")
    public Movie getMoviesByTitleEndpoint(@RequestParam String title) {
        // TODO: optimize the code to make query wider.
        Optional<Movie> foundMovie = movieRepository.findByOriginalTitle(title);

        // TODO: Make it throw a 404.
        return foundMovie.orElse(null);
    }

    @PostMapping("/movie/add-new")
    public void saveNewMovieEndpoint(@Valid @RequestBody Movie movie) {
        // TODO: implement response entity for location link on response.
        movieRepository.save(movie);
    }

}
