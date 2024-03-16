package org.example;

public class Episode {
    String name;
    Integer time;

    public Episode() {
    }

    public Episode(String name, Integer time) {
        this.name = name;
        this.time = time;
    }

    public Episode setName(String name) {
        this.name = name;
        return this;
    }

    public Episode setTime(Integer time) {
        this.time = time;
        return this;
    }
}
