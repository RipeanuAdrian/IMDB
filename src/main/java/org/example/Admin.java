package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class Admin<T extends Comparable<T> > extends Staff<T>  {

    static class  RequestsHolder {
            private static List<Request> allRequests;
            public static void addRequest(Request newRequest){
                if (allRequests == null){
                    allRequests = new ArrayList<>();
                }
                allRequests.add(newRequest);
            }
        public static void removeRequest(Request deleteRequest){
            allRequests.remove(deleteRequest);
        }
    }

    public void addUser(User newUser){
        IMDB imdb = IMDB.getInstance();
        ArrayList<User<?>> users = imdb.getUsers();

        users.add(newUser);
    }
    public void removeUser(User removeUser){
        IMDB imdb = IMDB.getInstance();
        ArrayList<User<?>> users = imdb.getUsers();

        users.remove(removeUser);
    }


    public Admin() {
        super();
    }


}
