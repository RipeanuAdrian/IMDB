package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Movie extends Production{
    Integer time;
    Integer realeaseYear;

    public Movie() {
    }

    public Movie(String title, List<String> directors, List<String> actors, List<Genre> genres, List<Rating> ratings,
                 String subject, Double grading, Integer time, Integer realeaseYear) {
        super(title, directors, actors, genres, ratings, subject, grading);
        this.time = time;
        this.realeaseYear = realeaseYear;
    }

    public Movie(String title, List<String> directors, List<String> actors, List<Genre> genres, List<Rating> ratings, String subject, Double grading) {
        super(title, directors, actors, genres, ratings, subject, grading);
    }
//    public Movie(){
//
//    }
//    public Movie(String title, List<String> directors, List<String> actors, List<Genre> genres, List<Rating> ratings,
//                 String subject, Double grading, Double time, Integer realeaseYear) {
//        this.title = title;
//        this.directors = directors;
//        this.actors = actors;
//        this.genres = genres;
//        this.ratings = ratings;
//        this.subject = subject;
//        this.grading = grading;
//        this.time = time;
//        this.realeaseYear = realeaseYear;
//    }

    public Movie setTime(Integer time) {
        this.time = time;
        return this;
    }

    public Movie setRealeaseYear(Integer realeaseYear) {
        this.realeaseYear = realeaseYear;
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
                System.out.println("Username: " + rating.getUsername());
                System.out.println("Grade: " + rating.getGrade());
                System.out.println("Comments: " + rating.getComents());
            }
        }

        if (this.subject != null)
            System.out.println("Subject: " + this.subject);

        if (this.grading != null)
            System.out.println("Grading: " + this.grading);

        if (this.time != null)
            System.out.println("Time: " + this.time);

        if (this.realeaseYear != null)
            System.out.println("Release Year: " + this.realeaseYear);
    }
    public static Movie searchMovie(String title) {
        IMDB imdb = IMDB.getInstance();
        List<Production> productions = imdb.getProductions();

        for (Production production : productions) {
            if (production instanceof Movie movieProduction) {
                if (movieProduction.getTitle().equals(title))
                    return movieProduction;
            }
        }
        return null;
    }


}
