package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
//!!!!! DE FACUT METODE PENTRU REQUESTURI
///!!! METODE PENTRU RATING
public class Regular<T extends Comparable<T> > extends User<T> implements RequestManager{

    List<Request> requestList = null;
    @Override
    public void createRequest(Request r) {
        if( requestList == null)
            requestList = new ArrayList<>();
        requestList.add(r);
    }

    @Override
    public void removeRequest(Request r) {
        requestList.remove(r);
        if(requestList.isEmpty())
            requestList = null;

    }
    public void addRating(String ratingProductionTitle){
        IMDB imdb = IMDB.getInstance();
        List<Production> productions = imdb.getProductions();
        int check = 0;
        for (Production production : productions){

            if (production.getTitle().equals(ratingProductionTitle)){
                List<Rating> productionRatings = production.getRatings();

                for ( Rating rating : productionRatings) {
                    String option1;
                    Scanner scan = new Scanner(System.in);

                    if (rating.getUsername().equals(getUsername())) {
                        check = 1;
                        do {
                            System.out.println("You have already gave a rating. Do you want to change it Y/N ?");
                            System.out.print("Enter choice: ");
                            option1 = scan.nextLine();
                        } while (!(option1.equals("Y")) && !(option1.equals("N")));

                        if (option1.equals("Y")) {
                            int grade;
                            do {
                                System.out.print("New production grade: ");
                                grade = scan.nextInt();

                                if (grade < 1 || grade > 10) {
                                    System.out.println("Invalid grade! Try again. ");
                                }
                            } while (grade < 1 || grade > 10);

                            scan.nextLine();
                            rating.setGrade(grade);
                            String option2;
                            do {
                                System.out.println("New comment ? Y/N");
                                System.out.print("Enter choice: ");
                                option2 = scan.nextLine();
                            } while (!(option2.equals("Y")) && !(option2.equals("N")));

                            if (option2.equals("Y")) {
                                System.out.print("Enter new comment: ");
                                String newComment = scan.nextLine();
                                rating.setComents(newComment);
                            }
                        }
                        return;
                    }
                }   Scanner scan = new Scanner(System.in);
                    if (check == 0) {
                       int grade;
                        do{
                            System.out.print("Production grade: ");
                            grade = scan.nextInt();

                            if( grade < 1 || grade > 10 ) {
                                System.out.println("Invalid grade! Try again. ");
                            }
                            }
                            while(( grade < 1 || grade > 10 ));

                            scan.nextLine();

                            System.out.print("Enter comment:");
                            String newComment  = scan.nextLine();
                            Rating newRating = new Rating(getUsername(), grade, newComment);
                            productionRatings.add(newRating);
                            return;
                    }

            }
        }
    }

    public List<Request> getRequestList() {
        return requestList;
    }
}