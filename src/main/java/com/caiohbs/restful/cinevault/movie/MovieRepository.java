package com.caiohbs.restful.cinevault.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByOriginalTitle(String title);

    List<Movie> findByEnglishTitleContaining(String title);

}
