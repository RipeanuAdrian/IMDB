package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JsonReader {
    public static  ArrayList<User<?>> loadingAccounts(String file){
        ArrayList<User<?>> users = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = (Object) parser.parse(new FileReader(file));
            JSONArray jsonArray = (JSONArray) obj;

            for (Object acountObj : jsonArray) {
                JSONObject jsonObject = (JSONObject) acountObj;
                User user = parseUser(jsonObject);
                users.add(user);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found at reading the acounts info");
        } catch (IOException e) {
            System.out.println("Error: Input/Output at reading the acounts info");
        } catch (ParseException e) {
            System.out.println("Error: Parser problem  at reading the acounts info");
        }
        return users;
    }

    public static User<?> parseUser(JSONObject acountObject){

        //seting usertype na extracting
        String userTypeString = (String) acountObject.get("userType");
        AccountType userType = AccountType.valueOf(userTypeString.toUpperCase());


        //seting username
        String username = (String) acountObject.get("username");

        //seing experience

        String experienceString = (String) acountObject.get("experience");
        Integer experience = null;
        if (experienceString == null) {
            experience = null;
        }
        else {
            experience = Integer.parseInt(experienceString);
        }

        //seting Information
        JSONObject informationJson = (JSONObject) acountObject.get("information");

        //seting credetials
        JSONObject credentialsJson = (JSONObject) informationJson.get("credentials");
        String email = (String) credentialsJson.get("email");
        String password = (String) credentialsJson.get("password");
        Credentials newCredentials = new Credentials(email, password);

        //again information
        String name = (String) informationJson.get("name");
        String country = (String) informationJson.get("country");
        Number ageString = (Number) informationJson.get("age");
        int age = ageString.intValue();
        char gender = ((String) informationJson.get("gender")).charAt(0);

        String birthDateString = (String) informationJson.get("birthDate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime birthDate = LocalDate.parse(birthDateString, formatter).atStartOfDay();

        User.Information.BuilderInformation newBuilder = User.Information.BuilderInformation.newInstance();
        User.Information newInformation = newBuilder.setCredentials(newCredentials).setCountry(country).setGen(gender).
                setName(name).setDateOfBirth(birthDate).setAge(age).build();

//        SortedSet<T> newContriboutionActorsAndProduction = new TreeSet<>();
//        SortedSet<> newFavorite = new TreeSet<>();
        //contriboution
        List<String> newNotification = null;
        if ( acountObject.containsKey("notifications")) {
            JSONArray newNotificationReader = (JSONArray) acountObject.get("notifications");
            newNotification = new ArrayList<>();
            for (Object production : newNotificationReader) {
                newNotification.add((String) production);
            }
        }

        UserFactory<?> userFactory = new UserFactory<>();
        User newUser =  userFactory.createUserType(userType);
        newUser.setInfo(newInformation);
        newUser.setTypeOfUser(userType);
        newUser.setUsername(username);
        newUser.setExperience(experience);
        newUser.setNotifications(newNotification);

        if (userType != AccountType.REGULAR) {
            Staff newUserStaff = (Staff) newUser;
            JSONArray productionsContributionArray = (JSONArray) acountObject.get("productionsContribution");
            IMDB imdb = IMDB.getInstance();
            ArrayList<Production> productionsList = imdb.getProductions();

            for (Object production : productionsContributionArray) {
                String productionCast = (String) production;

                Production actualProduction = Production.searchProductionByName(productionsList, productionCast);
                int index = productionsList.indexOf(actualProduction);

                if (index != -1) {
                    // Element found, retrieve and print it
                    Production foundElement = productionsList.get(index);
                    if (foundElement instanceof Movie) {
                        Movie foundElementMovie = (Movie) foundElement;
                        newUserStaff.addNewContriboutionActorAndProudcction(foundElementMovie);
                    } else if (foundElement instanceof Series) {
                        Series foundElementSeries = (Series) foundElement;
                        newUserStaff.addNewContriboutionActorAndProudcction(foundElementSeries);
                    }
                }

                //newContriboutionActorsAndProduction.add((Production) production);
            }
            if (acountObject.containsKey("actorsContribution")) {
                JSONArray actorsContriboutionArray = (JSONArray) acountObject.get("actorsContribution");
                ArrayList<Actor> actorsList = imdb.getActors();

                for (Object actorObj : actorsContriboutionArray) {
                    String actor = (String) actorObj;
                    Actor actualActor = Actor.searchActorByName(actorsList, actor);

                    int index = actorsList.indexOf(actualActor);
                    if (index != -1) {
                        newUserStaff.addNewContriboutionActorAndProudcction(actorsList.get(index));
                    }
                }
            }
        }
            IMDB imdb = IMDB.getInstance();
        if ( acountObject.containsKey("favoriteProductions")) {
            JSONArray favoriteProductions = (JSONArray) acountObject.get("favoriteProductions");

            ArrayList<Production> productionsList = imdb.getProductions();


            for (Object production : favoriteProductions) {

                String productionCast = (String) production;

                Production actualProduction = Production.searchProductionByName(productionsList, productionCast);
                int index = productionsList.indexOf(actualProduction);

                if (index != -1) {
                    // Element found, retrieve and print it
                    Production foundElement = productionsList.get(index);

                    if (foundElement instanceof Movie) {
                        Movie foundElementMovie = (Movie) foundElement;
                        newUser.addNewFavorite(foundElementMovie);

                    } else if (foundElement instanceof Series) {
                        Series foundElementSeries = (Series) foundElement;
                        newUser.addNewFavorite(foundElementSeries);
                    }
                }

            }
        }
        if ( acountObject.containsKey("favoriteActors")) {
            JSONArray actorsFavoriteArray = (JSONArray) acountObject.get("favoriteActors");
            ArrayList<Actor> actorsList = imdb.getActors();

            for (Object actorObj : actorsFavoriteArray) {
                String actor = (String) actorObj;
                Actor actualActor = Actor.searchActorByName(actorsList, actor);

                int index = actorsList.indexOf(actualActor);

                if (index != -1) {
                    newUser.addNewFavorite(actorsList.get(index));
                }
            }
        }

        //!! DE ADUGAT LA NOUL USER SI LISTA DE FAVORITE SI LISTA DE CONTRIBUTII

        return newUser;
        }



    public static ArrayList<Actor> loadingActors (String file) {
        ArrayList<Actor> actors = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(file));
            JSONArray jsonArray = (JSONArray) obj;

            for (Object actorObj : jsonArray) {
                JSONObject actorJson = (JSONObject) actorObj;
                Actor actor = parseActor(actorJson);
                actors.add(actor);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found at reading the actors info");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error: Input/Output at reading the actors info");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            System.out.println("Error: Parser problem  at reading the actors info");
            throw new RuntimeException(e);
        }
        return actors;
    }


    private static Actor parseActor(JSONObject actorJson) {
        Actor actor = new Actor();
        actor.setName((String) actorJson.get("name"));
        actor.setPersonalBiograpahy((String) actorJson.get("biography"));

        JSONArray performancesArray = (JSONArray) actorJson.get("performances");
        List<Map.Entry<String, String>> performances = new ArrayList<>();

        for (Object performanceObj : performancesArray) {
            JSONObject performanceJson = (JSONObject) performanceObj;
            String title = (String) performanceJson.get("title");
            String type = (String) performanceJson.get("type");
            performances.add(Map.entry(title, type));
        }

        actor.setCarrer(performances);

        return actor;
    }

    public static ArrayList<Production> loadingProductions(String file){

        ArrayList<Production> productions = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(file));
            JSONArray jsonArray = (JSONArray) obj;

            for( Object productionObj : jsonArray){
                JSONObject productionJson = (JSONObject) productionObj;
                Production production = parseProduction(productionJson);

                productions.add(production);
            }

        }catch (FileNotFoundException e) {
            System.out.println("Error: File not found at reading the productions info");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error: Input/Output at reading the productions info");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            System.out.println("Error: Parser problem  at reading the productions info");
            throw new RuntimeException(e);
        }

        return productions;

    }
    private static Production parseProduction(JSONObject productionJson){
        String productionTitle = (String)productionJson.get("title");
        JSONArray directorsJson = (JSONArray) productionJson.get("directors");
        List<String> directors = JsonReader.convertJsonArrayToList(directorsJson);
        JSONArray actorsJson = (JSONArray) productionJson.get("actors");
        List<String> actors = JsonReader.convertJsonArrayToList(actorsJson);
        IMDB imdb = IMDB.getInstance();
        List<Actor> actorsFromImdb = imdb.getActors();

        //!DE FACUT ASTA CU ACTORII MAI TARZIU DE ADAUGAT ACTOII CARE NU SUNT LA LISTA CU TOTI ACTORII
        JSONArray genresJson = (JSONArray) productionJson.get("genres");
        List<Genre> genres = JsonReader.convertJsonArrayToListOfGenres(genresJson);
        JSONArray ratingsJson = (JSONArray) productionJson.get("ratings");
        List<Rating> ratings = JsonReader.convertJsonArrayToListOfRatings(ratingsJson);
        String plot = (String) productionJson.get("plot");
        Double grade = (Double) productionJson.get("averageRating");
        String productionType = (String)productionJson.get("type");

        for (String actorName : actors){
            int check = 0;
            for(Actor imdbActor : actorsFromImdb){
                if (actorName.equals(imdbActor.getName())){
                    check = 1;
                    break;
                }
            }
            if (check == 0){
                String productionNewActorTitle = productionTitle;
                List<Map.Entry<String, String>> actorCareer = new ArrayList<>();
                actorCareer.add(new AbstractMap.SimpleEntry<>(productionTitle, productionType));
                Actor newActor = new Actor(actorName,actorCareer," ");

                imdb.addActor(newActor);
                Actor.unclaimedActorContriboution.add(newActor);


            }

        }

        if ( productionType.equals("Movie")){
            Integer time;
            if (productionJson.containsKey("duration")){
                String timeString = (String) productionJson.get("duration");
                time = JsonReader.extractNumericPart(timeString);

            }
            else{
                time = null;
            }
            Integer releaseYear;
            if (productionJson.containsKey("releaseYear")){
                Number releaseYearNumber = (Number) productionJson.get("releaseYear");
                releaseYear = releaseYearNumber.intValue();
            }
            else{
                releaseYear = null;
            }

            Movie newMovie = new Movie(productionTitle, directors, actors, genres, ratings, plot, grade, time,
                    releaseYear );
            return newMovie;
        }
        else if ( productionType.equals("Series")){
            Integer releaseYear;
            if (productionJson.containsKey("releaseYear")){
                Number releaseYearNumber = (Number) productionJson.get("releaseYear");
                releaseYear = releaseYearNumber.intValue();
            }
            else{
                releaseYear = null;
            }
            Integer numSeasons;
            if (productionJson.containsKey("numSeasons")){
                Number numSeasonsNumber = (Number) productionJson.get("numSeasons");
                numSeasons = numSeasonsNumber.intValue();

            }
            else{
                numSeasons = null;
            }
            Map<String,List<Episode>> seasonesContent;
            if (productionJson.containsKey("seasons")){
                JSONObject seasons = (JSONObject) productionJson.get("seasons");
                seasonesContent = seasoneEpisodesLoading(seasons);
            }
            else{
                seasonesContent = null;
            }
            Series  newSerie = new Series(productionTitle, directors, actors, genres, ratings, plot, grade, releaseYear,
                    numSeasons, seasonesContent);
            return newSerie;
        }

        return null;
    }
    public static ArrayList<Request> loadingRequest(String file){
        ArrayList<Request> requests = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(file));
            JSONArray jsonArray = (JSONArray) obj;

            for( Object requestObj : jsonArray){
                JSONObject requestJson = (JSONObject) requestObj;
                Request request = parseRequest(requestJson);

                requests.add(request);
            }

        }catch (FileNotFoundException e) {
            System.out.println("Error: File not found at reading the productions info");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error: Input/Output at reading the productions info");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            System.out.println("Error: Parser problem  at reading the productions info");
            throw new RuntimeException(e);
        }
        return requests;
    }
    private static Request parseRequest(JSONObject requestJson){
        String requestTypeString = (String) requestJson.get("type");
        RequestType requestType = RequestType.valueOf(((String) requestTypeString).toUpperCase());
        LocalDateTime createdDate = LocalDateTime.parse((String) requestJson.get("createdDate"),
                DateTimeFormatter.ISO_DATE_TIME);
        String username = (String) requestJson.get("username");
        String toUsername = (String) requestJson.get("to");
        String requestDescription = (String) requestJson.get("description");
        String problem = null;
        if ( requestJson.containsKey("actorName")){
            problem = (String) requestJson.get("actorName");
        } else if ( requestJson.containsKey("movieTitle")) {
            problem = (String) requestJson.get("movieTitle");
        }
        Request newRequest = new Request(requestType, createdDate,problem, requestDescription, username, toUsername );
        return newRequest;
    }
    private static List<String> convertJsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (Object item : jsonArray) {
            list.add(item.toString());
        }
        return list;
    }
    private static List<Genre> convertJsonArrayToListOfGenres(JSONArray jsonArray) {
        List<Genre> genreList = new ArrayList<>();
        for (Object item : jsonArray) {
            Genre genre = Genre.valueOf(((String) item).toUpperCase());
            genreList.add(genre);
        }
        return genreList;
    }
    private static List<Rating> convertJsonArrayToListOfRatings(JSONArray jsonArray) {
        List<Rating> ratingList = new ArrayList<>();

        for (Object obj : jsonArray) {
            JSONObject objJson =  (JSONObject) obj;
            String username = (String) objJson.get("username");
            Number rating = (Number) objJson.get("rating");
            String comment = (String) objJson.get("comment");
            Rating newRating = new Rating(username,rating.intValue(),comment);

            ratingList.add(newRating);
        }
        return ratingList;
    }
    private static Map<String,List<Episode>> seasoneEpisodesLoading(JSONObject seasones){
        Map<String,List<Episode>> seasonesContent = new HashMap<>();

        for (Object seasone : seasones.keySet()){
            String seasoneTitle = (String) seasone;
            JSONArray seasoneEpisodesJson = (JSONArray) seasones.get(seasoneTitle);

            List<Episode> episodeList = new ArrayList<>();

            for (Object episodeObj: seasoneEpisodesJson){
                JSONObject episodeObjJson = (JSONObject) episodeObj;
                String episodeTiltle = (String) episodeObjJson.get("episodeName");
                String episodeTimeString = (String) episodeObjJson.get("duration");
                Integer episodeTime = JsonReader.extractNumericPart(episodeTimeString);

                Episode newEpisode = new Episode(episodeTiltle,episodeTime);
                episodeList.add(newEpisode);
            }
            seasonesContent.put(seasoneTitle, episodeList);

        }
        return seasonesContent;
    }
    private static Integer extractNumericPart(String duration) {
        // Removing non-numeric characters
        String numericPart = "";
        for (char c : duration.toCharArray()) {
            if (c == ' ') {
                break;
            }
            else {
                numericPart = numericPart + c;
            }
        }
        return Integer.parseInt(numericPart);
    }
}

