package org.example;

import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IMDB {
    //initialiale of Sigleton class IMDB
    private static IMDB imdb = new IMDB();
    static class RequestHolder{

    }

    private IMDB(){

    }
    public static IMDB getInstance(){

        return imdb;
    }
    ArrayList<User<?>> users;
    ArrayList<Actor> actors;
    ArrayList<Request> requests;
    ArrayList<Production> productions;

    public ArrayList<User<?>> getUsers() {
        return users;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    public IMDB setActors(ArrayList<Actor> actors) {
        this.actors = actors;
        return this;
    }

    public IMDB setProductions(ArrayList<Production> productions) {
        this.productions = productions;
        return this;
    }

//    public static class RequestsHolder implements RequestManager{
//        List<Request> allRequests;
//        @Override
//        public void createRequest(Request r) {
//            allRequests.add(r);
//        }
//
//        @Override
//        public void removeRequest(Request r) {
//            allRequests.remove(r);
//
//        }
//    }
    public void run(){
        loadingDataFromJSON();
        String interfaceOption;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Chose interface: ");
            System.out.println("    1) Terminal");
            System.out.println("    2) GUI");
            System.out.print("Enter interface option:");
            interfaceOption = scan.nextLine();
            if (!interfaceOption.equals("1") && !interfaceOption.equals("2")){
                System.out.println("Incorrect option. Try again !");
            }
        } while (!interfaceOption.equals("1") && !interfaceOption.equals("2"));
        if (interfaceOption.equals("2")){
            System.out.println("Sorry! GUI is unavailable right now. Try again soon :((");
            return;
        }

        if  (interfaceOption.equals("1")) {

            int logout;
            do {
                logout = 0;
                User account = Terminal.autentification();

                if (account != null) {
                    System.out.println("Welcome " + account.username + " !");
                }

                if (account.typeOfUser == AccountType.REGULAR) {
                    int principalMenuAction;
                    int exitAccount = 0;
                    do {

                        principalMenuAction = Terminal.regularFirstPage();
                        switch (principalMenuAction) {
                            case 1:
                                int ViewProductionsOption;

                                ViewProductionsOption = Terminal.ViewProductionsDetails();
                                switch (ViewProductionsOption) {
                                    case 1:
                                        Terminal.filterProductionOnGenres();
                                        break;
                                    case 2:
                                        Terminal.filterProductionOnNumberOfReviews();
                                        break;
                                    case 3:
                                        Terminal.noFilterProductions();
                                        break;
                                }
                                break;

                            case 2:
                                int ViewActorsOption;
                                ViewActorsOption = Terminal.viewActorsDetails();

                                switch (ViewActorsOption) {
                                    case 1:
                                        Terminal.actorsInfo(Actor.actorsSortedAscendant(actors));
                                        break;

                                    case 2:
                                        Terminal.actorsInfo(Actor.actorsSortedDescendant(actors));
                                        break;

                                    case 3:
                                        Terminal.actorsInfo(actors);
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("The notification system isn't implemented yet. Sorry ! :((");
                                break;


                            case 4:
                                int searchOptions;
                                searchOptions = Terminal.searchingMenu();

                                switch (searchOptions) {

                                    case 1:
                                        Terminal.searchMovie();
                                        break;

                                    case 2:
                                        Terminal.searchSeries();
                                        break;

                                    case 3:
                                        Terminal.searchActor();
                                        break;
                                }
                                break;
                            case 5:
                                int favoritesOptions;
                                favoritesOptions = Terminal.FavotiteMenu();

                                switch (favoritesOptions){
                                    case 1:
                                        Production productionAdd = null;
                                        productionAdd = Terminal.searchProductionToFavorites();
                                        if (productionAdd != null)
                                            account.addNewFavorite(productionAdd);
                                        break;

                                    case 2:
                                        Production productionDelete = null;
                                        productionDelete = Terminal.searchProductionToFavorites();
                                        if (productionDelete != null)
                                            account.removeFavorites(productionDelete);
                                        break;

                                    case 3:
                                        Actor actorAdd = null;
                                        actorAdd = Terminal.searchActorToFavorites();
                                        if (actorAdd != null)
                                            account.addNewFavorite(actorAdd);
                                         break;

                                    case 4:
                                        Actor actorDelete = null;
                                        actorDelete = Terminal.searchActorToFavorites();
                                        if (actorDelete != null)
                                            account.removeFavorites(actorDelete);
                                        break;
                                    case 5:
                                        account.displayFavorites();
                                        System.out.println();
                                        break;
                                }
                                break;

                            case 6:
                                    int requestMenu;
                                    requestMenu = Terminal.requestMenu();

                                    switch (requestMenu){
                                        case 1:
                                            Terminal.addRequest(account);
                                            break;

                                        case 2:
                                            Terminal.removeRequest(account);
                                            break;

                                        case 3:
                                            List<Request> requestList = null;
                                            int i = 0;

                                            if  (account instanceof Regular userRegular){
                                                requestList = userRegular.getRequestList();
                                            }
                                            else if (account instanceof Contributor userContriboutor){
                                                requestList = userContriboutor.getRequestList();
                                            }
                                            if (requestList != null) {
                                                for (Request request : requestList) {
                                                    i++;

                                                    System.out.println("    " + i + ")");
                                                    request.displayRequest();
                                                    System.out.println();
                                                }
                                                //break;
                                            }
                                            else if (requestList == null)
                                                System.out.println("No requets made");
                                    }
                                break;

                            case 7:
                                int RatingMenu;
                                RatingMenu = Terminal.RatingMenu();

                                Production ratingProduction = Terminal.selectProduction();
                                if (ratingProduction != null){
                                switch (RatingMenu){
                                    case 1:
                                        Regular userRegular = (Regular) account;
                                        userRegular.addRating(ratingProduction.getTitle());
                                        break;

                                    case 2:
                                        Regular regularUser = (Regular) account;
                                        Rating rating = ratingProduction.searchUserRating(regularUser);
                                        if (rating == null){
                                            System.out.println("No rating found to be deleted");
                                        }
                                        else {
                                            ratingProduction.removeRating(rating);
                                        }
                                        break;

                                    case 3:
                                        ratingProduction.displayRatingList();
                                        break;
                                    }
                                }
                                else System.out.println("No production selected");

                                break;


                            case 8:
                                exitAccount = 1;
                                logout = 1;
                                break;
                            case 9:
                                return;

                        }
                    } while (exitAccount == 0);
                }

                if (account.typeOfUser == AccountType.CONTRIBUTOR){
                    int principalMenuAction;
                    int exitAccount = 0;
                    do {
                        principalMenuAction = Terminal.contributorFirstPage();

                        switch (principalMenuAction) {
                            case 1:
                                int ViewProductionsOption;

                                ViewProductionsOption = Terminal.ViewProductionsDetails();
                                switch (ViewProductionsOption) {
                                    case 1:
                                        Terminal.filterProductionOnGenres();
                                        break;
                                    case 2:
                                        Terminal.filterProductionOnNumberOfReviews();
                                        break;
                                    case 3:
                                        Terminal.noFilterProductions();
                                        break;
                                }
                                break;

                            case 2:
                                int ViewActorsOption;
                                ViewActorsOption = Terminal.viewActorsDetails();

                                switch (ViewActorsOption) {
                                    case 1:
                                        Terminal.actorsInfo(Actor.actorsSortedAscendant(actors));
                                        break;

                                    case 2:
                                        Terminal.actorsInfo(Actor.actorsSortedDescendant(actors));
                                        break;

                                    case 3:
                                        Terminal.actorsInfo(actors);
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("The notification system isn't implemented yet. Sorry ! :((");
                                break;


                            case 4:
                                int searchOptions;
                                searchOptions = Terminal.searchingMenu();

                                switch (searchOptions) {

                                    case 1:
                                        Terminal.searchMovie();
                                        break;

                                    case 2:
                                        Terminal.searchSeries();
                                        break;

                                    case 3:
                                        Terminal.searchActor();
                                        break;
                                }
                                break;
                            case 5:
                                int favoritesOptions;
                                favoritesOptions = Terminal.FavotiteMenu();

                                switch (favoritesOptions){
                                    case 1:
                                        Production productionAdd = null;
                                        productionAdd = Terminal.searchProductionToFavorites();
                                        if (productionAdd != null)
                                            account.addNewFavorite(productionAdd);
                                        break;

                                    case 2:
                                        Production productionDelete = null;
                                        productionDelete = Terminal.searchProductionToFavorites();
                                        if (productionDelete != null)
                                            account.removeFavorites(productionDelete);
                                        break;

                                    case 3:
                                        Actor actorAdd = null;
                                        actorAdd = Terminal.searchActorToFavorites();
                                        if (actorAdd != null)
                                            account.addNewFavorite(actorAdd);
                                        break;

                                    case 4:
                                        Actor actorDelete = null;
                                        actorDelete = Terminal.searchActorToFavorites();
                                        if (actorDelete != null)
                                            account.removeFavorites(actorDelete);
                                        break;
                                    case 5:
                                        account.displayFavorites();
                                        System.out.println();
                                        break;
                                }
                                break;

                            case 6:
                                int requestMenu;
                                requestMenu = Terminal.requestMenu();

                                switch (requestMenu){
                                    case 1:
                                        Terminal.addRequest(account);
                                        break;

                                    case 2:
                                        Terminal.removeRequest(account);
                                        break;

                                    case 3:
                                        List<Request> requestList = null;
                                        int i = 0;

                                        if  (account instanceof Regular userRegular){
                                            requestList = userRegular.getRequestList();
                                        }
                                        else if (account instanceof Contributor userContriboutor){
                                            requestList = userContriboutor.getRequestList();
                                        }
                                        if (requestList != null) {
                                            for (Request request : requestList) {
                                                i++;

                                                System.out.println("    " + i + ")");
                                                request.displayRequest();
                                                System.out.println();
                                            }
                                            //break;
                                        }
                                        else if (requestList == null)
                                            System.out.println("No requets made");
                                }
                                break;

                            case 7:
                                int addOrDeleteProductionOraActorMenu = Terminal.addOrDeleteProductionOrActor();
                                Staff userStaff = (Staff) account;
                                switch (addOrDeleteProductionOraActorMenu){
                                    case 1:
                                        Terminal.addProduction(userStaff);
                                        break;
                                    case 2:
                                        Terminal.removeProduction(userStaff);
                                        break;
                                    case 3:
                                        Terminal.addActor(userStaff);
                                        break;
                                    case 4:
                                        Terminal.removeActor(userStaff);
                                        break;
                                    case 5:
                                        userStaff.displayContriboutions();
                                        break;
                                }

                                break;
                            case 8:
                                userStaff = (Staff) account;
                                int viewOrResolveRequest = Terminal.ViewOrResolveRequestMenu();
                                switch (viewOrResolveRequest){
                                    case 1:
                                        Request selectRequest = Terminal.selectRequestToResolve(userStaff);
                                        Terminal.resolveRequest(userStaff, selectRequest);
                                        break;
                                    case 2:
                                        userStaff.displayRequests();
                                        break;
                                }
                                break;
                            case 9:
                                userStaff = (Staff) account;
                                int updateMenu = Terminal.updateMenu();

                                switch (updateMenu){
                                    case 1:
                                        Production updateProduction = Terminal.selectProduction();
                                        userStaff.updateProduction(updateProduction);
                                        break;
                                    case 2:
                                        Actor updateActor = Terminal.selectActor();
                                        userStaff.updateActor(updateActor);
                                        break;
                                }
                                break;

                            case 10:
                                exitAccount = 1;
                                logout = 1;
                                break;

                            case 11:
                                return;

                        }

                    }while (exitAccount == 0);
                }
                if (account.typeOfUser == AccountType.ADMIN){
                    int principalMenuAction;
                    int exitAccount = 0;
                    do {
                        principalMenuAction = Terminal.adminFirstPage();

                        switch (principalMenuAction) {
                            case 1:
                                int ViewProductionsOption;

                                ViewProductionsOption = Terminal.ViewProductionsDetails();
                                switch (ViewProductionsOption) {
                                    case 1:
                                        Terminal.filterProductionOnGenres();
                                        break;
                                    case 2:
                                        Terminal.filterProductionOnNumberOfReviews();
                                        break;
                                    case 3:
                                        Terminal.noFilterProductions();
                                        break;
                                }
                                break;

                            case 2:
                                int ViewActorsOption;
                                ViewActorsOption = Terminal.viewActorsDetails();

                                switch (ViewActorsOption) {
                                    case 1:
                                        Terminal.actorsInfo(Actor.actorsSortedAscendant(actors));
                                        break;

                                    case 2:
                                        Terminal.actorsInfo(Actor.actorsSortedDescendant(actors));
                                        break;

                                    case 3:
                                        Terminal.actorsInfo(actors);
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("The notification system isn't implemented yet. Sorry ! :((");
                                break;


                            case 4:
                                int searchOptions;
                                searchOptions = Terminal.searchingMenu();

                                switch (searchOptions) {

                                    case 1:
                                        Terminal.searchMovie();
                                        break;

                                    case 2:
                                        Terminal.searchSeries();
                                        break;

                                    case 3:
                                        Terminal.searchActor();
                                        break;
                                }
                                break;
                            case 5:
                                int favoritesOptions;
                                favoritesOptions = Terminal.FavotiteMenu();

                                switch (favoritesOptions){
                                    case 1:
                                        Production productionAdd = null;
                                        productionAdd = Terminal.searchProductionToFavorites();
                                        if (productionAdd != null)
                                            account.addNewFavorite(productionAdd);
                                        break;

                                    case 2:
                                        Production productionDelete = null;
                                        productionDelete = Terminal.searchProductionToFavorites();
                                        if (productionDelete != null)
                                            account.removeFavorites(productionDelete);
                                        break;

                                    case 3:
                                        Actor actorAdd = null;
                                        actorAdd = Terminal.searchActorToFavorites();
                                        if (actorAdd != null)
                                            account.addNewFavorite(actorAdd);
                                        break;

                                    case 4:
                                        Actor actorDelete = null;
                                        actorDelete = Terminal.searchActorToFavorites();
                                        if (actorDelete != null)
                                            account.removeFavorites(actorDelete);
                                        break;
                                    case 5:
                                        account.displayFavorites();
                                        System.out.println();
                                        break;
                                }
                                break;

                            case 6:
                                Terminal.addOrDeleteUser();
                                break;

                            case 7:
                                int addOrDeleteProductionOraActorMenu = Terminal.addOrDeleteProductionOrActor();
                                Staff userStaff = (Staff) account;
                                switch (addOrDeleteProductionOraActorMenu){
                                    case 1:
                                        Terminal.addProduction(userStaff);
                                        break;
                                    case 2:
                                        Terminal.removeProduction(userStaff);
                                        break;
                                    case 3:
                                        Terminal.addActor(userStaff);
                                        break;
                                    case 4:
                                        Terminal.removeActor(userStaff);
                                        break;
                                    case 5:
                                        userStaff.displayContriboutions();
                                        break;
                                }

                                break;
                            case 8:
                                userStaff = (Staff) account;
                                int viewOrResolveRequest = Terminal.ViewOrResolveRequestMenu();
                                switch (viewOrResolveRequest){
                                    case 1:
                                        Request selectRequest = Terminal.selectRequestToResolve(userStaff);
                                        Terminal.resolveRequest(userStaff, selectRequest);
                                        break;
                                    case 2:
                                        userStaff.displayRequests();
                                        break;
                                }
                                break;
                            case 9:
                                userStaff = (Staff) account;
                                int updateMenu = Terminal.updateMenu();

                                switch (updateMenu){
                                    case 1:
                                        Production updateProduction = Terminal.selectProduction();
                                        userStaff.updateProduction(updateProduction);
                                        break;
                                    case 2:
                                        Actor updateActor = Terminal.selectActor();
                                        userStaff.updateActor(updateActor);
                                        break;
                                }
                                break;

                            case 10:
                                exitAccount = 1;
                                logout = 1;
                                break;

                            case 11:
                                return;

                        }

                    }while (exitAccount == 0);
                }
            }while (logout == 1);
        }

    }
    private void loadingDataFromJSON(){

        this.actors = JsonReader.loadingActors("src/main/resources/input/actors.json");
        this.productions = JsonReader.loadingProductions("src/main/resources/input/production.json");
        this.requests = JsonReader.loadingRequest("src/main/resources/input/requests.json");
        this.users = JsonReader.loadingAccounts("src/main/resources/input/accounts.json");

        for( Actor unclaimedActor : Actor.unclaimedActorContriboution) {
            for (User auxUser : users) {
                if (auxUser instanceof Admin admin) {
                    admin.addNewContriboutionActorAndProudcction(unclaimedActor);
                }
            }
        }


        for (Request request : requests){

            for( User user : users){

                if( user.getUsername().equals(request.getUsernameOfRequest())) {
                    if (user instanceof Regular) {
                        Regular userRegular = (Regular) user;
                        userRegular.createRequest(request);
                    }
                    else if (user instanceof Contributor){
                        Contributor userContributor = (Contributor) user;
                        userContributor.createRequest(request);
                    }
                }
                if(user.getUsername().equals(request.getUsernameOfDoRequest())){
                    Staff userStaff = (Staff) user;
                    userStaff.addToRequestList(request);
                }
            }
        }
    }
    public User autentification(String email, String password){

        int ifEmailExist = 0;
        for (User user : users){
            if ( user.info.getCredentials().getEmail().equals(email)) {
                ifEmailExist = 1;
                if ( user.info.getCredentials().getPassword().equals(password)) {
                    return user;
                }
                else {
                    System.out.println("Incorrect password");
                }
            }
        }
        if (ifEmailExist == 0)
            System.out.println("No existing email");
        return null;
    }

    public void displayRequestList(){

        for (Request request : requests){

            System.out.println("Action: " + request.getAction());
            System.out.println("Date of the request: " + request.getDateOfRequest());
            System.out.println("Production/Actor: " + request.getProductionOrActor());
            System.out.println("Problem description: " + request.getProblemDescription());
            System.out.println("Request made by: " + request.getUsernameOfRequest());
            System.out.println("Request to: " + request.getUsernameOfDoRequest());
            System.out.println();

        }
    }
    public void addProduction(Production production){
        productions.add(production);
    }
    public void addUser(User user){
        users.add(user);
    }
    public void addActor(Actor actor){
        actors.add(actor);
    }


}
