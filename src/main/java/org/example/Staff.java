package org.example;

import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.io.BufferedReader;
import java.util.*;

abstract public class Staff<T extends Comparable<T>> extends User<T> implements StaffInterface {
    List<Request> requestList;
    SortedSet<T> newContriboutionActorsAndProduction;

    public Staff(Information info, AccountType typeOfUser, String username, int experience, List<String> notifications, SortedSet<T> favorites) {
        super(info, typeOfUser, username, experience, notifications);
    }

    public Staff(Information info, AccountType typeOfUser, String username, int experience, List<String> notifications,
                 SortedSet<T> favorites, List<Request> requestList) {
        super(info, typeOfUser, username, experience, notifications);
        this.requestList = requestList;
        this.newContriboutionActorsAndProduction = new TreeSet<>(new SortedComparator());
    }
    public void addNewContriboutionActorAndProudcction(T obj){
        newContriboutionActorsAndProduction.add(obj);
    }

    public Staff() {
        super();
        this.newContriboutionActorsAndProduction = new TreeSet<>(new SortedComparator());
    }


    @Override
    public void addProductionSystem(Production p) {
        IMDB imdb = IMDB.getInstance();
        ArrayList<Production> productions = imdb.getProductions();
        if (productions.contains(p)){
            System.out.println("This production is already present in the database");
            return;
        }
        addNewContriboutionActorAndProudcction((T)p);
        productions.add(p);
    }

    @Override
    public void addActorSystem(Actor a) {
        IMDB imdb = IMDB.getInstance();
        ArrayList<Actor> actors = imdb.getActors();
        if (actors.contains(a)){
            System.out.println("This actor is already present in the database");
            return;
        }
        addNewContriboutionActorAndProudcction((T)a);
        actors.add(a);
    }

    @Override
    public void removeProductionSystem(String name) {
        IMDB imdb = IMDB.getInstance();
        ArrayList<Production> productions = imdb.getProductions();
        for (Object contriboution : newContriboutionActorsAndProduction) {
            if (contriboution instanceof Production) {
                Production contriboutedProduction = (Production) contriboution;
                if (contriboutedProduction.getTitle().equals(name)) {
                    newContriboutionActorsAndProduction.remove(contriboutedProduction);
                    productions.remove(contriboutedProduction);
                    return;
                }
            }

        }
        System.out.println("Production doesn't exists or it wasn't on the comptenece of this user to remove it ");
    }

    @Override
    public void removeActorSystem(String name) {
        IMDB imdb = IMDB.getInstance();
        ArrayList<Actor> actors = imdb.getActors();

        for (Object contriboution : newContriboutionActorsAndProduction) {
            if (contriboution instanceof Actor) {
                Actor contriboutedActor = (Actor) contriboution;
                if (contriboutedActor.getName().equals(name)) {
                    newContriboutionActorsAndProduction.remove(contriboutedActor);
                    actors.remove(contriboutedActor);
                    return;
                }
            }
        }
        System.out.println("Actor doesn't exists or it wasn't on the comptenece of this user to remove it ");

    }

