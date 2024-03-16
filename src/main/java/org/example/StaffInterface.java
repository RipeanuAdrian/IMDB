package org.example;

public interface StaffInterface {
    public void addProductionSystem(Production p);
    public void addActorSystem(Actor a);
    public void removeProductionSystem(String name);
    public void removeActorSystem(String name);
    public void updateProduction(Production p);
    public void updateActor(Actor a);
    //mia trebuie pus ceva ultimul punct de la enunt de la interfata asta;
}
