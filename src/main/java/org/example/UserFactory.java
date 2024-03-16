package org.example;

public class UserFactory<T extends Comparable<T>> {
    public User<T> createUserType(AccountType typeOfAcount){
        switch (typeOfAcount){
            case REGULAR:
                return new Regular<>();
            case CONTRIBUTOR:
                return new Contributor<>();
            case ADMIN:
                return new Admin<>();
            default: return null;
        }
    }
}
