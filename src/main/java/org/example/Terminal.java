package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Terminal {

    public static User autentification(){
        IMDB imdb = IMDB.getInstance();
        String email;
        String password;
        User account;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Enter credentials");
            System.out.print("Enter email:");
            email = scan.nextLine();
            System.out.print("Enter password:");
            password = scan.nextLine();
            account = imdb.autentification(email, password);
            if (account == null)
                System.out.println("Incorrect credentials. Try Again!");
        }while (account == null);

        return account;
    }
    public static void regularAdminPage(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Chose action: ");
        System.out.println("    1) View productions details");
        System.out.println("    2) View actors details");
        System.out.println("    3) View notification");
        System.out.println("    4) Search for actor/movie/series");
        System.out.println("    5) Add/Delete actor/movie/series from favorites");
        System.out.println("    6) Add/Delete user");
        System.out.println("    7) Add/Delete actor/movie/series from system");
        System.out.println("    8) Update Production details");
        System.out.println("    9) Update Actor details");
        System.out.println("    10) Solve a request");
        System.out.println("    11) Logout");
    }
    public static int regularFirstPage(){
        Scanner scan = new Scanner(System.in);
        String actionMenu;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Chose action: ");
            System.out.println("    1) View productions details");
            System.out.println("    2) View actors details");
            System.out.println("    3) View notification");
            System.out.println("    4) Search for actor/movie/series");
            System.out.println("    5) Add/Delete actor/movie/series from favorites");
            System.out.println("    6) Create/Delete a request");
            System.out.println("    7) Add/Delete a review for a production");
            System.out.println("    8) Logout");
            System.out.println("    9) Exit");
            System.out.print("Enter choice: ");

            actionMenu = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(actionMenu);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to view productions details ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 9)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 8 .");
                errorHandler = 1;
            }
        } while (errorHandler == 1);
        return numberOfOption;
    }
    public static int contributorFirstPage(){
        Scanner scan = new Scanner(System.in);
        String actionMenu;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Chose action: ");
            System.out.println("    1) View productions details");
            System.out.println("    2) View actors details");
            System.out.println("    3) View notification");
            System.out.println("    4) Search for actor/movie/series");
            System.out.println("    5) Add/Delete actor/movie/series from favorites");
            System.out.println("    6) Create/Delete a request");
            System.out.println("    7) Add/Delete a production/actor");
            System.out.println("    8) Resolve and view requests");
            System.out.println("    9) Update the information  of a production/actor");
            System.out.println("    10) Logout");
            System.out.println("    11) Exit");
            System.out.print("Enter choice: ");

            actionMenu = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(actionMenu);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to view productions details ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 11)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 11 .");
                errorHandler = 1;
            }
        } while (errorHandler == 1);
        return numberOfOption;
    }
    public static int adminFirstPage(){
        Scanner scan = new Scanner(System.in);
        String actionMenu;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Chose action: ");
            System.out.println("    1) View productions details");
            System.out.println("    2) View actors details");
            System.out.println("    3) View notification");
            System.out.println("    4) Search for actor/movie/series");
            System.out.println("    5) Add/Delete actor/movie/series from favorites");
            System.out.println("    6) Add/Delete user");
            System.out.println("    7) Add/Delete a production/actor");
            System.out.println("    8) Resolve and view requests");
            System.out.println("    9) Update the information  of a production/actor");
            System.out.println("    10) Logout");
            System.out.println("    11) Exit");
            System.out.print("Enter choice: ");

            actionMenu = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(actionMenu);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to view productions details ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 11)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 11 .");
                errorHandler = 1;
            }
        } while (errorHandler == 1);
        return numberOfOption;
    }
    public static void addOrDeleteUser(){
        Scanner scan = new Scanner(System.in);
        String actionMenu;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Add or delete users: ");
            System.out.println("    1) Add user");
            System.out.println("    2) Delete user");
            System.out.print("Enter choice: ");

            actionMenu = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(actionMenu);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to add user ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 2)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 2 .");
                errorHandler = 1;
            }
        } while (errorHandler == 1);
        IMDB imdb = IMDB.getInstance();
        ArrayList<User<?>> users = imdb.getUsers();
        switch (numberOfOption){
            case 2:
                User removeUser = selectUser();
                users.remove(removeUser);
                break;
            case 1:
                AccountType accountType = null;
                do {
                    errorHandler = 0;
                    System.out.println("Enter type of user: ");
                    actionMenu = scan.nextLine();

                    try {
                        accountType = AccountType.valueOf(actionMenu.toUpperCase());
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("This is no type of user");
                        errorHandler = 1;
                    }
                }while (errorHandler == 1);
                UserFactory userFactory = new UserFactory();
                User newUser =  userFactory.createUserType(accountType);
                newUser.setNotifications(new ArrayList<>());
                newUser.setFavorites(new TreeSet<>());
                User.Information.BuilderInformation newBuilder = User.Information.BuilderInformation.newInstance();

                System.out.print("Enter new user email: ");
                actionMenu = scan.nextLine();
                Credentials newCredentials = new Credentials();
                newCredentials.setEmail(actionMenu);
                System.out.print("Enter name ,example -Anakin Skywalker-: ");
                String name = scan.nextLine();
                String[] separateName = name.split(" ");
                Random random = new Random();
                int userNumber = 1000 + random.nextInt(8999);
                String username = separateName[0].toLowerCase() + "_" + separateName[1].toLowerCase() + userNumber;
                newUser.setUsername(username);
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&";
                StringBuilder passwordBuilder = new StringBuilder();

                for (int iterator = 0; iterator < 11; iterator++){
                    int randomNumber = random.nextInt(characters.length());
                    passwordBuilder.append(characters.charAt(randomNumber));

                }
                newCredentials.setPassword(passwordBuilder.toString());
                System.out.println("This is the password: " + passwordBuilder.toString());
                System.out.print("Enter country: ");
                String country = scan.nextLine();
                newUser.setTypeOfUser(accountType);
                System.out.print("Enter gender: ");
                String genderString = scan.nextLine();
                char gender = genderString.toUpperCase().charAt(0);
                int age = -1;
                do {
                    errorHandler = 0;
                    System.out.print("Enter age: ");
                    String ageString = scan.nextLine();
                    try {
                        age = Integer.parseInt(ageString);
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Not a valid action. Try again !");
                        errorHandler = 1;
                    }
                }while (errorHandler == 1);

                System.out.print("Enter bhirtdate: ");
                String birth = scan.nextLine();
                LocalDateTime birthdate = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    // Parse the user input into a LocalDateTime object
                    birthdate = LocalDateTime.parse(birth, formatter);

                    // Print the parsed LocalDateTime
                } catch (Exception e) {
                    System.out.println("Error: Invalid date  format ");
                }
                User.Information newInformation = newBuilder.setCredentials(newCredentials).setCountry(country).setGen(gender).
                            setName(name).setDateOfBirth(birthdate).setAge(age).build();
                newUser.setInfo(newInformation);
                imdb.addUser(newUser);
                break;
        }
    }
    public static int addOrDeleteProductionOrActor(){
        Scanner scan = new Scanner(System.in);
        String actionMenu;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Chose action: ");
            System.out.println("    1) Add production");
            System.out.println("    2) Delete production");
            System.out.println("    3) Add actor");
            System.out.println("    4) Delete actor");
            System.out.println("    5) Show contribution list");
            System.out.print("Enter choice: ");

            actionMenu = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(actionMenu);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to add productions  ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 5)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 5 .");
                errorHandler = 1;
            }
        } while (errorHandler == 1);
        return numberOfOption;
    }

    public static void addProduction(Staff user){
        Scanner scan = new Scanner(System.in);
        String actionMenu;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Chose action: ");
            System.out.println("    1) Movie");
            System.out.println("    2) Series");
            System.out.print("Enter choice: ");

            actionMenu = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(actionMenu);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to add movie ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 2)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 2 .");
                errorHandler = 1;
            }
        } while (errorHandler == 1);

        System.out.print("Enter the name of the production you wish to add: ");
        String productionTitle = scan.nextLine();

        Production findProduction = Production.searchProduction(productionTitle);
        if (findProduction != null)
            System.out.println("Production is already added");
        else{
            List<String> directors = new ArrayList<>();
            int directorsNumber = 0;
            do {
                errorHandler = 0;
                System.out.print("Number of directors: ");
                actionMenu = scan.nextLine();

                try {
                    directorsNumber = Integer.parseInt(actionMenu);
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid action. Try again !");
                    System.out.println("Example of command to do an action: Insert '1' to add 1 director ");
                    errorHandler = 1;
                }

            } while (errorHandler == 1);


            for (int i = 1; i <= directorsNumber; i++){
                System.out.print("Name of director: ");
                String directorName = scan.nextLine();
                directors.add(directorName);
            }
            List<String> actors = new ArrayList<>();
            int actorsNumber = 0;
            do {
                errorHandler = 0;
                System.out.print("Number of actors: ");
                actionMenu = scan.nextLine();

                try {
                    actorsNumber = Integer.parseInt(actionMenu);
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid action. Try again !");
                    System.out.println("Example of command to do an action: Insert '1' to add 1 actor ");
                    errorHandler = 1;
                }

            } while (errorHandler == 1);


            for (int i = 1; i <= actorsNumber; i++){
                System.out.print("Name of actor: ");
                String actorName = scan.nextLine();
                actors.add(actorName);
            }
            List<Genre> genres = new ArrayList<>();
            int genresNumber = 0;
            do {
                errorHandler = 0;
                System.out.print("Number of genres: ");
                actionMenu = scan.nextLine();

                try {
                    genresNumber = Integer.parseInt(actionMenu);
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid action. Try again !");
                    System.out.println("Example of command to do an action: Insert '1' to add 1 genre ");
                    errorHandler = 1;
                }

            } while (errorHandler == 1);

            for (int i = 1; i <= genresNumber; i++){
                Genre genre = null;
                do {
                    errorHandler = 0;
                    System.out.print("Genre: ");
                    String genreString = scan.nextLine();
                    try {
                        genre = Genre.valueOf(genreString.toUpperCase());
                        if (genres != null) {
                            for (Genre genre1 : genres){
                                if (genre1 == genre){
                                    System.out.println("Genre already present");
                                    errorHandler = 1;
                                    break;
                                }
                            }
                        }
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println("This is no genre");
                        errorHandler = 1;
                    }
                }while (errorHandler == 1);
                if (genre != null)
                    genres.add(genre);
            }
            List<Rating> ratings = new ArrayList<>();
            System.out.print("Enter the subject of the production: ");
            String productionSubject = scan.nextLine();
            double grading = 0;


            switch (numberOfOption){
                case 1:
                    int time = -1;
                    do {
                        errorHandler = 0;
                        System.out.print("Time of the production: ");
                        actionMenu = scan.nextLine();

                        try {
                            time = Integer.parseInt(actionMenu);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid action. Try again !");
                            errorHandler = 1;
                        }

                    } while (errorHandler == 1);
                    int releaseYear = -1;
                    do {
                        errorHandler = 0;
                        System.out.print("Release year of the production: ");
                        actionMenu = scan.nextLine();

                        try {
                            releaseYear = Integer.parseInt(actionMenu);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid action. Try again !");;
                            errorHandler = 1;
                        }

                    } while (errorHandler == 1);

                    Movie newMovie = new Movie(productionTitle, directors, actors, genres, ratings, productionSubject,
                            grading, time, releaseYear);

                    IMDB imdb = IMDB.getInstance();

                    //imdb.addProduction(newMovie);
                    user.addProductionSystem(newMovie);
                    //user.addNewContriboutionActorAndProudcction(newMovie);
                    break;

                case 2:
                    int numberOfSeasones = -1;
                    do {
                        errorHandler = 0;
                        System.out.print("Number of seasones: ");
                        actionMenu = scan.nextLine();

                        try {
                            numberOfSeasones = Integer.parseInt(actionMenu);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid action. Try again !");
                            errorHandler = 1;
                        }

                    } while (errorHandler == 1);

                    int releaseYearSeries = -1;
                    do {
                        errorHandler = 0;
                        System.out.print("Release year of the production: ");
                        actionMenu = scan.nextLine();

                        try {
                            releaseYearSeries = Integer.parseInt(actionMenu);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid action. Try again !");;
                            errorHandler = 1;
                        }

                    } while (errorHandler == 1);
                    Map<String, List<Episode>> seasonEpisodes = new HashMap<>();
                    for ( int i = 1; i < numberOfSeasones; i++) {
                        String seasonesName = "Season " + i;
                        int numberEpisodes = -1;
                        do {
                            errorHandler = 0;
                            System.out.print("Number of episode in " + i + " season : ");
                            actionMenu = scan.nextLine();

                            try {
                                numberEpisodes = Integer.parseInt(actionMenu);
                            } catch (NumberFormatException e) {
                                System.out.println("Not a valid action. Try again !");

                                errorHandler = 1;
                            }
                        } while (errorHandler == 1);

                        List<Episode> episodes = new ArrayList<>();
                        for (int j = 1; j <= numberEpisodes; j++){

                            System.out.print("Name of episode in: ");
                            String episodeName = scan.nextLine();
                            int episodeTime = -1;
                            do {
                                errorHandler = 0;
                                System.out.print("time: ");
                                actionMenu = scan.nextLine();

                                try {
                                    episodeTime = Integer.parseInt(actionMenu);
                                } catch (NumberFormatException e) {
                                    System.out.println("Not a valid action. Try again !");

                                    errorHandler = 1;
                                }
                            } while (errorHandler == 1);

                            Episode episode = new Episode(episodeName,episodeTime);

                            episodes.add(episode);
                        }
                        seasonEpisodes.put(seasonesName, episodes);

                    }
                    Series newSeries = new Series(productionTitle, directors, actors, genres, ratings, productionSubject,
                            grading, releaseYearSeries, numberOfSeasones, seasonEpisodes);
                    IMDB imdb2 = IMDB.getInstance();

                    user.addProductionSystem(newSeries);
                    //imdb2.addProduction(newSeries);
                    //user.addNewContriboutionActorAndProudcction(newSeries);

                    break;

            }
        }

    }
    public static void addActor(Staff user){
        Scanner scan = new Scanner(System.in);
        String actionMenu;
        int numberOfOption = -1;
        int errorHandler;

        System.out.print("Enter the name of the actor you wish to add: ");
        String actorName = scan.nextLine();

        Actor findActor = Actor.searchActors(actorName);
        if (findActor != null)
            System.out.println("Actor is already added");
        else{
            System.out.print("Enter personal biography: ");
            String personalBiography = scan.nextLine();

            List<Map.Entry<String, String>> career = new ArrayList<>();
            do{
                System.out.println("Select a production where this actor had a role: ");
                 Production production =  Terminal.selectProduction();

                 if (production instanceof Movie)
                     career.add(new AbstractMap.SimpleEntry<>(production.getTitle(), "Movie"));

                 else if (production instanceof Series){
                     career.add(new AbstractMap.SimpleEntry<>(production.getTitle(), "Series"));;
                 }
                 System.out.println("Another entry ? Y/N");
                 actionMenu = scan.nextLine();

            }while(actionMenu.equals("Y"));
            Actor newActor = new Actor(actorName, career, personalBiography);
            user.addActorSystem(newActor);
            //user.addNewContriboutionActorAndProudcction(newActor);
        }

    }
    public static void removeProduction(Staff user){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the name of the production you wish to delete: ");
        String productionTitle = scan.nextLine();
        user.removeProductionSystem(productionTitle);

//        Production findProduction = Production.searchProduction(productionTitle);
//        if (findProduction != null)
//            System.out.println("Production is already added");
//        else {
//            Production searchContriboution = user.searchProductionContriboution(productionTitle);
//            if (searchContriboution == null)
//                System.out.println("This production isn't added by this user");
//        }

    }
    public static void removeActor(Staff user){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the name of the actor you wish to delete: ");
        String actorName = scan.nextLine();
        user.removeActorSystem(actorName);
    }
    public static int ViewOrResolveRequestMenu(){
        Scanner scan = new Scanner(System.in);
        String actionMenu;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Chose action: ");
            System.out.println("    1) Resolve request");
            System.out.println("    2) View requests");
            System.out.print("Enter choice: ");

            actionMenu = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(actionMenu);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' resolve request  ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 2)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 2 .");
                errorHandler = 1;
            }
        } while (errorHandler == 1);
        return numberOfOption;
    }
    public static int ViewProductionsDetails(){
        Scanner scan = new Scanner(System.in);
        String action;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Chose filter to view productions details: ");
            System.out.println("    1) Filter over genre");
            System.out.println("    2) Filter over number of reviews");
            System.out.println("    3) No filter");
            System.out.print("Enter choice: ");

            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '3' to view productions details " +
                        "with no filter");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 3)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 3 .");
                errorHandler = 1;
            }
        }while (errorHandler == 1);
        return numberOfOption;

    }
    public static void filterProductionOnGenres(){
        IMDB imdb = IMDB.getInstance();
        Scanner scan = new Scanner(System.in);
        String action;
        Genre actionGenre = null;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.print("Enter genre to filter production: ");
            action = scan.nextLine();
            try {
                actionGenre = Genre.valueOf(action.toUpperCase());
            } catch (IllegalArgumentException e) {
                errorHandler = 1;
                System.out.println("Invalid genre! Check the genre list and try again !");
            }

        }while(errorHandler == 1);
        if (actionGenre != null) {
            List<Production> productionsFilteredOfGenre = Production.
                    FilteredProductionsOnGenre(imdb.getProductions(), actionGenre);

            for (Production production : productionsFilteredOfGenre){

                if (production instanceof Movie){
                    Movie movieProduction = (Movie) production;
                    movieProduction.displayInfo();

                } else if (production instanceof Series) {
                    Series seriesProduction = (Series) production;
                    seriesProduction.displayInfo();
                }
            }
        }
    }
    public static void filterProductionOnNumberOfReviews(){
        IMDB imdb = IMDB.getInstance();
        Scanner scan = new Scanner(System.in);
        String action;
        Integer actionReview = null;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.print("Enter the number of reviews on which the function is filtered: ");
            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to filter the productions on " +
                        "1 review ");
                errorHandler = 1;
            }
            if (errorHandler != 1 &&  numberOfOption < -1){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number over 0.");
                errorHandler = 1;
            }

        }while(errorHandler == 1);
        actionReview = numberOfOption;

        if (actionReview != null) {
            List<Production> productionsFilteredOfNumbersOfReviews = Production.
                    FilteredProductionsOnNumberOfReviws(imdb.getProductions(), actionReview);

            for (Production production : productionsFilteredOfNumbersOfReviews){

                if (production instanceof Movie){
                    Movie movieProduction = (Movie) production;
                    movieProduction.displayInfo();

                } else if (production instanceof Series) {
                    Series seriesProduction = (Series) production;
                    seriesProduction.displayInfo();
                }
            }
        }
    }
    public static void noFilterProductions(){
        IMDB imdb = IMDB.getInstance();
        List<Production> productions = imdb.getProductions();

        for (Production production : productions){
            if (production instanceof Movie){
                Movie movieProduction = (Movie) production;
                movieProduction.displayInfo();

            } else if (production instanceof Series) {
                Series seriesProduction = (Series) production;
                seriesProduction.displayInfo();
            }
        }
    }
    public static int viewActorsDetails(){
        Scanner scan = new Scanner(System.in);
        String action;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("Do you want to sort actors on the criteria ?: ");
            System.out.println("    1) Filter over name ascendant ");
            System.out.println("    2) Filter over name descendant ");
            System.out.println("    3) No filter");
            System.out.print("Enter choice: ");

            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '3' to view actors details " +
                        "with no filter");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 3)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 3 .");
                errorHandler = 1;
            }
        }while (errorHandler == 1);
        return numberOfOption;

    }
    public static void  actorsInfo(List<Actor> actors){

        for (Actor actor : actors) {

            System.out.println("Name: " + actor.name);
            System.out.println("Biography: " + actor.personalBiograpahy);
            System.out.println("Performances:");

            for (Map.Entry<String, String> performance : actor.carrer) {
                System.out.println("  Title: " + performance.getKey() + ", Type: " + performance.getValue());
            }
            System.out.println();
        }
    }
    public static int searchingMenu(){
        Scanner scan = new Scanner(System.in);
        String action;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("What do you want to search ?  ");
            System.out.println("    1) A movie ");
            System.out.println("    2) A series ");
            System.out.println("    3) An actor  ");
            System.out.print("Enter choice: ");

            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to search an movie ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 3)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 3 .");
                errorHandler = 1;
            }
        }while (errorHandler == 1);
        return numberOfOption;
    }
    public static void searchActor(){
        Scanner scan = new Scanner(System.in);
        String searchName;

        System.out.print("Enter the name of the actor that you wish to search: ");
        searchName = scan.nextLine();
        Actor actor = Actor.searchActors(searchName);

        if (actor == null) {
            System.out.println("No actor matching this name found ");
            return;
        }
        actor.displayInfo();

    }
    public static void searchMovie(){
        Scanner scan = new Scanner(System.in);
        String searchTitle;

        System.out.print("Enter the title of the movie that you wish to search: ");
        searchTitle = scan.nextLine();
        Movie movie = Movie.searchMovie(searchTitle);

        if (movie == null) {
            System.out.println("No movie matching this title found ");
            return;
        }
        movie.displayInfo();

    }
    public static void searchSeries(){
        Scanner scan = new Scanner(System.in);
        String searchTitle;

        System.out.print("Enter the title of the series that you wish to search: ");
        searchTitle = scan.nextLine();
        Series series = Series.searchSeries(searchTitle);

        if (series == null) {
            System.out.println("No series matching this title found ");
            return;
        }
        series.displayInfo();

    }
    public static int FavotiteMenu(){
        Scanner scan = new Scanner(System.in);
        String action;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("What modification to the favorite list do you want ?  ");
            System.out.println("    1) Add production to favorite ");
            System.out.println("    2) Delete production from favorite");
            System.out.println("    3) Add actor to favorite ");
            System.out.println("    4) Delete actor from favorite");
            System.out.println("    5) View favorite list  ");
            System.out.print("Enter choice: ");

            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to add production to the favorite ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 5)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 5 .");
                errorHandler = 1;
            }
        }while (errorHandler == 1);

        return numberOfOption;
    }
    public static Production searchProductionToFavorites(){

        Scanner scan = new Scanner(System.in);
        String Title;

        System.out.print("Enter the title of the  production that you wish to add to the favorites: ");
        Title = scan.nextLine();
        Production production = Production.searchProduction(Title);

        if (production== null) {
            System.out.println("No production matching this title found ");
            return null;
        }
        return production;
    }
    public static Actor searchActorToFavorites(){

        Scanner scan = new Scanner(System.in);
        String name;

        System.out.print("Enter the name of the actor that you wish to add to the favorites: ");
        name = scan.nextLine();
        Actor actor = Actor.searchActors(name);

        if (actor == null) {
            System.out.println("No actor matching this name found ");
            return null;
        }
        return actor;
    }
    public static int requestMenu(){

        Scanner scan = new Scanner(System.in);
        String action;
        int numberOfOption = -1;
        int errorHandler;

        do {
            errorHandler = 0;
            System.out.println("What modification to the request list do you want ?  ");
            System.out.println("    1) Add request ");
            System.out.println("    2) Delete request");
            System.out.println("    3) View request list  ");
            System.out.print("Enter choice: ");

            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to add a request ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 3)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 3 .");
                errorHandler = 1;
            }
        }while (errorHandler == 1);

        return numberOfOption;
    }
    public static void addRequest( User account){
        Scanner scan = new Scanner(System.in);
        String action;
        int errorHandler;
        int numberOfOption = -1;
        IMDB imdb = IMDB.getInstance();

        do {
            errorHandler = 0;
            System.out.println("For what you want to do a request ? ");
            System.out.println("    1) Production");
            System.out.println("    2) Actor");
            System.out.println("    3) Others");
            System.out.print("Enter choice: ");

            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to select production  ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 3)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and 23.");
                errorHandler = 1;
            }
        }while (errorHandler == 1);

        switch (numberOfOption){
            case 1:
                System.out.print("Enter the title of the production on which do you want to make a request: ");
                action = scan.nextLine();
                Production production = Production.searchProduction(action);

                if (production == null){
                    System.out.println("No production matching this title found ");
                    return;
                }
                else{

                    Request newRequest = new Request();
                    newRequest.setAction(RequestType.MOVIE_ISSUE);
                    newRequest.setProductionOrActor(production.getTitle());
                    newRequest.setUsernameOfRequest(account.getUsername());
                    newRequest.setDateOfRequest(LocalDateTime.now());

                    System.out.println("Enter the problem description ");
                    action = scan.nextLine();
                    newRequest.setProblemDescription(action);
                    List<User<?>> users = imdb.getUsers();
                     int check = 0;
                    for ( User user : users){
                        if ( user instanceof Staff userStaff){
                            Production contriboutionProduction = userStaff.searchProductionContriboution(
                                    newRequest.getProductionOrActor());
                            if (contriboutionProduction != null) {
                                check = 1;
                                newRequest.setUsernameOfDoRequest(user.getUsername());
                                userStaff.addToRequestList(newRequest);
                            }
                        }
                    }
                    if (check == 0) {
                        newRequest.setUsernameOfRequest("Admin");
                        for ( User user : users){
                            if ( user instanceof Admin userStaff){
                                userStaff.addToRequestList(newRequest);

                            }
                        }
                    }

                    if (account instanceof Regular) {
                        Regular accountRegular = (Regular) account;
                        accountRegular.createRequest(newRequest);
                    }
                    else if(account instanceof Contributor){
                        Contributor accountContriboutor = (Contributor) account;
                        accountContriboutor.createRequest(newRequest);
                    }
                }
                break;

            case 2:
                System.out.print("Enter the name of the actor on which do you want to make a request: ");
                action = scan.nextLine();
                Actor actor = Actor.searchActors(action);

                if (actor == null){
                    System.out.println("No actor matching this actor found ");
                    return;
                }
                else{
                    Request newRequest = new Request();
                    newRequest.setAction(RequestType.ACTOR_ISSUE);
                    newRequest.setProductionOrActor(actor.getName());
                    newRequest.setUsernameOfRequest(account.getUsername());
                    newRequest.setDateOfRequest(LocalDateTime.now());


                    System.out.println("Enter the problem description ");
                    action = scan.nextLine();
                    newRequest.setProblemDescription(action);
                    List<User<?>> users = imdb.getUsers();

                    for ( User user : users){
                        if ( user instanceof Staff userStaff){
                            Actor contriboutionActor = userStaff.searchActorContriboution(
                                    newRequest.getProductionOrActor());
                            if (contriboutionActor != null) {
                                newRequest.setUsernameOfRequest(user.getUsername());
                                userStaff.addToRequestList(newRequest);
                            }
                        }
                    }
                    if (account instanceof Regular) {
                        Regular accountRegular = (Regular) account;
                        accountRegular.createRequest(newRequest);
                    }
                    else if(account instanceof Contributor){
                        Contributor accountContriboutor = (Contributor) account;
                        accountContriboutor.createRequest(newRequest);
                    }
                }
                break;
            case 3:
                Request newRequest = new Request();
                int numberOfOption2 = -1;
                do {
                    errorHandler = 0;
                System.out.println("What type of request do tou want to do ?");
                System.out.println("    1) DELETE_ACCOUNT");
                System.out.println("    2) OTHERS");
                action = scan.nextLine();
                    try {
                        numberOfOption2 = Integer.parseInt(action);
                    } catch (NumberFormatException e) {
                        System.out.println("Not a valid action. Try again !");
                        System.out.println("Example of command to do an action: Insert '1' to select DELETE_ACCOUNT  ");
                        errorHandler = 1;
                    }
                    if (errorHandler != 1 && ( numberOfOption2 < 1 || numberOfOption2 > 2)){
                        System.out.println("Not a valid action. Try again !");
                        System.out.println("Try a number between 1 and 2.");
                        errorHandler = 1;
                    }
                }while (errorHandler == 1);
                switch (numberOfOption2) {
                    case 1:
                        newRequest.setAction(RequestType.DELETE_ACCOUNT);
                        break;

                    case 2:
                        newRequest.setAction(RequestType.OTHERS);
                        break;
                }
                newRequest.setProductionOrActor(null);
                newRequest.setUsernameOfRequest(account.getUsername());
                newRequest.setDateOfRequest(LocalDateTime.now());


                System.out.println("Enter the problem description ");
                action = scan.nextLine();
                newRequest.setProblemDescription(action);
                List<User<?>> users = imdb.getUsers();

                newRequest.setUsernameOfDoRequest("ADMIN");
                Admin.RequestsHolder.addRequest(newRequest);

                if (account instanceof Regular) {
                    Regular accountRegular = (Regular) account;
                    accountRegular.createRequest(newRequest);
                }
                else if(account instanceof Contributor){
                    Contributor accountContriboutor = (Contributor) account;
                    accountContriboutor.createRequest(newRequest);
                }
                break;
        }

        }
        public static void removeRequest(User user){
            Scanner scan = new Scanner(System.in);
            String action;
            int errorHandler;
            int numberOfOption = -1;
            IMDB imdb = IMDB.getInstance();
            List<Request> requestList = null;

            if  (user instanceof Regular userRegular){
                requestList = userRegular.getRequestList();
            }
            else if (user instanceof Contributor userContriboutor){
                requestList = userContriboutor.getRequestList();
            }
            int i = 0;

            do {
                i = 0;
                errorHandler = 0;
                System.out.println("For what request you want to delete ? ");


                for (Request request : requestList) {
                    i++;

                    System.out.println("    " + i + ")");
                    request.displayRequest();
                    System.out.println();
                }
                System.out.print("Enter choice: ");
                action = scan.nextLine();
                try {
                    numberOfOption = Integer.parseInt(action);
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid action. Try again !");
                    System.out.println("Example of command to do an action: Insert '1' to a select  request  1 ");
                    errorHandler = 1;
                }
                if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > i)){
                    System.out.println("Not a valid action. Try again !");
                    System.out.println("Try a number between 1 and " + i + ".");
                    errorHandler = 1;
                }
            }while (errorHandler == 1);

            i = 0;

            for (Request request : requestList){
                i++;
                if (i == numberOfOption){
                    if  (user instanceof Regular userRegular){
                        userRegular.removeRequest(request);
                        return;
                    }
                    else if (user instanceof Contributor userContriboutor){
                        userContriboutor.removeRequest(request);
                        return;
                    }
                }
            }
        }
        public static int RatingMenu(){
            Scanner scan = new Scanner(System.in);
            String action;
            int numberOfOption = -1;
            int errorHandler;

            do {
                errorHandler = 0;
                System.out.println("What modification to the rating list do you want ?  ");
                System.out.println("    1) Add rating for a production");
                System.out.println("    2) Delete rating from a production");
                System.out.println("    3) View rating list of a production  ");
                System.out.print("Enter choice: ");

                action = scan.nextLine();
                try {
                    numberOfOption = Integer.parseInt(action);
                } catch (NumberFormatException e) {
                    System.out.println("Not a valid action. Try again !");
                    System.out.println("Example of command to do an action: Insert '1' to add a request ");
                    errorHandler = 1;
                }
                if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 3)){
                    System.out.println("Not a valid action. Try again !");
                    System.out.println("Try a number between 1 and 3 .");
                    errorHandler = 1;
                }
            }while (errorHandler == 1);

            return numberOfOption;
        }
