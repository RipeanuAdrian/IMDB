package org.example;

import java.util.List;
import java.util.Map;

public class Series extends Production {
    private Integer releaseYear;
    private Integer seasonsNumber;
    private Map<String, List<Episode>> seasonEpisodes;

    public Series() {
    }

    public Series(String title, List<String> directors, List<String> actors, List<Genre> genres, List<Rating> ratings,
                  String subject, Double grading, Integer releaseYear, Integer seasonsNumber, Map<String,
            List<Episode>> seasonEpisodes) {
        super(title, directors, actors, genres, ratings, subject, grading);
        this.releaseYear = releaseYear;
        this.seasonsNumber = seasonsNumber;
        this.seasonEpisodes = seasonEpisodes;
    }

    public Series setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public Integer getSeasonsNumber() {
        return seasonsNumber;
    }

    public Series setSeasonsNumber(Integer seasonsNumber) {
        this.seasonsNumber = seasonsNumber;
        return this;
    }

    public Series setSeasonEpisodes(Map<String, List<Episode>> seasonEpisodes) {
        this.seasonEpisodes = seasonEpisodes;
        return this;
    }

    @Override
    public void displayInfo() {
        if (this.title != null)
            System.out.print("Title: " + this.title + "\n");

        if (this.directors != null) {
            System.out.println("Directors: ");
            for (String director : this.directors) {
                System.out.println(director);
            }
        }

        if (this.actors != null) {
            System.out.println("Actors: ");
            for (String actor : this.actors) {
                System.out.println(actor);
            }
        }

        if (this.genres != null) {
            System.out.println("Genres: ");
            for (Genre genre : this.genres) {
                System.out.println(genre.toString());
            }
        }

        if (this.ratings != null) {
            System.out.println("Ratings: ");
            for (Rating rating : this.ratings) {
                System.out.println(rating.toString());
            }
        }

        if (this.subject != null)
            System.out.println("Subject: " + this.subject);

        if (this.grading != null)
            System.out.println("Grading: " + this.grading);
        if (this.releaseYear != null)
            System.out.println("Release year: " + this.releaseYear);

        if (this.seasonsNumber != null)
            System.out.println("Number of seasones: " + this.seasonsNumber);

        if (this.seasonEpisodes != null){
            System.out.println("Season Episodes:");

            for (Map.Entry<String, List<Episode>> entry : seasonEpisodes.entrySet()){
                String seasonTitle = entry.getKey();
                List<Episode> episodes = entry.getValue();

                System.out.println("Season: " + seasonTitle);

                for (Episode episode : episodes) {
                    System.out.println("Episode Name: " + episode.name);
                    System.out.println("Episode Time: " + episode.time);
                }
            }

        }

    }
    public static Series searchSeries(String title) {
        IMDB imdb = IMDB.getInstance();
        List<Production> productions = imdb.getProductions();

        for (Production production : productions) {
            if (production instanceof Series seriesProduction) {
                if (seriesProduction.getTitle().equals(title))
                    return seriesProduction;
            }
        }
        return null;
    }

    public Map<String, List<Episode>> getSeasonEpisodes() {
        return seasonEpisodes;
    }
}