    @Override
    public void updateProduction(Production p) {
        IMDB imdb = IMDB.getInstance();
        ArrayList<Production> productions = imdb.getProductions();

        //newContriboutionActorsAndProduction.contains(p);
        int check = 0;
        for (Object contriboution : newContriboutionActorsAndProduction) {

            Scanner scan = new Scanner(System.in);
            if (contriboution instanceof Production && ((Production) contriboution).getTitle().equals(p.getTitle())) {
                check = 1;

                String option1 = null;
                String option2 = null;
                String option3 = null;
                Production contriboutedProduction;
                do {
                    contriboutedProduction = (Production) contriboution;
                    System.out.println("Do tou want to modify the title ?  Y/N");
                    System.out.print("Enter choice: ");
                    option1 = scan.nextLine();

                } while (!(option1.equals("Y")) && !(option1.equals("N")));

                if (option1.equals("Y")){
                    System.out.print("New title: ");
                    String newTitle = scan.nextLine();
                    contriboutedProduction.setTitle(newTitle);
                }
                do {
                    System.out.println("Do tou want to modify the subject ?  Y/N");
                    System.out.print("Enter choice: ");
                    option2 = scan.nextLine();
                }while (!(option2.equals("Y")) && !(option2.equals("N")));

                if (option2.equals("Y")){
                    System.out.print("New Subject: ");
                    String newSubject = scan.nextLine();
                    contriboutedProduction.setSubject(newSubject);
                }
                do {
                    System.out.println("Do you want to modify the List of directors ?  Y/N");
                    System.out.print("Enter choice: ");
                    option3 = scan.nextLine();
                }while (!(option3.equals("Y")) && !(option3.equals("N")));

                if (option3.equals("Y")){
                    String option4 = null;
                    do {
                        System.out.println("Remove directors ? Y/N");
                        System.out.print("Enter choice: ");
                        option4 = scan.nextLine();
                    }while (!(option4.equals("Y")) && !(option4.equals("N")));

                    while (option4.equals("Y")){
                        for ( String director : p.directors)
                            System.out.println(director);
                        System.out.print("Name of the director to be removed: ");
                        String removeThisDirector = scan.nextLine();
                        int x = 0;
                        for (String director : p.directors){

                            if(director.equals(removeThisDirector)){
                                p.directors.remove(director);
                                x = 1;
                                break;
                            }
                        }
                        if ( x == 0){
                            System.out.println("Director not found");
                        }
                        do {
                            System.out.println("Remove directors ? Y/N");
                            System.out.print("Enter choice: ");
                            option4 = scan.nextLine();
                        }while (!(option4.equals("Y")) && !(option4.equals("N")));
                    }
                    String option5;
                    do {
                        System.out.println("Add directors ? Y/N");
                        System.out.print("Enter choice: ");
                        option5 = scan.nextLine();
                    }while (!(option5.equals("Y")) && !(option5.equals("N")));

                    while (option5.equals("Y")){
                        System.out.print("Name of the director to be added: ");
                        String addThisDirector = scan.nextLine();
                        int x = 0;
                        for (String director : p.directors){

                            if (director.equals(addThisDirector)){
                                x = 1;
                            }
                        }
                        if ( x == 1){
                            System.out.println("Director already exists");
                        }
                        else {
                            p.directors.add(addThisDirector);
                        }
                        do {
                            System.out.println("Add directors ? Y/N");
                            System.out.print("Enter choice: ");
                            option5 = scan.nextLine();
                        }while (!(option5.equals("Y")) && !(option5.equals("N")));
                    }

                }
                do {
                    System.out.println("Do tou want to modify the List of actors ?  Y/N");
                    option3 = scan.nextLine();
                }while (!(option3.equals("Y")) && !(option3.equals("N")));

                if (option3.equals("Y")){
                    String option4 = null;
                    do {
                        System.out.println("Remove actors ? Y/N");
                        System.out.print("Enter choice: ");
                        option4 = scan.nextLine();
                    }while (!(option4.equals("Y")) && !(option4.equals("N")));

                    while (option4.equals("Y")){
                        System.out.print("Name of the actor to be removed: ");
                        String removeThisActor = scan.nextLine();
                        int x = 0;
                        for (String actor : p.actors){

                            if(actor.equals(removeThisActor)){
                                p.actors.remove(actor);
                                x = 1;
                                break;
                            }
                        }
                        if ( x == 0){
                            System.out.println("Actor not found");
                        }
                        do {
                            System.out.println("Remove actors? Y/N");
                            System.out.print("Enter choice: ");
                            option4 = scan.nextLine();
                        }while (!(option4.equals("Y")) && !(option4.equals("N")));
                    }
                    String option5 = null;
                    do {
                        System.out.println("Add actors ? Y/N");
                        System.out.print("Enter choice: ");
                        option5 = scan.nextLine();
                    }while (!(option5.equals("Y")) && !(option5.equals("N")));

                    while (option5.equals("Y")){
                        System.out.print("Name of the actors to be added: ");
                        String addThisActor = scan.nextLine();
                        int x = 0;
                        for (String actor : p.actors){

                            if(actor.equals(addThisActor)){
                                x = 1;
                                break;
                            }
                        }
                        if ( x == 1){
                            System.out.println("Actor already exists");
                        }
                        else {
                            p.actors.add(addThisActor);
                        }
                        do {
                            System.out.println("Add actors ? Y/N");
                            System.out.print("Enter choice: ");
                            option5 = scan.nextLine();
                        }while (!(option5.equals("Y")) && !(option5.equals("N")));
                    }

                }
                do {
                    System.out.println("Do you want to modify the list of genre of this production ? Y/N");
                    System.out.print("Enter choice: ");
                    option3 = scan.nextLine();
                }while (!(option3.equals("Y")) && !(option3.equals("N")));

                if (option3.equals("Y")){
                    String option4 = null;
                    do {
                        System.out.println("Remove genres ? Y/N");
                        System.out.print("Enter choice: ");
                        option4 = scan.nextLine();
                    }while (!(option4.equals("Y")) && !(option4.equals("N")));

                    while (option4.equals("Y")){
                        System.out.print("Genre to be removed: ");
                        String removeThisgenre = scan.nextLine();
                        int x = 0;
                        for (Genre genre : p.genres){

                            if(genre.name().equals(removeThisgenre)){
                                p.genres.remove(genre);
                                x = 1;
                                break;
                            }
                        }
                        if ( x == 0){
                            System.out.println("Genre not found");
                        }
                        do {
                            System.out.println("Remove genres ? Y/N");
                            System.out.print("Enter choice: ");
                            option4 = scan.nextLine();
                        }while (!(option4.equals("Y")) && !(option4.equals("N")));
                    }
                    String option5 = null;
                    do {
                        System.out.println("Add genre ? Y/N");
                        System.out.print("Enter choice: ");
                        option5 = scan.nextLine();
                    }while (!(option5.equals("Y")) && !(option5.equals("N")));

                    while (option5.equals("Y")){
                        System.out.print("Genre to be added: ");
                        String addThisGenre = scan.nextLine();
                        int x = 0;
                        for (Genre genre : p.genres){

                            if(genre.name().equals(addThisGenre)){
                                x = 1;
                            }
                        }
                        if ( x == 1){
                            System.out.println("Genre already in the list");
                        }
                        else {
                            p.genres.add(Genre.valueOf(addThisGenre));
                        }
                        do {
                            System.out.println("Add genre ? Y/N");
                            System.out.print("Enter choice: ");
                            option5 = scan.nextLine();
                        }while (!(option5.equals("Y")) && !(option5.equals("N")));
                    }

                }
                do {
                    System.out.println("Do you want to modify the list of Rating of this production ? Y/N");
                    System.out.print("Enter choice: ");
                    option3 = scan.nextLine();
                }while (!(option3.equals("Y")) && !(option3.equals("N")));

                if (option3.equals("Y")){
                    String option4 = null;
                    do {
                        System.out.println("Remove rating ? Y/N");
                        System.out.print("Enter choice: ");
                        option4 = scan.nextLine();
                    }while (!(option4.equals("Y")) && !(option4.equals("N")));

                    while (option4.equals("Y")){
                        System.out.print("Name of username rating to be removed: ");
                        String removeThisRating = scan.nextLine();
                        int x = 0;
                        for (Rating rating : p.ratings){

                            if(rating.getUsername().equals(removeThisRating)){
                                p.ratings.remove(rating);
                                x = 1;
                                break;
                            }
                        }
                        if ( x == 0){
                            System.out.println("Rating not found");
                        }
                        do {
                            System.out.println("Remove rating ? Y/N");
                            System.out.print("Enter choice: ");
                            option4 = scan.nextLine();
                        }while (!(option4.equals("Y")) && !(option4.equals("N")));
                    }

                }
//                do {
//                    System.out.println("Do you want to modify the description of this production ? Y/N");
//                    System.out.print("Enter choice: ");
//                    option3 = scan.nextLine();
//                }while (!(option3.equals("Y")) && !(option3.equals("N")));
//
//                if(option3.equals("Y")){
//                     String option4 = scan.nextLine();
//                     p.subject = option4;
//                }
                if ( p instanceof Movie movie){
                    do {
                        System.out.println("Do you want to modify the duration of this production ? Y/N");
                        System.out.print("Enter choice: ");
                        option3 = scan.nextLine();
                    }while (!(option3.equals("Y")) && !(option3.equals("N")));;

                    int errorHandler;
                    String action;
                    int numberOfOption = -1;
                    do {
                        errorHandler = 0;
                        System.out.print("Enter the updated duration of this production: ");

                        action = scan.nextLine();
                        try {
                            numberOfOption = Integer.parseInt(action);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid action. Try again !");
                            errorHandler = 1;
                        }
                    }while (errorHandler == 1);

                    movie.setTime(numberOfOption);
                    do {
                        System.out.println("Do you want to modify the release year of this production ? Y/N");
                        System.out.print("Enter choice: ");
                        option3 = scan.nextLine();
                    }while (!(option3.equals("Y")) && !(option3.equals("N")));;

                    do {
                        errorHandler = 0;
                        System.out.print("Enter the updated release year of this production: ");

                        action = scan.nextLine();
                        try {
                            numberOfOption = Integer.parseInt(action);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid action. Try again !");
                            errorHandler = 1;
                        }
                    }while (errorHandler == 1);

                    movie.setRealeaseYear(numberOfOption);



                }
                if (p instanceof Series series) {
                    do {
                        System.out.println("Do you want to modify the number of seasones of this production ? Y/N");
                        System.out.print("Enter choice: ");
                        option3 = scan.nextLine();
                    } while (!(option3.equals("Y")) && !(option3.equals("N")));


                    int errorHandler;
                    String action;
                    int numberOfOption = -1;
                    if(option3.equals("Y")) {
                        do {
                            errorHandler = 0;
                            System.out.print("Enter the updated number of seasones of this production: ");

                            action = scan.nextLine();
                            try {
                                numberOfOption = Integer.parseInt(action);
                            } catch (NumberFormatException e) {
                                System.out.println("Not a valid action. Try again !");
                                errorHandler = 1;
                            }
                        } while (errorHandler == 1);

                        series.setSeasonsNumber(numberOfOption);
                    }
                    int oldNumberSeasones = series.getSeasonsNumber();
                    do {
                        errorHandler = 0;
                        System.out.print("Enter the updated release year of this production: ");

                        action = scan.nextLine();
                        try {
                            numberOfOption = Integer.parseInt(action);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid action. Try again !");
                            errorHandler = 1;
                        }
                    } while (errorHandler == 1);

                    series.setReleaseYear(numberOfOption);
                    System.out.println("Do you want to update the episodes list ? Y/N");
                    System.out.print("Enter choice: ");
                    action = scan.nextLine();
                    if (action.equals("Y")) {
                        do {
                            errorHandler = 0;
                            System.out.print("What seasone you want to update ? : ");

                            action = scan.nextLine();
                            try {
                                numberOfOption = Integer.parseInt(action);
                            } catch (NumberFormatException e) {
                                System.out.println("Not a valid action. Try again !");
                                errorHandler = 1;
                            }
                            if (errorHandler != 1 && (numberOfOption < 1 || numberOfOption > series.getSeasonsNumber())) {
                                System.out.println("Not a valid action. Try again !");
                                System.out.println("Try a number between 1 and 2 .");
                                errorHandler = 1;
                            }


                        } while (errorHandler == 1);
                        int seasoneToUpdate = numberOfOption;

                        Set<String> seasonTitles = series.getSeasonEpisodes().keySet();
                        int i = 0;
                        String rightSeasone = "Season " + seasoneToUpdate;
//                        for (String seasonTitle : seasonTitles) {
//                            i++;
//                            if( i == seasoneToUpdate ){
//                                rightSeasone = seasonTitle;
//                                break;
//                            }
//                            //System.out.println("Season Title: " + seasonTitle);
//                        }
                        List<Episode> updateEpsiode =  series.getSeasonEpisodes().get(rightSeasone);

                        System.out.println("Do you want to remove an episode ? Y/N");
                        System.out.print("Enter choice: ");
                        action = scan.nextLine();
                        if (action.equals("Y")) {
                            do {
                                errorHandler = 0;
                                System.out.print("What episodes do  you want to remove ? : ");

                                action = scan.nextLine();
                                try {
                                    numberOfOption = Integer.parseInt(action);
                                } catch (NumberFormatException e) {
                                    System.out.println("Not a valid action. Try again !");
                                    errorHandler = 1;
                                }
                                if (errorHandler != 1 && (numberOfOption < 1 || numberOfOption > updateEpsiode.size())) {
                                    System.out.println("Not a valid action. Try again !");
                                    System.out.println("Try a number between 1 and " + updateEpsiode.size() + ".");
                                    errorHandler = 1;
                                }


                            } while (errorHandler == 1);
                            updateEpsiode.remove(numberOfOption);
                        }
                        System.out.println("Do you want to add an episode ? Y/N");
                        System.out.print("Enter choice: ");
                        action = scan.nextLine();
                        if (action.equals("Y")) {
                            System.out.print("time of the episode : ");
                            action = scan.nextLine();
                            int time = Integer.parseInt(action);
                            System.out.print("New episode name: ");
                            action = scan.nextLine();
                            Episode newEpiosode = new Episode(action, time);
                            updateEpsiode.add(newEpiosode);
                        }


                    }
                }


                //!!! DE CONTINUAT CU FIECARE CHESTIE SPECIFICA PENTRU MOVIE sau Series

            }
        }
        if (check == 0)
            System.out.println("No production found or isn't in the competence of this user");
    }

