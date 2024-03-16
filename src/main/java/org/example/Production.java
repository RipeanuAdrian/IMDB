package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Production implements Comparable<Production> {
    String title;
    List<String> directors;
    List<String> actors;
    List<Genre> genres;
    List<Rating> ratings;
    String subject;
    Double grading;

    public Production(String title, List<String> directors, List<String> actors, List<Genre> genres,
                      List<Rating> ratings, String subject, Double grading) {
        this.title = title;
        this.directors = directors;
        this.actors = actors;
        this.genres = genres;
        this.ratings = ratings;
        this.subject = subject;
        this.grading = grading;
    }

    public Production() {

    }

    public Production setTitle(String title) {
        this.title = title;
        return this;
    }

    public Production setDirectors(List<String> directors) {
        this.directors = directors;
        return this;
    }

    public Production setActors(List<String> actors) {
        this.actors = actors;
        return this;
    }

    public Production setGenres(List<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public Production setRatings(List<Rating> ratings) {
        this.ratings = ratings;
        return this;
    }

    public Production setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public Production setGrading(Double grading) {
        this.grading = grading;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public abstract void displayInfo ();

    public List<Rating> getRatings() {

        return ratings;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public int compareTo(Production o) {
        return this.title.compareTo(o.title);
    }
    public static Production searchProductionByName(List<Production> productions, String name) {
        for (Production production : productions) {
            if (production.getTitle().equals(name)) {
                return production;
            }
        }
        return null;
    }
    public static List<Production> FilteredProductionsOnGenre(List<Production> productions, Genre genre){
        return productions.stream()
                .filter(production -> production.getGenres().contains(genre))
                .collect(Collectors.toList());
    }
    public static List<Production> FilteredProductionsOnNumberOfReviws(List<Production> productions, int reviewsNumber){
        return productions.stream()
                .filter(production -> production.getRatings().size() == reviewsNumber)
                .collect(Collectors.toList());
    }
    public static Production searchProduction(String nameOrTitle) {
        IMDB imdb = IMDB.getInstance();
        List<Production> productions = imdb.getProductions();

        for (Production production : productions) {
            if (production.getTitle().equals(nameOrTitle))
                return production;
        }
        return null;
    }
    public Rating searchUserRating(Regular user){
        for (Rating rating : ratings){
            if (rating.getUsername().equals(user.getUsername()))
                return rating;
        }
         return null;
    }
    public void displayRatingList(){
        for (Rating rating : ratings){
            rating.displayRating();
            System.out.println();
        }
    }
public void removeRating(Rating r){
        ratings.remove(r);
}
public void updateGrade(){
        double gradeProduction = 0;
        int numberOfGrades = 0;
        for (Rating rating : ratings){
            numberOfGrades++;
            gradeProduction = gradeProduction + rating.getGrade();
        }
        gradeProduction = gradeProduction/numberOfGrades;
        this.grading = gradeProduction;
}
}

