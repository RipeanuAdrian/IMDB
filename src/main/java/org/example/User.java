package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

// !!!!!!   DE FACUT METODA DE ACTUALIZARE A EXPERIENTEI
//!!!!!!!   DE FACUT METODA DE DELOGARE
abstract public class User <T extends Comparable<T> > {
    //T poate fi doar  obiecte de tip Actor, Movie, Series
    public Information info;
    public AccountType typeOfUser;
    String username;
    Integer experience;
    List<String> notifications;
    //favorites productions and actors
    SortedSet<T> favorites;

    public User() {
        this.favorites =  new TreeSet<>(new SortedComparator());
    }

    public User(Information info, AccountType typeOfUser, String username,
                Integer experience, List<String> notifications) {
        this.info = info;
        this.typeOfUser = typeOfUser;
        this.username = username;
        this.experience = experience;
        this.notifications = notifications;
        this.favorites =  new TreeSet<>(new SortedComparator());


    }

    public User<T> setInfo(Information info) {
        this.info = info;
        return this;
    }

    public User<T> setTypeOfUser(AccountType typeOfUser) {
        this.typeOfUser = typeOfUser;
        return this;
    }

    public User<T> setUsername(String username) {
        this.username = username;
        return this;
    }

    public User<T> setExperience(Integer experience) {
        this.experience = experience;
        return this;
    }

    public User<T> setNotifications(List<String> notifications) {
        this.notifications = notifications;
        return this;
    }

    public User<T> setFavorites(SortedSet<T> favorites) {
        this.favorites = favorites;
        return this;
    }

    public void addNewFavotite(T obj){
        favorites.add(obj);
    }

    public static class Information {
        private Credentials credentials;
        String name;
        String country;
        char gen;
        private LocalDateTime dateOfBirth;
        int age;
        Information(){

        }
        Information(BuilderInformation builder) {
            this.credentials = builder.credentials;
            this.name = builder.name;
            this.country = builder.country;
            this.gen = builder.gen;
            this.dateOfBirth = builder.dateOfBirth;
            this.age = builder.age;
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public LocalDateTime getDateOfBirth() {
            return dateOfBirth;
        }

        public static class BuilderInformation {
            private Credentials credentials;
            String name;
            String country;
            char gen;
            private LocalDateTime dateOfBirth;
            int age;
            BuilderInformation(){

            }
            public static BuilderInformation newInstance(){
                return new BuilderInformation();
            }

            public BuilderInformation setCredentials(Credentials credentials) {
                this.credentials = credentials;
                return this;

            }
            public BuilderInformation setName(String name){
                this.name = name;
                return this;
            }

            public BuilderInformation setGen(char gen) {
                this.gen = gen;
                return this;
            }

            public BuilderInformation setDateOfBirth(LocalDateTime dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
                return this;
            }

            public BuilderInformation setCountry(String country){
                this.country = country;
                return this;
            }

            public BuilderInformation setAge(int age) {
                this.age = age;
                return this;
            }

            public Information build() {
                return new Information(this);
            }
        }


    }
    public void  addNewFavorite(T obj) {
        favorites.add(obj);
    }
    public void addFavorites(T favorite){
        if( favorite != null)
            this.favorites.add(favorite);
    }
    public void removeFavorites(T unfavorite){
        if( unfavorite != null)
            this.favorites.remove(unfavorite);
    }

    // Add this method to your User class
    public void printUserInfo() {
        System.out.println("Username: " + this.username);
        System.out.println("Experience: " + this.experience);
        System.out.println("Information:");
        System.out.println("  Name: " + this.info.name);
        System.out.println("  Country: " + this.info.country);
        System.out.println("  Gender: " + this.info.gen);
        System.out.println("  Date of Birth: " + this.info.dateOfBirth);
        System.out.println("  Age: " + this.info.age);
        System.out.println("  Email: " + this.info.credentials.email);
        System.out.println("  Password: " + this.info.credentials.getPassword());
        System.out.println("User Type: " + this.typeOfUser);
//        System.out.println("Contribution: " + this.productionsContribution);
        //System.out.println("Favorite: " + this.favorites);
        for (Object favorite : favorites){
            if(favorite instanceof Production){
                System.out.println(((Production) favorite).getTitle());
            }
            else if (favorite instanceof Actor){
                System.out.println(((Actor) favorite).getName());
            }
        }
        System.out.println();
    }

    public void displayFavorites(){

        for ( T item : favorites ){
            if (item instanceof Production itemProduction){
                System.out.println(itemProduction.getTitle());
            }
            else if (item instanceof Actor itemActor){
                System.out.println(itemActor.getName());
            }
        }
    }

    public String getUsername() {
        return username;
    }
}