    @Override
    public void updateActor(Actor a) {
            IMDB imdb = IMDB.getInstance();
            ArrayList<Actor> actors = imdb.getActors();

            //newContriboutionActorsAndProduction.contains(p);
        int check = 0;
            for (Object contriboution : newContriboutionActorsAndProduction) {

                Scanner scan = new Scanner(System.in);
                if (contriboution instanceof Actor && ((Actor) contriboution).getName().equals(a.getName())) {
                    check = 1;
                    String option1 = null;
                    String option2 = null;
                    String option3 = null;
                    Actor contriboutedActor;
                    do {
                        contriboutedActor = (Actor) contriboution;
                        System.out.println("Do tou want to modify the name ?  Y/N");
                        option1 = scan.nextLine();
                    } while (!(option1.equals("Y")) && !(option1.equals("N")));

                    if (option1.equals("Y")){
                        System.out.print("New name: ");
                        String newName = scan.nextLine();
                        contriboutedActor.setName(newName);
                    }
                    do {
                        System.out.println("Do tou want to modify the personal biography ?  Y/N");
                        option2 = scan.nextLine();
                    }while (!(option2.equals("Y")) && !(option2.equals("N")));

                    if (option2.equals("Y")){
                        System.out.print("New Persona Biography: ");
                        String newSubject = scan.nextLine();
                        contriboutedActor.setPersonalBiograpahy(newSubject);
                    }
                    do {
                        System.out.println("Do you want to modify the career ?  Y/N");
                        option3 = scan.nextLine();
                    }while (!(option3.equals("Y")) && !(option3.equals("N")));

                    if (option3.equals("Y")){
                        String option4 = null;
                        do {
                            System.out.println("Remove role ? Y/N");
                            option4 = scan.nextLine();
                        }while (!(option4.equals("Y")) && !(option4.equals("N")));

                        while (option4.equals("Y")){
                            System.out.print("Name of the role to be removed: ");
                            String removeThisRole = scan.nextLine();
                            int x = 0;
                            a.removeCareer(removeThisRole);

                            do {
                                System.out.println("Remove role ? Y/N");
                                option4 = scan.nextLine();
                            }while (!(option4.equals("Y")) && !(option4.equals("N")));
                        }
                        String option5;
                        do {
                            System.out.println("Add role ? Y/N");
                            option5 = scan.nextLine();
                        }while (!(option5.equals("Y")) && !(option5.equals("N")));

                        while (option5.equals("Y")){
                            System.out.print("Name of the production  to be added to to the role list : ");
                            String addThisRole = scan.nextLine();
                            int x = 0;
                           Production production =  Production.searchProduction(addThisRole);
                           if(production == null){
                               System.out.println("No production found");
                           }
                           else {
                               if( production instanceof Movie)
                                    a.adCareer(production.getTitle(), "Movie");
                               else if ( production instanceof Series)
                                   a.adCareer(production.getTitle(), "Series");
                           }

                            do {
                                System.out.println("Add role ? Y/N");
                                option5 = scan.nextLine();
                            }while (!(option5.equals("Y")) && !(option5.equals("N")));
                        }

                    }


                }
            }
            if (check == 0)
                System.out.println("No actor found or isn't in the competence of this user ");

    }
    public void printUserInfo(){
        super.printUserInfo();
        //System.out.println("Contribution: " + this.newContriboutionActorsAndProduction);
        for (Object contriboution : newContriboutionActorsAndProduction){
            if(contriboution instanceof Production){
                System.out.println(((Production) contriboution).getTitle());
            }
            else if (contriboution instanceof Actor){
                System.out.println(((Actor) contriboution).getName());
            }
        }
        System.out.println();
    }
    public  Production searchProductionContriboution(String title){

        for ( T contriboution : newContriboutionActorsAndProduction){
            if (contriboution instanceof Production production){
                if (production.getTitle().equals(title))
                    return production;
            }
        }
        return null;
    }
    public  Actor searchActorContriboution(String name){

        for ( T contriboution : newContriboutionActorsAndProduction){
            if (contriboution instanceof Actor actor){
                if (actor.getName().equals(name))
                    return actor;
            }
        }
        return null;
    }
    public void addToRequestList(Request request){
        if (requestList == null)
            requestList = new ArrayList<>();
        requestList.add(request);
    }
    public void displayContriboutions(){
            for( T countriboution : newContriboutionActorsAndProduction){
                if(countriboution instanceof Production production)
                    System.out.println(production.getTitle());
                else if(countriboution instanceof Actor actor)
                    System.out.println(actor.getName());
            }
    }
    public void displayRequests() {
        int i = 0;
        if (requestList == null)
            System.out.println("No request available");
        else {
            for (Request request : requestList) {
                i++;

                System.out.println("    " + i + ")");
                request.displayRequest();
                System.out.println();
            }
        }
    }

    public List<Request> getRequestList() {
        return requestList;
    }
    public void removeRequestToDo(Request r){
        IMDB imdb = IMDB.getInstance();
        String username = r.getUsernameOfRequest();
        List<User<?>> users = imdb.getUsers();

        for (User user : users){

            if (user.getUsername().equals(username)){
                if (user instanceof Regular regular)
                    regular.removeRequest(r);
                else if (user instanceof Contributor contributor){
                    contributor.removeRequest(r);
                }
                requestList.remove(r);

            }
        }

    }
}

