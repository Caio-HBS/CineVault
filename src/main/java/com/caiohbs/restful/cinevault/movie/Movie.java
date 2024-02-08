package com.caiohbs.restful.cinevault.movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Size(min=1, max=30, message="'original_title' has to be between 1-30 characters")
    private String originalTitle;
    @NotNull
    @Size(min=1, max=30, message="'english_title' has to be between 1-30 characters")
    private String englishTitle;
    @NotNull
    @Size(min=1, max=700, message="'description' has to be between 1-700 characters")
    private String description;
    @NotNull
    @Size(min=1, max=3, message="'original_language' has to be between 1-3 characters")
    private String originalLanguage;
    @NotNull
    private Date releaseDate;
    @NotNull
    private String backdropPath;
    @NotNull
    private String posterPath;

    public Movie(
            Integer id, String originalTitle, String englishTitle,
            String description, String originalLanguage, Date releaseDate,
            String backdropPath, String posterPath
    ) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.englishTitle = englishTitle;
        this.description = description;
        this.originalLanguage = originalLanguage;
        this.releaseDate = releaseDate;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
    }

    public Movie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public String toString() {
        return "Movie{" +
               "id=" + id +
               ", originalTitle='" + originalTitle + '\'' +
               ", englishTitle='" + englishTitle + '\'' +
               ", description='" + description + '\'' +
               ", originalLanguage='" + originalLanguage + '\'' +
               ", releaseDate=" + releaseDate +
               ", backdropPath='" + backdropPath + '\'' +
               ", posterPath='" + posterPath + '\'' +
               '}';
    }
}