public static Production selectProduction(){

    Scanner scan = new Scanner(System.in);
    String action;
    int numberOfOption = -1;
    int errorHandler;
    IMDB imdb = IMDB.getInstance();
    int i = 0;

    do {
        i = 0;
        errorHandler = 0;
        System.out.println("Select a production:  ");

        for (Production production : imdb.getProductions()){
            i++;

            System.out.println("    " + i + ") " + production.getTitle());
        }
        System.out.print("Enter choice: ");

        action = scan.nextLine();
        try {
            numberOfOption = Integer.parseInt(action);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid action. Try again !");
            System.out.println("Example of command to do an action: Insert '1' to select a production ");
            errorHandler = 1;
        }
        if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > i)){
            System.out.println("Not a valid action. Try again !");
            System.out.println("Try a number between 1 and " + i );
            errorHandler = 1;
        }
    }while (errorHandler == 1);

    i = 0;

    for (Production production : imdb.getProductions()){
        i++;
        if (i == numberOfOption){
            return production;
        }
    }
    return null;

}
    public static User selectUser(){

        Scanner scan = new Scanner(System.in);
        String action;
        int numberOfOption = -1;
        int errorHandler;
        IMDB imdb = IMDB.getInstance();
        int i = 0;

        do {
            i = 0;
            errorHandler = 0;
            System.out.println("Select a user:  ");

            for (User user : imdb.getUsers()){
                i++;

                System.out.println("    " + i + ") " + user.getUsername());
            }
            System.out.print("Enter choice: ");

            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to select a production ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > i)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and " + i );
                errorHandler = 1;
            }
        }while (errorHandler == 1);

        i = 0;

