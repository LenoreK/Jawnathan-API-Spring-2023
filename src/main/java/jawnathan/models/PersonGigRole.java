package jawnathan.models;

public class PersonGigRole {
    private int gigId;
    private Person person;
    private PersonRole personRole;

    public int getGigId() {
        return gigId;
    }

    public void setGigId(int gigId) {
        this.gigId = gigId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonRole getPersonRole() {
        return personRole;
    }

    public void setPersonRole(PersonRole personRole) {
        this.personRole = personRole;
    }
}