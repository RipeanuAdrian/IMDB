package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Contributor<T extends Comparable<T> > extends Staff <T> implements RequestManager {
    List<Request> autorequest = null;

public Contributor(){
    super();
}
    @Override
    public void createRequest(Request r) {
        if (autorequest == null)
            autorequest = new ArrayList<>();
        autorequest.add(r);
    }

    @Override
    public void removeRequest(Request r) {
        autorequest.remove(r);
    }
    public List<Request> getRequestList() {
        return requestList;
    }


}
