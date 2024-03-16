package org.example;

import java.util.Comparator;

public class SortedComparator implements Comparator<Object> {

    @Override
    public int compare(Object o, Object t1) {
        String oString = getIdentifier(o);
        String t1String = getIdentifier(t1);
        return oString.compareTo(t1String);
    }
    public String getIdentifier(Object obj){
        if (obj instanceof Production){
            Production productionObj = (Production) obj;
            return productionObj.getTitle();
        } else if (obj instanceof Actor) {
            Actor actorObj = (Actor) obj;
            return actorObj.getName();

        }
        return null;
    }
}
