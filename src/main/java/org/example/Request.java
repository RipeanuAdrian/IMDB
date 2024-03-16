package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Request {
    private RequestType action;
    private LocalDateTime dateOfRequest;
    String ProductionOrActor;
    String problemDescription;
    String usernameOfRequest;
    String usernameOfDoRequest;
    public Request(){}

    public Request setAction(RequestType action) {
        this.action = action;
        return this;
    }

    public Request setDateOfRequest(LocalDateTime dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
        return this;
    }

    public Request setProductionOrActor(String ProductionOrActor) {
        this.ProductionOrActor = ProductionOrActor;
        return this;
    }

    public Request setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
        return this;
    }

    public Request setUsernameOfRequest(String usernameOfRequest) {
        this.usernameOfRequest = usernameOfRequest;
        return this;
    }

    public Request setUsernameOfDoRequest(String usernameOfDoRequest) {
        this.usernameOfDoRequest = usernameOfDoRequest;
        return this;
    }

    public Request(RequestType action, LocalDateTime dateOfRequest, String ProductionOrActor, String problemDescription,
                   String usernameOfRequest, String usernameOfDoRequest) {
        this.action = action;
        this.dateOfRequest = dateOfRequest;
        this.ProductionOrActor = ProductionOrActor;
        this.problemDescription = problemDescription;
        this.usernameOfRequest = usernameOfRequest;
        this.usernameOfDoRequest = usernameOfDoRequest;
    }

    public RequestType getAction() {
        return action;
    }

    public LocalDateTime getDateOfRequest() {
        return dateOfRequest;
    }

    public String getProductionOrActor() {
        return ProductionOrActor;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getUsernameOfRequest() {
        return usernameOfRequest;
    }

    public String getUsernameOfDoRequest() {
        return usernameOfDoRequest;
    }
    public void displayRequest() {
            System.out.println("Request Type: " + getAction());

            LocalDateTime date = getDateOfRequest();
            if (date != null) {
                String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                System.out.println("Date of Request: " + formattedDate);
            }
            if( getProductionOrActor() != null ) {
                System.out.println("Production or Actor: " + getProductionOrActor());
            }
            System.out.println("Problem Description: " + getProblemDescription());
            System.out.println("Username of Request: " + getUsernameOfRequest());
            System.out.println("Username of Do Request: " + getUsernameOfDoRequest());

    }
}
