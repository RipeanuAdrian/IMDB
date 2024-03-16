package org.example;

public class Rating {
    String username;
    private int grade;//trebuie sa fie intre 1 si 10 trebuie sa fac cu try catch
    String Coments;

    public Rating() {
    }

    public Rating(String username, int grade, String coments) {
        this.username = username;
        this.grade = grade;
        Coments = coments;
    }

    public Rating setUsername(String username) {
        this.username = username;
        return this;
    }

    public Rating setGrade(int grade) {
        this.grade = grade;
        return this;
    }

    public Rating setComents(String coments) {
        Coments = coments;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public int getGrade() {
        return grade;
    }

    public String getComents() {
        return Coments;
    }
    public void displayRating(){
        System.out.println("Username: " + username);
        System.out.println("Production grade: " + grade);
        System.out.println("Comment: " + Coments);
    }
}