//        for (Production production : imdb.getProductions()){
//            i++;
//            if (i == numberOfOption){
//                return production;
//            }
//        }
        ArrayList<User<?>> users =  imdb.getUsers();
         return users.get(numberOfOption - 1);
        //return null;

    }
    public static Actor selectActor(){

        Scanner scan = new Scanner(System.in);
        String action;
        int numberOfOption = -1;
        int errorHandler;
        IMDB imdb = IMDB.getInstance();
        int i = 0;

        do {
            i = 0;
            errorHandler = 0;
            System.out.println("Select an actor:  ");

            for (Actor actor : imdb.getActors()){
                i++;

                System.out.println("    " + i + ") " + actor.getName());
            }
            System.out.print("Enter choice: ");

            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to select an actor ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > i)){
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and " + i );
                errorHandler = 1;
            }
        }while (errorHandler == 1);

        i = 0;

        for (Actor actor : imdb.getActors()){
            i++;
            if (i == numberOfOption){
                return actor;
            }
        }
        return null;

    }
public static Request selectRequestToResolve(Staff user){
    Scanner scan = new Scanner(System.in);
    String action;
    int numberOfOption = -1;
    int errorHandler;
    IMDB imdb = IMDB.getInstance();
    int i = 0;
    List<Request> requestsToResolve = user.getRequestList();
    if (requestsToResolve == null)
        System.out.println("No request avalaible ");
    else {
        do {
            i = 0;
            errorHandler = 0;
            System.out.println("Select a request to resolve it:  ");


            for (Request request : requestsToResolve) {
                i++;

                System.out.println("    " + i + ")");
                request.displayRequest();
                System.out.println();
            }
            System.out.print("Enter choice: ");
            action = scan.nextLine();
            try {
                numberOfOption = Integer.parseInt(action);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Example of command to do an action: Insert '1' to select request 1  ");
                errorHandler = 1;
            }
            if (errorHandler != 1 && (numberOfOption < 1 || numberOfOption > i)) {
                System.out.println("Not a valid action. Try again !");
                System.out.println("Try a number between 1 and " + i + ".");
                errorHandler = 1;
            }
        } while (errorHandler == 1);

        i = 0;

        for (Request request : requestsToResolve) {
            i++;
            if (i == numberOfOption) {
                return request;
            }
        }
    }
    return null;

}
public static int updateMenu(){
    Scanner scan = new Scanner(System.in);
    String action;
    int numberOfOption = -1;
    int errorHandler;

    do {
        errorHandler = 0;
        System.out.println("What you whish to update ?  ");
        System.out.println("    1) A production");
        System.out.println("    2) An actor");
        System.out.print("Enter choice: ");

        action = scan.nextLine();
        try {
            numberOfOption = Integer.parseInt(action);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid action. Try again !");
            System.out.println("Example of command to do an action: Insert '1' to update a production ");
            errorHandler = 1;
        }
        if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 2)){
            System.out.println("Not a valid action. Try again !");
            System.out.println("Try a number between 1 and 2 .");
            errorHandler = 1;
        }
    }while (errorHandler == 1);

    return numberOfOption;
}
public static void resolveRequest(Staff user, Request r){
    Scanner scan = new Scanner(System.in);
    String action;
    int numberOfOption = -1;
    int errorHandler;

    do {
        errorHandler = 0;
        System.out.println("Resolve or reject request ?  ");
        System.out.println("    1) Resolve request");
        System.out.println("    2) Delete request");
        System.out.print("Enter choice: ");

        action = scan.nextLine();
        try {
            numberOfOption = Integer.parseInt(action);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid action. Try again !");
            System.out.println("Example of command to do an action: Insert '1' to resolve a request ");
            errorHandler = 1;
        }
        if (errorHandler != 1 && ( numberOfOption < 1 || numberOfOption > 2)){
            System.out.println("Not a valid action. Try again !");
            System.out.println("Try a number between 1 and 2 .");
            errorHandler = 1;
        }
    }while (errorHandler == 1);

    switch (numberOfOption){
        case 1:
            System.out.println("Go to the main menu to resolve it");
            break;
        case 2:
            user.removeRequestToDo(r);
            break;
    }
}

}
