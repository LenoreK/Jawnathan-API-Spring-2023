package jawnathan.models;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int personId;
    private String firstName;
    private String middleName;
    private String lastName;

    private List<PersonGigRole> personGigRoles = new ArrayList<>();

    public Person(int personId, String firstName, String middleName, String lastName) {
        this.personId = personId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PersonGigRole> getPersonGigRoles() {
        return personGigRoles;
    }

    public void setPersonGigRoles(List<PersonGigRole> personGigRoles) {
        this.personGigRoles = personGigRoles;
    }
}