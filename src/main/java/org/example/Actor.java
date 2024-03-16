package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Actor implements Comparable<Actor>{
    static List<Actor> unclaimedActorContriboution = new ArrayList<>();
    String name;
    List<Map.Entry<String, String>> carrer;
    String personalBiograpahy;

    public Actor() {
    }

    public Actor(String name, List<Map.Entry<String, String>> carrer, String personalBiograpahy) {
        this.name = name;
        this.carrer = carrer;
        this.personalBiograpahy = personalBiograpahy;
    }

    public Actor setName(String name) {
        this.name = name;
        return this;
    }

    public Actor setCarrer(List<Map.Entry<String, String>> carrer) {
        this.carrer = carrer;
        return this;
    }

    public Actor setPersonalBiograpahy(String personal_biograpahy) {
        this.personalBiograpahy = personal_biograpahy;
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(@NotNull Actor actor) {

        return this.name.compareTo(actor.name);

    }
    public static Actor searchActorByName(List<Actor> actors, String name) {
        for (Actor actor : actors) {
            if (actor.getName().equals(name)) {
                return actor;
            }
        }
        return null; // Return null if not found
    }
    public static List<Actor> actorsSortedAscendant(List<Actor> actors){

        Collections.sort(actors, Comparator.comparing(Actor::getName));
        return actors;
    }
    public static List<Actor> actorsSortedDescendant(List<Actor> actors){

        Collections.sort(actors, Comparator.comparing(Actor::getName).reversed());
        return actors;
    }
    public static Actor searchActors(String nameOrTitle) {
        IMDB imdb = IMDB.getInstance();
        List<Actor> actors = imdb.getActors();

        for (Actor actor : actors) {
            if (actor.getName().equals(nameOrTitle))
                return actor;
        }
        return null;
    }
    public void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Biography: " + personalBiograpahy);
        System.out.println("Performances:");

        for (Map.Entry<String, String> performance : carrer) {
            System.out.println("  Title: " + performance.getKey() + ", Type: " + performance.getValue());
        }
        System.out.println();
    }

        public void removeCareer(String keyToRemove) {
            Iterator<Map.Entry<String, String>> iterator = carrer.iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();

                if (entry.getKey().equals(keyToRemove)) {
                    iterator.remove();
                    break;
                }
            }
        }
    public void adCareer(String key, String value) {
        Map.Entry<String, String> newEntry = new AbstractMap.SimpleEntry<>(key, value);
        carrer.add(newEntry);
    }

}
